package com.example.softgamestest

import java.util.*

object FormService {
    private const val SECONDS_IN_YEAR = 60 * 60 * 24 * 365

    fun makeFullName(firstName: String?, lastName: String?): String {
        if (firstName?.isEmpty() != false && lastName?.isEmpty() != false) {
            return "Invalid first name and last name"
        } else if (firstName?.isEmpty() != false) {
            return "Invalid first name"
        } else if (lastName?.isEmpty() != false) {
            return "Invalid last name"
        }

        return "$firstName $lastName"
    }

    fun calculateUserAge(dateOfBirthday: Date?): String {
        if (dateOfBirthday == null) {
            return "Invalid date of birthday: please pick a date"
        }

        val now = Date()

        if (dateOfBirthday.after(now)) {
            return "Invalid date of birthday: date can't be bigger than today's date"
        }

        val age = (now.time - dateOfBirthday.time) / 1000 / SECONDS_IN_YEAR

        return age.toString()
    }
}