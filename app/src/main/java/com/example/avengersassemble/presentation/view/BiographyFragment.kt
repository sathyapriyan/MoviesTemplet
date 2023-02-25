package com.example.avengersassemble.presentation.view


import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.avengersassemble.R
import com.example.avengersassemble.databinding.FragmentBiographyBinding
import com.example.avengersassemble.viewmodels.BiographyViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BiographyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class BiographyFragment : BottomSheetDialogFragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentBiographyBinding
    private val viewModel: BiographyViewModel by viewModels()
    private val args: BiographyFragmentArgs by navArgs()

    private var locUserId:String = "1"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBiographyBinding.inflate(layoutInflater)
        binding.txtName.text = args.data.realname
        binding.txtBio.text = args.data.bio

        lifecycleScope.launch {
            viewModel.userId.collect{
                locUserId=it
            }
        }

        val f=args.isFavourite

        println("doctorArgs.isFavourite $f")
        if(args.isFavourite){
            binding.textViewFavourite.text= resources.getText(R.string.remove_txt)
            binding.imgbtnFavourite.setImageResource(R.drawable.ic_unfavorite)


        }else{
            binding.textViewFavourite.text= resources.getText(R.string.add_txt)
            binding.imgbtnFavourite.setImageResource(R.drawable.ic_favorite)

        }
        binding.cardViewFav.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.addOrRemoveFavourite(args.data,args.data.isFavourite)
            }
            val directions = BiographyFragmentDirections.actionBiographyFragmentToMainScreenFragment(locUserId)
            findNavController().navigate(directions = directions)
        }

        return binding.root

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentBiographyBinding.inflate(layoutInflater)


        return super.onCreateDialog(savedInstanceState)
    }

}