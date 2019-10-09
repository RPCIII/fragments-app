package com.example.fragmentsapplication

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object DataBindingAdapters {                //responsible for making calls from framework to dynamically set information
                                            //used in generated java only
    @BindingAdapter("android:src")
    fun setImageResource(imageView: ImageView, resource: Int) {
        imageView.setImageResource(resource)
    }
}