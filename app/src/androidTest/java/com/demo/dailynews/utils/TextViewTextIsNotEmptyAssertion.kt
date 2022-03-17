package com.demo.dailynews.utils

import android.view.View
import android.widget.TextView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion

class TextViewTextIsNotEmptyAssertion :ViewAssertion {
    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        val textView = view as TextView
        assert(!textView!!.text.isNullOrBlank())
    }
}