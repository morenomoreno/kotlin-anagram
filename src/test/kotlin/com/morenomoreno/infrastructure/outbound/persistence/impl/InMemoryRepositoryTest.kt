package com.morenomoreno.infrastructure.outbound.persistence.impl

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class InMemoryRepositoryTest {
    private val sut = InMemoryRepository()

    @Test
    fun add_whenMultipleTexts_shouldBeAdded(){
        sut.add("word1", "word2", "word3")

        val allWords: Set<String> = sut.findAll()

        assertEquals(3, allWords.size)
        assertTrue(allWords.contains("word1"))
        assertTrue(allWords.contains("word2"))
        assertTrue(allWords.contains("word3"))
    }

    @Test
    fun add_whenDuplicateTexts_ShouldNotAddDuplicates() {
        sut.add("text1", "text1", "text2")

        val allTexts: Set<String> = sut.findAll()

        assertEquals(2, allTexts.size)
        assertTrue(allTexts.contains("text1"))
        assertTrue(allTexts.contains("text2"))
    }
}