package com.bodakesatish.clinic.ui.patientlist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bodakesatish.clinic.databinding.ListRowPatientBinding
import com.bodakesatish.clinic.domain.model.Patient

class PatientListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemList: List<Patient> = emptyList()
    var onBatchSelected: ((Patient) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ListRowPatientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PatientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {

            is PatientViewHolder -> {
                holder.bind(itemList[position], position)
            }

        }
    }

    fun setData(data: List<Patient>) {
        itemList = data
        notifyItemRangeChanged(0, data.size)
    }

    fun setOnClickListener(onBatchSelected: ((Patient)) -> Unit) {
        this.onBatchSelected = onBatchSelected
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class PatientViewHolder(val binding: ListRowPatientBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Patient, position: Int) {

            binding.tvNumber.text = "${position + 1}."
            binding.tvCustomerName.text = "${data.firstName}"
            binding.tvCustomerPhone.text = "${data.lastName}"

            binding.root.setOnClickListener {
                onBatchSelected?.invoke(data)
            }
        }

    }
}