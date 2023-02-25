package com.example.avengersassemble.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.avengersassemble.data.remote.NetworkResult
import com.example.avengersassemble.databinding.FragmentMainScreenBinding
import com.example.avengersassemble.data.local.entity.FavouriteList
import com.example.avengersassemble.presentation.adapters.RecyclerViewAdapter
import com.example.avengersassemble.viewmodels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class MainScreenFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentMainScreenBinding
    private val viewModel: ProfileViewModel by viewModels()
    private val doctorArgs: MainScreenFragmentArgs by navArgs()

    @Inject
    lateinit var ioDispatcher: CoroutineDispatcher

    private val dataList: MutableList<FavouriteList> = mutableListOf()

    private val recyclerViewAdapter : RecyclerViewAdapter by lazy{
        RecyclerViewAdapter(dataList) {
            val directions = MainScreenFragmentDirections.actionMainScreenFragmentToBiographyFragment(it.isFavourite,it)
            findNavController().navigate(directions = directions)
        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainScreenBinding.inflate(layoutInflater)


        binding.txtUserName.text = "hi, ${doctorArgs.userId}!"


            viewModel.getAllDoctorsDetails(
                context = requireContext()
            )


        viewModel.ListResponse.observe(viewLifecycleOwner) { response ->
            println(" it-->     "+response)

            when(response) {

                is NetworkResult.Success -> {

                    println(" it-->     "+response)

                    dataList.clear()
                    response.data?.forEach {
                        dataList.add(it)
                        println(" it-->     "+it)
                    }

                    setupRecyclerView()

                }

                is NetworkResult.Error -> {


                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                }

                is NetworkResult.Loading -> {


                }

            }

        }

        return binding.root
    }
    private fun setupRecyclerView() {

            binding.reccleViewMainData.apply {
                adapter = recyclerViewAdapter
                layoutManager = GridLayoutManager(
                    context,
                    2
                )
                isNestedScrollingEnabled = false
            }

    }

}