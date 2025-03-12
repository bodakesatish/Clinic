package com.bodakesatish.clinic.ui.patientcheckuplist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bodakesatish.clinic.R
import com.bodakesatish.clinic.databinding.FragmentAddOrUpdatePatientCheckUpBinding
import com.bodakesatish.clinic.databinding.FragmentCheckupListBinding
import com.bodakesatish.clinic.databinding.FragmentGalleryBinding
import com.bodakesatish.clinic.ui.patientcheckuplist.adapter.PatientCheckupListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PatientCheckupListFragment : Fragment() {

    private var _binding: FragmentCheckupListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: PatientCheckupListViewModel by viewModels()

    private var patientAdapter : PatientCheckupListAdapter = PatientCheckupListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckupListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListeners()
        initObservers()
        initData()
        onBackPressed()
    }

    private fun onBackPressed() {
        // This callback will only be called when FragmentCustomerList is at least Started.
        val callback = requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            // Handle the back button event
            // e.g., navigate to the previous screen or pop the back stack
            requireActivity().finish()
        }

        // You can enable/disable the callback based on certain conditions
        // callback.isEnabled = someCondition
    }

    private fun initView() {
//        binding.headerGeneric.tvHeader.text = "List of Customers"
//        binding.headerGeneric.btnBack.setImageResource(R.drawable.ic_menu_24)
    }

    private fun initListeners() {
        binding.btnNewPatient.setOnClickListener {
            findNavController().navigate(R.id.nav_add_or_update_checkup)
        }
        patientAdapter.setOnClickListener {
//            val action = FragmentCustomerListDirections.actionFragmentCustomerListToFragmentAddOrUpdateCustomer(it)
//            findNavController().navigate(action)
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.patientCheckupList.collect { data ->
                    Log.d(tag, "$tag->initObservers collect->patientsCheckUp")
                    // Update UI with the received data
                    patientAdapter.setData(data)
                }
            }
        }
    }

    private fun initData() {
        binding.rvCustomerList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvCustomerList.adapter = patientAdapter
        binding.rvCustomerList.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPatientCheckupList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}