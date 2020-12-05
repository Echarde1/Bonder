package com.brocoding.bonder.androidApp

import android.content.Context
import androidx.annotation.ColorRes
import androidx.compose.ui.graphics.Color

fun Context.getColorInt(@ColorRes colorId: Int): Int = getColor(colorId)

fun Context.getColor(@ColorRes colorId: Int): Color = Color(getColor(colorId))