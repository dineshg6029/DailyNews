package com.demo.dailynews.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView

import androidx.test.espresso.NoMatchingViewException

import androidx.test.espresso.ViewAssertion


class RecyclerViewIsNotEmptyAssertion : ViewAssertion {
    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        assert(adapter!!.itemCount>0)
    }
}