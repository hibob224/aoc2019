package utils

fun Boolean?.orFalse(): Boolean = this ?: false

fun Boolean?.orTrue(): Boolean = this ?: true

infix fun <T> Boolean.then(param: T): T? = if (this) param else null
