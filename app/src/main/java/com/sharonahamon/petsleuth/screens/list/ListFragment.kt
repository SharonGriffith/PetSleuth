package com.sharonahamon.petsleuth.screens.list

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.sharonahamon.petsleuth.R
import com.sharonahamon.petsleuth.databinding.ListFragmentBinding
import com.sharonahamon.petsleuth.common.PetSleuthViewModel
import timber.log.Timber

class ListFragment : Fragment() {

    private lateinit var viewModel: PetSleuthViewModel

    private lateinit var binding: ListFragmentBinding

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

        binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)

        binding.petSleuthViewModel = viewModel
        binding.lifecycleOwner = this

        binding.listButtonAdd.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_listItemFragment_to_instructionsFragment))
        binding.fab.setOnClickListener { view: View ->
            goToPetDetail(view)
        }

        return binding.root
    }

    private fun goToPetDetail(view: View) {
        // for now, this is going to the last pet in the list
        // I assume we will learn how to select an item and do something with it
        // I spent a lot of time researching how to do this and it seemed very complex for a first project
        val action =
            ListFragmentDirections.actionListFragmentToDetailFragment(viewModel.petList.size)
        view.findNavController().navigate(action)
    }

    private fun logout(view: View) {
        viewModel.logout()
        view.findNavController().navigate(R.id.action_listItemFragment_to_loginFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // custom toolbar that only appears for this fragment
        binding.listToolbar.inflateMenu(R.menu.list_menu)

        binding.listToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_logout -> {
                    logout(view)
                    true
                }
                else -> false
            }
        }

        Timber.i("called OnViewCreated")
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