package com.example.paktunes.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.paktunes.R
import com.example.paktunes.databinding.FragmentSignUpBinding
import com.example.paktunes.ui.viewModel.AuthViewModel
import com.example.paktunes.utill.Resource
import com.example.paktunes.utill.hideProgressBar
import com.example.paktunes.utill.showCenteredProgressBar
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch

class SignUpFragment() : Fragment(R.layout.fragment_sign_up) {

    private lateinit var binding: FragmentSignUpBinding
    private val authViewModel: AuthViewModel by activityViewModels()
    val logTag = "SignUpFragment"
    private var progressBar: ProgressBar? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)

        binding.loginLink.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.btnSignUp.setOnClickListener {
            val name = binding.inputFullName.text.toString()
            val password = binding.inputPassword.text.toString()
            val email = binding.inputEmail.text.toString()

            authViewModel.signUp(name, email, password)

            viewLifecycleOwner.lifecycleScope.launch {
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    authViewModel.signUpFlow.collect {
                        flowResponse(it)
                    }
                }
            }

        }
    }

    private fun flowResponse(res: Resource<FirebaseUser>?) {

        when (res) {
            is Resource.Success -> {
                progressBar = requireActivity().showCenteredProgressBar()
                Toast.makeText(context, "account Created Successfully", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
            }

            is Resource.Loading -> {
                progressBar = requireActivity().showCenteredProgressBar()
            }

            is Resource.Error -> {
                progressBar?.let { requireActivity().hideProgressBar(it) }
                progressBar = null
                Toast.makeText(context, res.message, Toast.LENGTH_LONG).show()
                Log.d(logTag, res.message)
            }

            else -> {
                Toast.makeText(context, "something went wrong", Toast.LENGTH_LONG).show()

            }
        }


    }
    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().hideProgressBar(progressBar!!)
        progressBar = null
    }


}