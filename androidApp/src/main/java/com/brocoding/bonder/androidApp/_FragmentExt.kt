package com.brocoding.bonder.androidApp

import androidx.annotation.ColorRes
import androidx.compose.ui.graphics.Color
import androidx.fragment.app.Fragment

internal inline fun <reified T> Fragment.getFragmentArgument(key: String) =
    requireArguments().get(key) as? T ?: throw WrongFragmentArgumentTypeException(key, T::class.java)

internal class WrongFragmentArgumentTypeException(
    key: String,
    type: Class<*>
) : RuntimeException("Fragment argument with the key: $key does not match the type: $type")

fun Fragment.getColor(@ColorRes colorId: Int): Color = Color(requireContext().getColor(colorId))

fun Fragment.getColorInt(@ColorRes colorId: Int): Int = requireContext().getColor(colorId)

