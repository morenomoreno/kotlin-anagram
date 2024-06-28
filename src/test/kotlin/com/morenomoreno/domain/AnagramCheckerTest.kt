package com.morenomoreno.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class AnagramCheckerTest {

    @ParameterizedTest
    @CsvSource(
        "listen, silent, true",
        "evil, vile, true",
        "one, two, false",
        "one, three, false",
        "restful, fluster, true",
        "fluster, restful, true",
        "anagram, nagaram, true",
        "aa, aaa, false",
        "aaa, aaa, true",
    )
    fun givenStrings_whenChecked_thenIdentifyIfAnagrams(str1: String, str2: String, expectedResult: Boolean) {
        assertEquals(expectedResult, AnagramChecker.areAnagrams(str1, str2))
    }
}