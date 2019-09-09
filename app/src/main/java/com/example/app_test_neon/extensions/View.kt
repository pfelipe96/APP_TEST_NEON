package com.example.app_test_neon.extensions

import android.view.View

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}