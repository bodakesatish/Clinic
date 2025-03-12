package com.bodakesatish.clinic.ui.addpatientcheckup

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bodakesatish.clinic.domain.model.Checkup
import com.bodakesatish.clinic.domain.usecases.AddOrUpdateCheckUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddOrUpdatePatientCheckupViewModel @Inject constructor(
    private val addOrUpdateCheckUpUseCase: AddOrUpdateCheckUpUseCase
) : ViewModel() {

    private val tag = this.javaClass.simpleName

    var checkup = Checkup().apply {
        patientId = 1
    }

    val checkupResponse = MutableLiveData<Boolean>()

    init {
        Log.d(tag, "$tag->init")
    }

    fun addOrUpdatePatientCheckup() {
        Log.d(tag, "In $tag addOrUpdatePatientCheckup")
        viewModelScope.launch(Dispatchers.IO) {
            val id = addOrUpdateCheckUpUseCase.invoke(checkup)
            Log.d(tag, "In $tag $id")
            viewModelScope.launch(Dispatchers.Main) {
                checkupResponse.value = true
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i(tag , "$tag->onCleared")
    }

}