package com.morenomoreno.infrastructure.outbound.persistence.impl

import com.morenomoreno.infrastructure.outbound.persistence.AnagramRepository

class InMemoryRepository : AnagramRepository {
    private val history: MutableSet<String> = mutableSetOf()

    override fun add(vararg str: String) {
        str.forEach(history::add)
    }

    override fun findAll(): Set<String> {
        return history
    }
}