package com.example.softgamestest

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object FormDataParser {
    private const val DATE_PATTERN = "yyyy-mm-dd"

    fun tryParseDate(dateOfBirthday: String?): Date? {
        if (dateOfBirthday?.isEmpty() == null) {
            return null
        }

        return try {
            val formatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
            formatter.parse(dateOfBirthday)
        } catch (e: ParseException) {
            null
        }
    }
}