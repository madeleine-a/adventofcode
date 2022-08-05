package se.aoc.seventeen

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class Day4Test{
    val d = Day4()
    @ParameterizedTest
    @CsvSource("aa bb cc dd ee, true", "aa bb cc dd aa, false", "aa bb cc dd aaa, true")
    fun isValid(phrase: String, valid: Boolean){
        assertEquals(valid, d.isValid(phrase.split(" ")))
    }

    @ParameterizedTest
    @CsvSource("abcde fghij, true", "abcde xyz ecdab, false", "a ab abc abd abf abj, true",
    "iiii oiii ooii oooi oooo, true", "oiii ioii iioi iiio, false")
    fun isValidAnagrams(phrase: String, valid: Boolean){
        assertEquals(valid, d.isValidAnagrams(phrase.split(" ")))
    }


}