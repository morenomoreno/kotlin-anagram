package com.morenomoreno.application.impl

import com.morenomoreno.application.AnagramService
import com.morenomoreno.domain.AnagramChecker
import com.morenomoreno.infrastructure.outbound.persistence.AnagramRepository

class AnagramServiceImpl(
    private val repository: AnagramRepository,
) : AnagramService {

    override fun checkTwoTextAreAnagrams(str1: String, str2: String): Boolean {
        repository.add(str1, str2)
        return AnagramChecker.areAnagrams(str1, str2)
    }

    override fun getTextAnagramsFromHistory(str: String): List<String> {
        return repository.findAll().filter { AnagramChecker.areAnagrams(it, str) }
    }
}