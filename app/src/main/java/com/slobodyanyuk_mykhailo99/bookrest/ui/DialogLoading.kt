package com.slobodyanyuk_mykhailo99.bookrest.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import com.slobodyanyuk_mykhailo99.bookrest.R


class DialogLoading(private val activity: Activity) {
    private lateinit var dialog: Dialog
    private lateinit var animation: AnimationDrawable
    private lateinit var image: ImageView
    @SuppressLint("InflateParams")
    fun startLoadingDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater: LayoutInflater = activity.layoutInflater
        val view: View = inflater.inflate(R.layout.dialog_loading, null)
        image = view.findViewById<ImageView>(R.id.iv_loading_animation)

        val rotate = RotateAnimation(
            0f,
            180f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotate.duration = 10000
        rotate.interpolator = LinearInterpolator()
        builder.setView(view)
            .setCancelable(true)
        dialog = builder.create().also {
            it.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
            it.window?.setWindowAnimations(R.style.DialogSliding)
            image.startAnimation(rotate)
            it.show()
        }
    }
    fun dismissDialog() {
        dialog.dismiss()
    }
}