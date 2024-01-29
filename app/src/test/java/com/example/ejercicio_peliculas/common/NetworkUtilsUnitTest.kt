package com.example.ejercicio_peliculas.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import com.example.ejercicio_peliculas.common.NetworkUtils.isInternetAvailable
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NetworkUtilsUnitTest {
    @RelaxedMockK
    private lateinit var mockContext: Context

    @RelaxedMockK
    private lateinit var mockConnectivityManager: ConnectivityManager

    @RelaxedMockK
    private lateinit var mockNetwork: Network

    @RelaxedMockK
    private lateinit var mockNetworkCapabilities: NetworkCapabilities

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `internet is available when wifi is connected`() {
        coEvery { mockConnectivityManager.activeNetwork } returns mockNetwork
        coEvery { mockConnectivityManager.getNetworkCapabilities(mockNetwork)} returns mockNetworkCapabilities
        coEvery { mockNetworkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) } returns true
        val result = isInternetAvailable(mockContext)
        Assert.assertTrue(result)
    }

    @Test
    fun `internet is available when network cellular is connected`() {
        coEvery { mockConnectivityManager.activeNetwork } returns mockNetwork
        coEvery { mockConnectivityManager.getNetworkCapabilities(mockNetwork)} returns mockNetworkCapabilities
        coEvery { mockNetworkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) } returns true
        val result = isInternetAvailable(mockContext)
        Assert.assertTrue(result)
    }


    @Test
    fun `no internet is available`() {
        coEvery {  mockConnectivityManager.activeNetwork } returns null
        val result = isInternetAvailable(mockContext)
        Assert.assertFalse(result)
    }

}