package com.bodakesatish.clinic.ui.addpatientcheckup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bodakesatish.clinic.databinding.FragmentAddOrUpdatePatientCheckUpBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentAddOrUpdatePatientCheckup : Fragment() {

    private var _binding: FragmentAddOrUpdatePatientCheckUpBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: AddOrUpdatePatientCheckupViewModel by viewModels()

    private val tag = this.javaClass.simpleName


//    val args : FragmentAddOrUpdateCustomerArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddOrUpdatePatientCheckUpBinding.inflate(inflater, container, false)
        val root: View = binding.root
//        args.customer?.let {
//            viewModel.customer = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initHeader()
        initListeners()
        initObservers()
        initData()

    }

    private fun initHeader() {

    }

    private fun initListeners() {
        // Update ViewModel when input fields change
        binding.evPatientSymptoms.editText?.doAfterTextChanged { editable ->
            viewModel.checkup.symptoms = editable?.toString() ?: ""
        }
        binding.evPatientMedicines.editText?.doAfterTextChanged { editable ->
            viewModel.checkup.medicines = editable?.toString() ?: ""
        }
        binding.evOtherDetails.editText?.doAfterTextChanged { editable ->
            viewModel.checkup.otherDetails = editable?.toString() ?: ""
        }
        binding.btnAdd.setOnClickListener {
            if(binding.evPatientSymptoms.editText.toString().isEmpty()) {
                showSnackBar("Enter Symptoms")
            } else if(binding.evPatientMedicines.editText.toString().isEmpty()) {
                showSnackBar("Enter Medicines")
            } else {
                viewModel.addOrUpdatePatientCheckup()
            }
        }
        viewModel.checkupResponse.observe(viewLifecycleOwner) {
            showSnackBar("Checkup added successfully")
            navigateToCustomerListScreen()
        }

        // This callback will only be called when FragmentAddOrUpdateCustomer is at least Started.
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // Handle the back button event
            // e.g., navigate to the previous screen or pop the back stack
            navigateToCustomerListScreen()
        }

        // You can enable/disable the callback based on certain conditions
        // callback.isEnabled = someCondition
    }

    private fun initObservers() {

    }

    private fun initData() {
//        binding.evCustomerName.editText?.setText(viewModel.customer.name)
//        binding.evCustomerPhone.editText?.setText(viewModel.customer.phone.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(tag , "$tag->onDestroyView")
        _binding = null
    }

    private fun navigateToCustomerListScreen() {
        findNavController().popBackStack()
    }

    private fun showSnackBar(message : String) {
        Snackbar.make(
            requireView(),
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

}