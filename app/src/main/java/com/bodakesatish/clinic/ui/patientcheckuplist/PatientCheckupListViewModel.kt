package com.bodakesatish.clinic.ui.patientcheckuplist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bodakesatish.clinic.domain.model.PatientCheckUp
import com.bodakesatish.clinic.domain.usecases.GetPatientCheckupListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientCheckupListViewModel @Inject constructor(
    private val getPatientCheckupListUseCase: GetPatientCheckupListUseCase
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is PatientCheckupListViewModel Fragment"
    }
    val text: LiveData<String> = _text

    private val tag = this.javaClass.simpleName

    private val _patientCheckupList = MutableStateFlow<List<PatientCheckUp>>(emptyList())
    val patientCheckupList: StateFlow<List<PatientCheckUp>> = _patientCheckupList.asStateFlow()

    init {
        Log.d(tag, "$tag->init")
    }

    fun getPatientCheckupList() {
        Log.d(tag, "$tag->getPatientCheckupList")
        viewModelScope.launch(Dispatchers.IO) {

            getPatientCheckupListUseCase.invoke().collect { list ->
                _patientCheckupList.value = list
                Log.d(tag, "In $tag $list")
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(tag , "$tag->onCleared")
    }
}