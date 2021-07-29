package com.adesso.ghrepolist.internal.injection.module

import com.adesso.ghrepolist.BuildConfig
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import retrofit2.Retrofit

class NetworkModuleTest {

    @MockK
    internal lateinit var networkModule: NetworkModule

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when provideRetrofit called should return retrofit object with specific base url`() {
        every { networkModule.provideRetrofit(any(), any()) } returns
                Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .build()

        val retrofit = networkModule.provideRetrofit(mockk(), mockk())

        Assert.assertEquals("https://api.github.com/", retrofit.baseUrl().toString())
    }

}