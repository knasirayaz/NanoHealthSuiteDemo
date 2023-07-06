package com.knasirayaz.nanohealthsuitedemo.domain.common

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

enum class SnackBarTypes {
    SUCCESS,
    ALERT,
    ERROR,
}

fun showToast(v: View, message: String, type: SnackBarTypes) {
    val snackBarView = Snackbar.make(v, message, Snackbar.LENGTH_LONG)
    snackBarView.animationMode = BaseTransientBottomBar.ANIMATION_MODE_FADE
    snackBarView.show()
}

inline fun <reified T : Any> Activity.launchActivity(finishActivity: Boolean = true) {
    startActivity(Intent(this, T::class.java))
    if (finishActivity) {
        finish()
    }
}



inline fun <reified T : Any> Activity.launchActivity(
    finishActivity: Boolean = true,
    bundle: Bundle
) {
    val intent = Intent(this, T::class.java)
    intent.putExtras(bundle)
    startActivity(intent)
    if (finishActivity) {
        finish()
    }
}