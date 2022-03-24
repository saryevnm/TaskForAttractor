package com.it.taskforattractor.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.LayoutRes
import com.bumptech.glide.Glide


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun <T> ImageView.loadImage(uri: T) {
    Glide.with(this)
        .load(uri)
        .into(this)
}

fun Context.showMessage(message: String?) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.INVISIBLE
    }

var View.invisible: Boolean
    get() = visibility == View.INVISIBLE
    set(value) {
        visibility = if (value) View.GONE else View.VISIBLE
    }