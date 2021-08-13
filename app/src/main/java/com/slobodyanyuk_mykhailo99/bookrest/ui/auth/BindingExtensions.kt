package com.slobodyanyuk_mykhailo99.bookrest.ui.auth

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object BindingExtensions{
    @JvmStatic
    @BindingAdapter("errorText")
    fun TextInputLayout.setErrorMessage(errorMessage: String?) {
        this.isErrorEnabled = true
        this.error = errorMessage
    }


}