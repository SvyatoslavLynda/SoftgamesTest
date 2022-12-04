package com.example.softgamestest

import org.junit.Assert.*
import org.junit.Test
import java.util.Calendar

class FormDataServiceTest {
    @Test
    fun makeFullNameWithArgumentsNullValues() {
        val expected = "Invalid first name and last name"
        val actual = FormService.makeFullName(null, null)
        assertEquals(expected, actual)
    }

    @Test
    fun makeFullNameWithArgumentFirstNameNullValue() {
        val expected = "Invalid first name"
        val actual = FormService.makeFullName(null, "Robinson")
        assertEquals(expected, actual)
    }

    @Test
    fun makeFullNameWithArgumentLastNameNullValue() {
        val expected = "Invalid last name"
        val actual = FormService.makeFullName("Robyn", null)
        assertEquals(expected, actual)
    }

    @Test
    fun makeFullNameWithArgumentsEmptyValues() {
        val expected = "Invalid first name and last name"
        val actual = FormService.makeFullName("", "")
        assertEquals(expected, actual)
    }

    @Test
    fun makeFullNameWithArgumentFirstNameEmptyValue() {
        val expected = "Invalid first name"
        val actual = FormService.makeFullName("", "Robinson")
        assertEquals(expected, actual)
    }

    @Test
    fun makeFullNameWithArgumentLastNameEmptyValue() {
        val expected = "Invalid last name"
        val actual = FormService.makeFullName("Robyn", "")
        assertEquals(expected, actual)
    }

    @Test
    fun makeFullNameWithValidArguments() {
        val firsName = "Robyn"
        val lastName = "Robinson"
        val expected = "$firsName $lastName"
        val actual = FormService.makeFullName(firsName, lastName)
        assertEquals(expected, actual)
    }

    @Test
    fun calculateUserAgeWithArgumentNullValue() {
        val expected = "Invalid date of birthday: please pick a date"
        val actual = FormService.calculateUserAge(null)
        assertEquals(expected, actual)
    }

    @Test
    fun calculateUserAgeWithArgumentTheSameDateLastYear() {
        val calendar = Calendar.getInstance().apply {
            add(Calendar.YEAR, -1)
        }
        val expected = "1"
        val actual = FormService.calculateUserAge(calendar.time)
        assertEquals(expected, actual)
    }

    @Test
    fun calculateUserAgeWithArgumentWithDayAgoDate() {
        val calendar = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, -1)
        }
        val expected = "0"
        val actual = FormService.calculateUserAge(calendar.time)
        assertEquals(expected, actual)
    }

    @Test
    fun calculateUserAgeWithArgumentWithMonthAgoDate() {
        val calendar = Calendar.getInstance().apply {
            add(Calendar.MONTH, -1)
        }
        val expected = "0"
        val actual = FormService.calculateUserAge(calendar.time)
        assertEquals(expected, actual)
    }

    @Test
    fun calculateUserAgeWithArgumentTheSameDateNextYear() {
        val calendar = Calendar.getInstance().apply {
            add(Calendar.YEAR, 1)
        }
        val expected = "Invalid date of birthday: date can't be bigger than today's date"
        val actual = FormService.calculateUserAge(calendar.time)
        assertEquals(expected, actual)
    }

    @Test
    fun calculateUserAgeWithArgumentWithNextMonthDate() {
        val calendar = Calendar.getInstance().apply {
            add(Calendar.MONTH, 1)
        }
        val expected = "Invalid date of birthday: date can't be bigger than today's date"
        val actual = FormService.calculateUserAge(calendar.time)
        assertEquals(expected, actual)
    }

    @Test
    fun calculateUserAgeWithArgumentWithNextDayDate() {
        val calendar = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, 1)
        }
        val expected = "Invalid date of birthday: date can't be bigger than today's date"
        val actual = FormService.calculateUserAge(calendar.time)
        assertEquals(expected, actual)
    }
}