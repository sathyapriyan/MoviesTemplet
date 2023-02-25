package com.example.avengersassemble.presentation.view

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.avengersassemble.R
import com.example.avengersassemble.data.remote.NetworkResult
import com.example.avengersassemble.databinding.FragmentLoginBinding
import com.example.avengersassemble.util.SharedPreferenceKeys
import com.example.avengersassemble.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    @Inject
    @Named("userPreferences")
    lateinit var preferencesDataStoreUser: DataStore<Preferences>

    @Inject
    lateinit var ioDispatcher: CoroutineDispatcher



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater)
        val textLogin = SpannableString(requireContext().getString(R.string.tab_login))
        val textRegister = SpannableString(requireContext().getString(R.string.tab_register))

        binding.btnLogin.setOnClickListener {

            viewModel.authenticatePatient(
                mobileNum = binding.txtInputLayoutUserName.editText?.text.toString(),
                password = binding.txtInputLayoutPassword.editText?.text.toString(),
                requireContext()
            )

        }

        binding.txtLogin.setOnClickListener {
            textLogin.setSpan(UnderlineSpan(), 0, textLogin.length, 0)

            binding.txtLogin.text = textLogin
            binding.txtRegister.text = requireContext().getString(R.string.tab_register_u)

            binding.txtLogin.setTextColor(ContextCompat.getColor(requireContext(),R.color.purple_500))
            binding.txtRegister.setTextColor(ContextCompat.getColor(requireContext(),R.color.gray))
        }
        binding.txtRegister.setOnClickListener {
            textRegister.setSpan(UnderlineSpan(), 0, textRegister.length, 0)
            binding.txtLogin.text = requireContext().getString(R.string.tab_login_u)
            binding.txtRegister.text = textRegister
            binding.txtLogin.setTextColor(ContextCompat.getColor(requireContext(),R.color.gray))
            binding.txtRegister.setTextColor(ContextCompat.getColor(requireContext(),R.color.purple_500))

        }
        viewModel.authenticationResponse.observe(viewLifecycleOwner){ response ->

            when(response) {

                is NetworkResult.Success -> {

                    if(response.data?.status!!){
                        binding.progressBarLogin.visibility = View.INVISIBLE
                        binding.btnLogin.visibility = View.VISIBLE

                        Toast.makeText(
                            requireContext(),
                            "Login Successful",
                            Toast.LENGTH_SHORT
                        ).show()
                        lifecycleScope.launch(ioDispatcher) {
                            preferencesDataStoreUser.edit { preferences ->
                                preferences[SharedPreferenceKeys.USER_ID] = response.data?.userId!!
                            }

                        }

                        val directions = LoginFragmentDirections.actionLoginFragmentToMainScreenFragment(response.data?.userId!!)
                        findNavController().navigate(directions = directions)


                    }else{
                        binding.progressBarLogin.visibility = View.INVISIBLE
                        binding.btnLogin.visibility = View.VISIBLE

                        Toast.makeText(
                            requireContext(),
                            "Login Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }


                    println("Login Response --> ${response.data}")

                }

                is NetworkResult.Error -> {

                    binding.progressBarLogin.visibility = View.INVISIBLE
                    binding.btnLogin.visibility = View.VISIBLE

                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()

                }

                is NetworkResult.Loading -> {

                    binding.progressBarLogin.visibility = View.VISIBLE
                    binding.btnLogin.visibility = View.INVISIBLE

                }

            }

        }

        return binding.root

        /*val tv = view!!.findViewById(R.id.tv) as TextView
        val content = SpannableString("Content")
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        tv.text = content*/
    }
}