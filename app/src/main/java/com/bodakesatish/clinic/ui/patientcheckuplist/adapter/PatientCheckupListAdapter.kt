package com.bodakesatish.clinic.ui.patientcheckuplist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bodakesatish.clinic.databinding.ListRowPatientBinding
import com.bodakesatish.clinic.domain.model.PatientCheckUp

class PatientCheckupListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemList: List<PatientCheckUp> = emptyList()
    var onBatchSelected: ((PatientCheckUp) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ListRowPatientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PatientCheckupViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {

            is PatientCheckupViewHolder -> {
                holder.bind(itemList[position], position)
            }

        }
    }

    fun setData(data: List<PatientCheckUp>) {
        itemList = data
        notifyItemRangeChanged(0, data.size)
    }

    fun setOnClickListener(onBatchSelected: ((PatientCheckUp)) -> Unit) {
        this.onBatchSelected = onBatchSelected
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class PatientCheckupViewHolder(val binding: ListRowPatientBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: PatientCheckUp, position: Int) {

            binding.tvNumber.text = "${position + 1}."
            binding.tvCustomerName.text = "${data.patientName}"
            binding.tvCustomerPhone.text = "${data.medicines}"

            binding.root.setOnClickListener {
                onBatchSelected?.invoke(data)
            }
        }

    }
}