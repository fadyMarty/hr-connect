package com.hrconnect.android.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import okhttp3.Interceptor
import okhttp3.Response

class CacheInterceptor(
    private val context: Context,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val cache = request.header("X-Cache") == "true"
        if (!cache) {
            return chain.proceed(request)
        }
        return if (hasNetwork()) {
            chain.proceed(request).newBuilder()
                .header("Cache-Control", "public, no-cache")
                .build()
        } else {
            val maxStale = 60 * 60 * 24 * 7
            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .build()
            chain.proceed(request)
        }
    }

    private fun hasNetwork(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}