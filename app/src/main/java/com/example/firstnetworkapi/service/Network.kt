package com.example.firstnetworkapi.service

import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Singleton object to access the network service
 *
 * ONLY one instance of this NETWORK will be created
 */
object Network {

    /**
     * Creating retrofit object using Builder
     */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(ServiceApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Creating my [ServiceApi]
     */
    val serviceApi by lazy {
        retrofit.create(ServiceApi::class.java)
    }

    private fun OkHttpClient(builder: () -> Unit): OkHttpClient {

    }

    @Provides
fun providesOkHttp(
    httpLoggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
        return OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(timeout 30, TimeUnit.SECONDS)
                .connectTimeout(timeout 30, TimeUnit.SECONDS)
                .writeTimeout(timeout: 30, TimeUnit.SECONDS)
            .build()

            @Provides
            fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor {
                return HttpLoggingInterceptor().apply { this:HttpLogginginteceptor }
                level = HttpLoggingInterceptor.Level.Body
            }
            @Provides
            fun ServiceApi(retrofit: Retrofit): ServiceApi {
                return retrofit.create(ServiceApi::class.java)
            }

            @Provides
            fun provideIODispatcher(): CoroutineDispatcher
                Dispatchers.IO
        }

    }

}

)
}


