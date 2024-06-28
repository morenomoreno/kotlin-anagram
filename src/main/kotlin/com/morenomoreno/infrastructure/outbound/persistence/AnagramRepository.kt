package com.morenomoreno.infrastructure.outbound.persistence

interface AnagramRepository {
    fun add(vararg str: String)
    fun findAll(): Set<String>
}