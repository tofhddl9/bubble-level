package com.lgtm.bubble_level.util

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

fun Context.toDp(px: Number): Int {
    return (px.toFloat() / (resources.displayMetrics.densityDpi.toFloat().div(DisplayMetrics.DENSITY_DEFAULT))).toInt()
}

fun Number.pxToDp(context: Context) = context.toDp(this)


fun Context.toPx(dp: Number): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics).toInt()
}

fun Number.toPx(context: Context) = context.toPx(this)