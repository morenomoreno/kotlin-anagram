package com.morenomoreno.infrastructure.inbound.cli

import com.morenomoreno.application.AnagramService
import com.morenomoreno.application.impl.AnagramServiceImpl
import com.morenomoreno.infrastructure.outbound.persistence.impl.InMemoryRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.Scanner
import kotlin.test.assertTrue
import com.github.stefanbirkner.systemlambda.SystemLambda.catchSystemExit

class CommandLineInterfaceIntegrationTest {

    private lateinit var repository: InMemoryRepository
    private lateinit var service: AnagramService
    private lateinit var outputStream: ByteArrayOutputStream

    @BeforeEach
    fun setUp() {
        repository = InMemoryRepository()
        service = AnagramServiceImpl(repository)
        outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
    }

    private fun simulateUserInput(vararg inputs: String) {
        val input = inputs.joinToString("\n")
        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)
    }

    @Test
    fun run_whenComparingTwoAnagrams_shouldReturnTrueMessage() {
        catchSystemExit {
            simulateUserInput("1", "listen", "silent", "", "3")
            val scanner = Scanner(System.`in`)
            val cli = CommandLineInterface(scanner, service)

            cli.run()

            val output = outputStream.toString()
            assertTrue(output.contains("'listen' and 'silent' are anagrams."))
        }
    }
 @Test
    fun run_whenComparingTwoTextsNotAnagrams_shouldReturnFalseMessage() {
        catchSystemExit {
            simulateUserInput("1", "listen", "silent2", "", "3")
            val scanner = Scanner(System.`in`)
            val cli = CommandLineInterface(scanner, service)

            cli.run()

            val output = outputStream.toString()
            assertTrue(output.contains("'listen' and 'silent2' not are anagrams."))
        }
    }

    @Test
    fun run_whenComparingTwoAnagramsThenCompareWithHistoryAndExit_shouldReturnTrueMessageAndHistoryMessage() {
        catchSystemExit {
            simulateUserInput("1", "listen", "silent", "", "2", "listen", "", "3")
            val scanner = Scanner(System.`in`)
            val cli = CommandLineInterface(scanner, service)

            cli.run()

            val output = outputStream.toString()
            assertTrue(output.contains("'listen' and 'silent' are anagrams."))
            assertTrue(output.contains("'listen' is an anagram of [silent]"))
        }
    }

    @Test
    fun run_whenInputInvalidChoice_shouldPrintInvalidChoiceMsg() {
        catchSystemExit {
            simulateUserInput("4", "", "3")
            val scanner = Scanner(System.`in`)
            val cli = CommandLineInterface(scanner, service)

            cli.run()

            val output = outputStream.toString()
            assertTrue(output.contains("Invalid choice. Please try again."))
        }
    }

    @Test
    fun run_whenInputExitChoice_shouldPrintExitMsg() {
        catchSystemExit {
            simulateUserInput("3")
            val scanner = Scanner(System.`in`)
            val cli = CommandLineInterface(scanner, service)

            cli.run()

            val output = outputStream.toString()
            assertTrue(output.contains("Exiting..."))
        }
    }
}