package com.morenomoreno

import com.morenomoreno.infrastructure.inbound.cli.CommandLineInterface
import com.morenomoreno.infrastructure.outbound.persistence.impl.InMemoryRepository
import com.morenomoreno.application.impl.AnagramServiceImpl
import java.util.Scanner


fun main() {
    val scanner = Scanner(System.`in`)
    val repository = InMemoryRepository()
    val service = AnagramServiceImpl(repository)

    val cli = CommandLineInterface(scanner, service)
    cli.run()
}