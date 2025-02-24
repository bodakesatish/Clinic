package com.bodakesatish.clinic.login

/**
 * Data validation state of the login form.
 */
sealed class LoginFormState

data class UserNameFormState(
    val isDataValid: Boolean = false,
    val usernameError: Int? = null
) : LoginFormState()

data class PasswordFormState(
    val isDataValid: Boolean = false,
    val passwordError: Int? = null
) : LoginFormState()

data class FailedLoginFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null
) : LoginFormState()

data class SuccessfulLoginFormState(
    val isDataValid: Boolean = false
) : LoginFormState()