package com.knasirayaz.nanohealthsuitedemo.domain.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {

    @JvmName("loadImage1")
    fun ImageView.loadImage(imageUrl: String?, placeholder: Int? = null) {
        Glide.with(context)
            .load(imageUrl)
            .placeholder(placeholder ?: android.R.color.darker_gray)
            .into(this)
    }

    @JvmName("loadImage1")
    fun ImageView.loadImage(imageUrl: Int) {
        Glide.with(context)
            .load(imageUrl)
            .into(this)
    }

}