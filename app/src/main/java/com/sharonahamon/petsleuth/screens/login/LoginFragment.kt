package com.sharonahamon.petsleuth.screens.login

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
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
        viewModel.currentUserEmail = getEmailFromUserInput()

        val action =
            LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()

        // build a list of dummy data to simulate reading from a database
        viewModel.buildDummyPetList()

        view.findNavController().navigate(action)
    }

    private fun getEmailFromUserInput(): String {
        val email = binding.loginUsernameText.text.toString()
        Timber.i("email=%s", email)
        return email
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.i("called OnDestroyView")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("called OnCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("called OnDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Timber.i("called OnDetach")
    }

    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
        Timber.i("called OnInflate")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("called OnPause")
    }

    override fun onResume() {
        super.onResume()
        Timber.i("called OnResume")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Timber.i("called OnSaveInstanceState")
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        Timber.i("called OnAttachFragment")
    }

    override fun onStart() {
        super.onStart()
        Timber.i("called OnStart")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("called OnStop")
    }
}