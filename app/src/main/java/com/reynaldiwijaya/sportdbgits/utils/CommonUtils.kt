package com.reynaldiwijaya.sportdbgits.utils

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.reynaldiwijaya.sportdbgits.R
import com.squareup.picasso.Picasso

fun emptyString() = ""

fun ImageView.loadImage(url : String) {
    Picasso.get().load(url).into(this)
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun ImageButton.setFavoriteImage(context : Context, isFavorite : Boolean) {
    this.setImageDrawable(ContextCompat.getDrawable(context,
        if (isFavorite) R.drawable.ic_star_favorite else R.drawable.ic_star_default))
}

fun View.onClick(action : (view : View) -> Unit) {
    this.setOnClickListener(action)
}

fun showMessage(view: View, message : String) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}