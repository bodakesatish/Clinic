package com.bodakesatish.clinic.ui.patientlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bodakesatish.clinic.domain.model.Patient
import com.bodakesatish.clinic.domain.usecases.GetPatientListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientListViewModel @Inject constructor(
    private val getPatientListUseCase: GetPatientListUseCase
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is gallery Fragment"
    }
    val text: LiveData<String> = _text

    private val tag = this.javaClass.simpleName

    private val _patientList = MutableStateFlow<List<Patient>>(emptyList())
    val patientList: StateFlow<List<Patient>> = _patientList.asStateFlow()

    init {
        Log.d(tag, "$tag->init")
    }

    fun getCustomerList() {
        Log.d(tag, "$tag->getCustomerList")
        viewModelScope.launch(Dispatchers.IO) {

            getPatientListUseCase.invoke().collect { list ->
                _patientList.value = list
                Log.d(tag, "In $tag $list")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(tag , "$tag->onCleared")
    }
}