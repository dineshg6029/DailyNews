package com.java.predictweather.data.retrofit

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class NetworkUtils @Inject constructor(@ActivityContext private val mContext: Context){

    fun isConnected():Boolean{
        val connectivityManager = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var isConnected = false
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        capabilities?.run {
            if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                isConnected = true
            } else if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                isConnected = true
            }
        }
        return isConnected
    }

}
