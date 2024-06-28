package com.morenomoreno.application

interface AnagramService {
    fun checkTwoTextAreAnagrams(str1: String, str2: String): Boolean
    fun getTextAnagramsFromHistory(str: String): List<String>
}