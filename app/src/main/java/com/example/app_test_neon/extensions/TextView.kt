package com.example.app_test_neon.extensions

import android.widget.TextView

fun TextView.color(color: Int){
    setTextColor(context.resources.getColor(color))
}