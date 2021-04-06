package com.sharonahamon.petsleuth.screens.list

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.sharonahamon.petsleuth.R
import com.sharonahamon.petsleuth.common.PetSleuthViewModel
import com.sharonahamon.petsleuth.databinding.ListFragmentBinding

class ListFragment : Fragment() {

    // Use the 'by activityViewModels()' Kotlin property delegate
    // from the fragment-ktx artifact
    private val viewModel: PetSleuthViewModel by activityViewModels()

    private lateinit var binding: ListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Timber.i("called OnCreateView")

        binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false)

        binding.petSleuthViewModel = viewModel
        binding.lifecycleOwner = this

        binding.listButtonAdd.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_listItemFragment_to_instructionsFragment))
        binding.fab.setOnClickListener { view: View ->
            goToPetDetail(view)
        }

        return binding.root
    }

    /**
     * The base code for this function was copied from the Android Developer website.
     */
    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // custom toolbar that only appears for this fragment
        binding.listToolbar.inflateMenu(R.menu.list_menu)

        binding.listToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_logout -> {
                    doLogout(view)
                    true
                }
                else -> false
            }
        }

        // observe the view model's nextPetId to make the widget (which jumps to a specific pet ID) visible
        // it basically reflects the pet list size
        // at initial load the pet list is empty, so there are no pets to jump to, thus the widget is invisible
        viewModel.nextPetId.observe(viewLifecycleOwner, Observer<Int> { nextPetId: Int ->
            if (nextPetId >= 1) {
                binding.listInstructionsContainer.visibility = View.VISIBLE
                binding.fab.visibility = View.VISIBLE
            }
        })

        //Timber.i("called OnViewCreated")
    }

    private fun goToPetDetail(view: View) {
        // for now, this is going to the first pet in the list by default,
        // else the user can type in which ID they want to jump to
        var petId = getPetIdFromUserInput()

        // I assume we will learn how to select a list item and do something with it
        // I spent a lot of time researching how to do this and it seemed very complex for a first project
        val action =
            ListFragmentDirections.actionListFragmentToDetailFragment(petId)
        view.findNavController().navigate(action)
    }

    private fun getPetIdFromUserInput(): Int {
        val petId = binding.listPetIdValue.text?.toString()?.toInt() ?: 1
        //Timber.i("petId=%s", petId)
        return petId
    }

    private fun doLogout(view: View) {
        viewModel.logout()
        view.findNavController().navigate(R.id.action_listItemFragment_to_loginFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //Timber.i("called OnDestroyView")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Timber.i("called OnCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        //Timber.i("called OnDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        //Timber.i("called OnDetach")
    }

    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
        //Timber.i("called OnInflate")
    }

    override fun onPause() {
        super.onPause()
        //Timber.i("called OnPause")
    }

    override fun onResume() {
        super.onResume()
        //Timber.i("called OnResume")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //Timber.i("called OnSaveInstanceState")
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        //Timber.i("called OnAttachFragment")
    }

    override fun onStart() {
        super.onStart()
        //Timber.i("called OnStart")
    }

    override fun onStop() {
        super.onStop()
        //Timber.i("called OnStop")
    }
}