package com.demo.dailynews.common

import org.junit.Assert
import org.junit.Test

class UtilitiesKtTest {

    @Test
    fun updateDateTime() {
        val input = "2022-03-16T09:37:22.1267498Z"
        val expectedOutput = "2022-03-16 09:37:22"
        val output = updateDateTime(input)
        Assert.assertEquals(expectedOutput, output)
    }
}