package com.slobodyanyuk_mykhailo99.bookrest.ui.auth

import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.ImageView
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