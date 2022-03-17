package com.demo.dailynews.data

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ErrorTest{

    private val message = "error message"
    private var noInternetConnectionError: Error = Error(NO_INTERNET_CONNECTION,message)
    private var networkError: Error = Error(NETWORK_ERROR,message)
    private var defaultError: Error = Error(DEFAULT_ERROR,message)
    var fieldsError: Error = Error(CHECK_YOUR_FIELDS,message)

    @Test
    fun testErrors(){
        assertThat(noInternetConnectionError.code,`is`(NO_INTERNET_CONNECTION))
        assertThat(networkError.code,`is`(NETWORK_ERROR))
        assertThat(defaultError.code,`is`(DEFAULT_ERROR))
        assertThat(fieldsError.code,`is`(CHECK_YOUR_FIELDS))
        assertThat(noInternetConnectionError.description,`is`(message))
    }


}