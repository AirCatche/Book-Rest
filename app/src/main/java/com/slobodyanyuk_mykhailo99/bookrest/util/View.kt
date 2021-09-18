package com.slobodyanyuk_mykhailo99.bookrest.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.snackbar(message: String) {
    Snackbar.make(this,message, Snackbar.LENGTH_INDEFINITE).also { snackbar ->
        snackbar.setAction("OK") {
            snackbar.dismiss()
        }.show()
    }
}