package com.reynaldiwijaya.sportdbgits.utils

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.material.snackbar.Snackbar
import com.reynaldiwijaya.sportdbgits.R
import com.squareup.picasso.Picasso

fun emptyString() = ""

fun ImageView.loadImage(url : String, context: Context) {
    Picasso.get()
        .load(url)
        .placeholder(createCircularProgressDrawable(context))
        .error(R.drawable.ic_broken_image)
        .into(this)

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

fun createCircularProgressDrawable(context: Context) : CircularProgressDrawable {
    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 4f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    return circularProgressDrawable
}