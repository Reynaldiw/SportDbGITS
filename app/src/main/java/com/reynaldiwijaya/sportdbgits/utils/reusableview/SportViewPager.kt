package com.reynaldiwijaya.sportdbgits.utils.reusableview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class SportViewPager(context: Context, attributes: AttributeSet) : ViewPager(context, attributes) {
    var swipeEnabled : Boolean = true

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return if (swipeEnabled) {
            super.onTouchEvent(ev)
        } else {
            false
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return if (swipeEnabled) {
            super.onInterceptTouchEvent(ev)
        } else {
            false
        }

    }
}