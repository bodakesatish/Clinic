package com.bodakesatish.clinic.ui.add

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bodakesatish.clinic.domain.model.Patient
import com.bodakesatish.clinic.domain.usecases.AddOrUpdatePatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddOrUpdatePatientViewModel @Inject constructor(
    private val addOrUpdateCustomerUseCase: AddOrUpdatePatientUseCase
) : ViewModel() {

    private val tag = this.javaClass.simpleName

    var customer = Patient()

    val customerResponse = MutableLiveData<Boolean>()

    init {
        Log.d(tag, "$tag->init")
    }

    fun addOrUpdatePatient() {
        Log.d(tag, "In $tag addOrUpdatePatient")
        viewModelScope.launch(Dispatchers.IO) {
            val id = addOrUpdateCustomerUseCase.invoke(customer)
            Log.d(tag, "In $tag $id")
            viewModelScope.launch(Dispatchers.Main) {
                customerResponse.value = true
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(tag , "$tag->onCleared")
    }

}