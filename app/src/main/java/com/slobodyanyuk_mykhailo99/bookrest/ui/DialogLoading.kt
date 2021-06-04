package com.slobodyanyuk_mykhailo99.bookrest.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.slobodyanyuk_mykhailo99.bookrest.R

class DialogLoading(private val activity: Activity) {
    private lateinit var dialog: Dialog
    private lateinit var animation: AnimationDrawable
    @SuppressLint("InflateParams")
    fun startLoadingDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater: LayoutInflater = activity.layoutInflater
        val view: View = inflater.inflate(R.layout.dialog_loading, null)
        val loading = view.findViewById<ImageView>(R.id.iv_loading_animation)
        animation = loading.drawable as AnimationDrawable
        builder.setView(view)
            .setCancelable(true)
        dialog = builder.create().also {
            it.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
            it.window?.setWindowAnimations(R.style.DialogSliding)
            animation.start()
            it.show()
        }
    }
    fun dismissDialog() {
        dialog.dismiss()
        animation.stop()
    }
}