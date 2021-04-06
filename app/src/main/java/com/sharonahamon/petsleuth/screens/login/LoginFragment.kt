package com.sharonahamon.petsleuth.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.sharonahamon.petsleuth.R
import com.sharonahamon.petsleuth.common.PetSleuthViewModel
import com.sharonahamon.petsleuth.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    // Use the 'by activityViewModels()' Kotlin property delegate
    // from the fragment-ktx artifact
    private val viewModel: PetSleuthViewModel by activityViewModels()

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)

        binding.petSleuthViewModel = viewModel
        binding.lifecycleOwner = this

        binding.loginButtonSignin.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            doLogin(view)
        }

        binding.loginButtonRegister.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            doRegister(view)
        }

        return binding.root
    }

    private fun doRegister(view: View) {
        // right now the buttons both do the same thing, so just call common code
        // but in the future they will do different things
        doLogin(view)
    }

    private fun doLogin(view: View) {
        // save off the email in the view model
        viewModel.login(getEmailFromUserInput())

        // build a list of dummy data to simulate reading from a database
        //viewModel.buildDummyPetList()

        view.findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
    }

    private fun getEmailFromUserInput(): String {
        return binding.loginUsernameText.text.toString()
    }
}