package com.example.app_test_neon.extensions

import android.os.Build
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: Int?) {
    url?.let {
        if (Build.VERSION.SDK_INT >= 21) {
            Glide.with(this.context)
                .load(url)
                .into(this)
        } else {
            Glide.with(this)
                .load(url)
                .into(this)
        }
    }
}

fun ImageView.loadImage(url: String?) {
    url?.let {
        if (it.isNotEmpty()) {
            if (Build.VERSION.SDK_INT >= 21) {
                Glide.with(this.context)
                    .load(url)
                    .into(this)
            } else {
                Glide.with(this)
                    .load(url)
                    .into(this)
            }
        }
    }
}