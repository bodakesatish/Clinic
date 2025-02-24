package com.bodakesatish.clinic.login

import android.util.Log
import android.util.Patterns
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bodakesatish.clinic.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModelLogin @Inject constructor(
//    private val loginUseCase: LoginUseCase,
) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginWithPasswordFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private val tag = this.javaClass.simpleName

    init {
        Log.d(tag, "In $tag init")
    }

    fun onUserNameChanged(username: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = UserNameFormState(isDataValid = false,usernameError = R.string.invalid_username)
        } else {
            _loginForm.value = UserNameFormState(isDataValid = true)
        }
    }

    fun onPasswordChanged(password: String) {
        if (!isPasswordValid(password)) {
            _loginForm.value = PasswordFormState(isDataValid = false, passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = PasswordFormState(isDataValid = true, passwordError = R.string.invalid_password)
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else if(username.isDigitsOnly() && username.length == 10) {
            return true
        } else {
            false
        }
    }

    fun login(username: String, password: String) {
        Log.d(tag, "In $tag login")
        onUserNameChanged(username)
        onPasswordChanged(password)
        if ((isUserNameValid(username) && isPasswordValid(password)) && (username == "8600381118" && password == "123456"))  {
            _loginResult.value = LoginResult(true)
        } else {
            _loginResult.value = LoginResult(false)
        }
    }

}