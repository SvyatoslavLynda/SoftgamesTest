package com.example.softgamestest

import java.util.*

object FormService {
    private const val SECONDS_IN_YEAR = 60 * 60 * 24 * 365

    fun makeFullName(firstName: String?, lastName: String?): String {
        if (firstName?.isEmpty() == true && lastName?.isEmpty() == true) {
            return "Invalid first name and last name"
        } else if (firstName?.isEmpty() == true) {
            return "Invalid first name"
        } else if (lastName?.isEmpty() == true) {
            return "Invalid last name"
        }

        return "$firstName $lastName"
    }

    fun calculateUserAge(dateOfBirthday: Date?): String {
        if (dateOfBirthday == null) {
            return "Invalid date of birthday"
        }

        val age = (Date().time - dateOfBirthday.time) / 1000 / SECONDS_IN_YEAR

        return age.toString()
    }
}