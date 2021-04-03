package com.sharonahamon.petsleuth.ui.instructions

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
import com.sharonahamon.petsleuth.databinding.InstructionsFragmentBinding
import com.sharonahamon.petsleuth.common.PetSleuthViewModel
import timber.log.Timber

class InstructionsFragment : Fragment() {

    private lateinit var viewModel: PetSleuthViewModel

    private lateinit var binding: InstructionsFragmentBinding

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

        binding =
            DataBindingUtil.inflate(inflater, R.layout.instructions_fragment, container, false)

        binding.petSleuthViewModel = viewModel
        binding.lifecycleOwner = this

        binding.instructionsButtonSave.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            savePet(view)
        }

        binding.instructionsButtonList.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            view.findNavController().navigate(R.id.action_instructionsFragment_to_listItemFragment)
        }

        return binding.root
    }

    private fun savePet(view: View) {
        viewModel.savePetFromUserInputToViewModel(
            viewModel.loggedOnUserEmail, // email that was saved on login
            getCityDataFromUserInput(),
            getStateDataFromUserInput(),
            getZipDataFromUserInput(),
            getStatusDataFromUserInput(),
            getSpeciesDataFromUserInput(),
            getSexDataFromUserInput(),
            getBreedDataFromUserInput(),
            viewModel.getToday()
        )

        view.findNavController().navigate(R.id.action_instructionsFragment_to_detailFragment)
    }

    private fun getStatusDataFromUserInput(): String {
        var status = "Lost"
        val checkedId = binding.instructionsRadioPurposeContainer.checkedRadioButtonId

        // Do nothing if nothing is checked (id == -1)
        if (-1 != checkedId) {
            when (checkedId) {
                R.id.instructions_radio_purpose_found -> status = "Found"
                R.id.instructions_radio_purpose_lost -> status = "Lost"
            }
        }

        Timber.i("status=%s", status)
        return status
    }

    private fun getSexDataFromUserInput(): String {
        var sex = "Male"
        val checkedId = binding.instructionsRadioSexContainer.checkedRadioButtonId

        // Do nothing if nothing is checked (id == -1)
        if (-1 != checkedId) {
            when (checkedId) {
                R.id.instructions_radio_sex_male -> sex = "Male"
                R.id.instructions_radio_sex_female -> sex = "Female"
            }
        }

        Timber.i("sex=%s", sex)
        return sex
    }

    private fun getBreedDataFromUserInput(): String {
        val breed = binding.instructionsAnswerBreed.text.toString()

        Timber.i("breed=%s", breed)
        return breed
    }

    private fun getZipDataFromUserInput(): String {
        val zip = binding.instructionsAnswerLocationZip.text.toString()

        Timber.i("zip=%s", zip)
        return zip
    }

    private fun getStateDataFromUserInput(): String {
        val state = binding.instructionsAnswerLocationState.text.toString()

        Timber.i("state=%s", state)
        return state
    }

    private fun getCityDataFromUserInput(): String {
        val city = binding.instructionsAnswerLocationCity.text.toString()

        Timber.i("city=%s", city)
        return city
    }

    private fun getSpeciesDataFromUserInput(): String {
        var species = ""
        val checkedId = binding.instructionsRadioSpeciesContainer.checkedRadioButtonId

        // Do nothing if nothing is checked (id == -1)
        if (-1 != checkedId) {
            when (checkedId) {
                R.id.instructions_radio_species_dog -> species = "Dog"
                R.id.instructions_radio_species_cat -> species = "Cat"
            }
        }

        Timber.i("species=%s", species)
        return species
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