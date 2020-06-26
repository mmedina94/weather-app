package com.example.astropay.utils

import java.util.*

private val DECIMAL_FORMAT = "%.2f "
private val INTEGER_FORMAT = "%.0f "

fun Boolean.check(assertTrue: (() -> Unit)? = null, assertFalse: (() -> Unit)? = null) {
    if (this) {
        assertTrue?.invoke()
    } else {
        assertFalse?.invoke()
    }
}

fun Double.convertToCelsius() = (this - 273.15).toInt()

fun Double.getDoubleWithTwoDecimalsString(): String {
    return String.format(
        Locale.getDefault(),
        getDecimalFormatString(this),
        this
    )
}


fun getDecimalFormatString(value: Double): String {
    return if (hasDecimalComponents(value)) {
        DECIMAL_FORMAT
    } else INTEGER_FORMAT
}

private fun hasDecimalComponents(value: Double): Boolean {
    return value % 1 != 0.0
}


fun Int.Companion.ZERO_VALUE() = 0