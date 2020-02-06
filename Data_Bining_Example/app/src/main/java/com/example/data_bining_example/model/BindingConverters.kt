package com.example.data_bining_example.model

import android.view.View
import androidx.databinding.BindingConversion


object ConverterUtil {
    @JvmStatic
    fun isZero(number: Int): Boolean = number == 0
}

object BindingConverters {
    @BindingConversion
    @JvmStatic
    fun booleanToVisibility(isNotVisible: Boolean): Int {
        return if (isNotVisible) View.GONE else View.VISIBLE
    }
}