package com.morenomoreno.infrastructure.inbound.cli


import com.morenomoreno.application.AnagramService
import java.util.Scanner
import kotlin.system.exitProcess

class CommandLineInterface(
    private val scanner: Scanner,
    private val service: AnagramService,
) {

    fun run() {
        while (true) {
            showMenu()
            when (askForInput("Enter your choice: ")) {
                "1" -> compareTwoAnagrams()
                "2" -> checkAnagramInHistory()
                "3" -> exit()
                else -> println("Invalid choice. Please try again.")
            }

            askForInput("Press Enter to continue...")
            println("------------------------------")
        }
    }

    private fun showMenu() {
        println("Select an option:")
        println("1. Compare two anagrams")
        println("2. Check anagram in history")
        println("3. Exit")
    }

    private fun exit() {
        println("Exiting...")
        exitProcess(0)
    }

    private fun compareTwoAnagrams() {
        val str1 = askForInput("Enter the first string: ")
        val str2 = askForInput("Enter the second string: ")

        if (service.checkTwoTextAreAnagrams(str1, str2))
            println("'$str1' and '$str2' are anagrams.")
        else
            println("'$str1' and '$str2' are not anagrams.")
    }

    private fun checkAnagramInHistory() {
        val str = askForInput("Enter the string to check: ")

        service.getTextAnagramsFromHistory(str).let { anagrams ->
            if (anagrams.isEmpty()) println("No anagram of '$str' found in history.")
            else println("'$str' is an anagram of $anagrams")
        }
    }

    private fun askForInput(str: String): String {
        print(str)
        return scanner.nextLine().trim()
    }
}