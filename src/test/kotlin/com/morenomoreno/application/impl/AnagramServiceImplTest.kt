package com.morenomoreno.application.impl

import com.morenomoreno.infrastructure.outbound.persistence.AnagramRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class AnagramServiceImplTest {

    private val repository = mock<AnagramRepository>()
    private val sut = AnagramServiceImpl(repository)

    @Test
    fun checkTwoTextAreAnagrams_whenCallValidInputs_shouldAddInRepository() {
        val text1 = "str1"
        val text2 = "str2"

        sut.checkTwoTextAreAnagrams(text1, text2)

        verify(repository).add(text1,text2)
    }

    @Test
    fun checkTwoTextAreAnagrams_whenAreAnagrams_shouldReturnTrue() {
        val result = sut.checkTwoTextAreAnagrams("str1", "st1r")

        assertTrue(result)
    }

    @Test
    fun checkTwoTextAreAnagrams_whenAreNotAnagrams_shouldReturnFalse() {
        val result = sut.checkTwoTextAreAnagrams("str1", "str2")

        assertFalse(result)
    }

    @Test
    fun getTextAnagramsFromHistory_whenTextHasAnagramsInHistory_shouldReturnAnagrams() {
        doReturn(setOf("str1", "st1r", "str2")).whenever(repository).findAll()

        val result = sut.getTextAnagramsFromHistory("1str")

        assertEquals(listOf("str1", "st1r"), result)
    }

    @Test
    fun getTextAnagramsFromHistory_whenTextNoHasAnagramsInHistory_shouldReturnEmptyList() {
        doReturn(setOf("str1", "st1r", "str2")).whenever(repository).findAll()

        val result = sut.getTextAnagramsFromHistory("str3")

        assertEquals(emptyList<String>(), result)
    }
}