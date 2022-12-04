package com.example.softgamestest

import org.junit.Assert.*
import org.junit.Test
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class FormDataParserTest {
    @Test
    fun tryParseDateWithArgumentNullValue() {
        val expected = null
        val actual = FormDataParser.tryParseDate(dateOfBirthday = null)
        assertEquals(expected, actual)
    }

    @Test
    fun tryParseDateWithArgumentEmptyStringValue() {
        val expected = null
        val actual = FormDataParser.tryParseDate(dateOfBirthday = "")
        assertEquals(expected, actual)
    }

    @Test
    fun tryParseDateWithArgumentStringNotDateValue() {
        val expected = null
        val actual = FormDataParser.tryParseDate(dateOfBirthday = "qwerty")
        assertEquals(expected, actual)
    }

    @Test
    fun tryParseDateWithArgumentIncorrectDateFormatValue() {
        val expected = null
        val actual = FormDataParser.tryParseDate(dateOfBirthday = "12.12.2001")
        assertEquals(expected, actual)
    }

    @Test
    fun tryParseDateWithArgumentCorrectDateFormatValue() {
        val dayOfBirthday = "1993-01-30"
        val formatter = SimpleDateFormat("yyyy-mm-dd", Locale.getDefault())
        val expected = try {
            formatter.parse(dayOfBirthday)
        } catch (e: ParseException) {
            null
        }

        val actual = FormDataParser.tryParseDate(dateOfBirthday = dayOfBirthday)
        assertEquals(expected, actual)
    }
}