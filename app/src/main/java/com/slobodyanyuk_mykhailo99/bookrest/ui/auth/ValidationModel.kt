package com.slobodyanyuk_mykhailo99.bookrest.ui.auth

import android.content.Context
import android.util.Patterns
import java.util.regex.Pattern

data class ValidationModel(val isValid: Boolean, val message: String) {
    companion object {
        val AUTH_PASSWORD_PATTERN: Pattern = Pattern.compile(
            "(?=.*[a-zA-Z])" +
                    "(?=\\S+$)" +            //no white spaces
                    ".{6,15}" +              //at least 6 characters
                    "$"
        )
        const val FAILURE_EMAIL_EMPTY = "Please enter email address"
        const val FAILURE_EMAIL_INCORRECT = "Please check correctness of email address"
        const val FAILURE_PASSWORD_EMPTY = "Please enter password"
        const val FAILURE_PASSWORD_INCORRECT = "Weak password. Allowing a-z, numbers"
        const val FAILURE_CONFIRMATION_PASSWORD_EMPTY = "Please enter password first"
        const val FAILURE_PASSWORD_AFTER_CONFIRMATION = "Your password different from confirmation now"
        const val FAILURE_CONFIRMATION_EMPTY = "Please confirm your password"
        const val FAILURE_CONFIRMATION_INCORRECT = "Passwords should match!"
        const val FAILURE_USERNAME_EMPTY = "Please enter username"
        //const val FAILURE_USERNAME_INCORRECT = "Passwords should be unique! Change it and try again"
        const val SUCCESS = ""
    }
}

fun String.validateEmail(email: Context): ValidationModel {
    return when {
        this.trim().isEmpty() -> {
            ValidationModel(false, ValidationModel.FAILURE_EMAIL_EMPTY)
        }
        (!Patterns.EMAIL_ADDRESS.matcher(this).matches()) -> {
            ValidationModel(false, ValidationModel.FAILURE_EMAIL_INCORRECT)
        }
        else -> {
            ValidationModel(true, ValidationModel.SUCCESS)
        }
    }

}

fun String.validatePassword(password: Context, confirmation: String?): ValidationModel {
    return when {
        this.trim().isEmpty() -> {
             ValidationModel(false, ValidationModel.FAILURE_PASSWORD_EMPTY)
        }
        !ValidationModel.AUTH_PASSWORD_PATTERN.matcher(this).matches() -> {
            ValidationModel(false, ValidationModel.FAILURE_PASSWORD_INCORRECT)
        }
        this != confirmation && confirmation != null -> {
            ValidationModel(false, ValidationModel.FAILURE_PASSWORD_AFTER_CONFIRMATION)
        }
        else -> {
            ValidationModel(true, ValidationModel.SUCCESS)
        }
    }
}

fun String.validateConfirmation(password: String?, confirmation: Context): ValidationModel {
    return when {
        password == null -> {
            ValidationModel(false, ValidationModel.FAILURE_CONFIRMATION_PASSWORD_EMPTY)
        }
        this.trim().isEmpty() -> {
            ValidationModel(false, ValidationModel.FAILURE_CONFIRMATION_EMPTY)
        }
        password != this -> {
            ValidationModel(false, ValidationModel.FAILURE_CONFIRMATION_INCORRECT)
        }
        else -> {
            ValidationModel(true, ValidationModel.SUCCESS)
        }
    }
}
fun String.validateUsername(username: Context): ValidationModel {
    return when {
        this.trim().isEmpty() -> {
            ValidationModel(false, ValidationModel.FAILURE_USERNAME_EMPTY)
        }
        else -> {
            ValidationModel(true, ValidationModel.SUCCESS)
        }
    }
}

