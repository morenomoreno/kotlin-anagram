package com.morenomoreno.domain


object AnagramChecker {
    fun areAnagrams(str1: String, str2: String): Boolean {
        if (str1.length != str2.length)
            return false

        val str1Count = str1.groupingBy { it }.eachCount()
        val str2Count = str2.groupingBy { it }.eachCount()

        return str1Count == str2Count
    }
}