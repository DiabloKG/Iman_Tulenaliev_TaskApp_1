package com.example.iman_tulenaliev_taskapp_1.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.loadImage(url: String){
    com.bumptech.glide.Glide
        .with(this)
        .load(url)
        .circleCrop()
        .into(this as ImageView);
}