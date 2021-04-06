package com.sharonahamon.petsleuth.screens.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.sharonahamon.petsleuth.R
import com.sharonahamon.petsleuth.common.PetSleuthViewModel
import com.sharonahamon.petsleuth.databinding.LoginFragmentBinding
import timber.log.Timber

class LoginFragment : Fragment() {

    private lateinit var viewModel: PetSleuthViewModel

    private lateinit var binding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.i("called OnCreateView")

        // get the existing instance of the viewModel instead of creating a new one
        // tie the viewModel to the parent activity so that it does not get
        // destroyed when a fragment is popped off the back stack
        viewModel = ViewModelProvider(requireActivity()).get(PetSleuthViewModel::class.java)
        Timber.i("called ViewModelProvider")

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
        val email = binding.loginUsernameText.text.toString()
        Timber.i("email=%s", email)
        return email
    }
}