package com.example.my.project.paginationandroid.repositories.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RemoteInjector {
    const val API_KEY = "d6fd31ff-2b46-4600-b25d-cbcd09f0ac14"
    const val BASE_URL = "https://api.thedogapi.com"
    const val HEADER_API_KEY = "x-api-key"


    fun injectApiService(retrofit: Retrofit = getRetrofit()): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    private fun getRetrofit(okHttpClient: OkHttpClient = getOkHttpClient()): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }


    private fun getOkHttpNetworkInterceptor(): Interceptor {
        return Interceptor { chain ->
            val newRequest = chain.request().newBuilder().addHeader(HEADER_API_KEY, API_KEY).build()
            chain.proceed(newRequest)
        }
    }

    private fun getHttpLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }


    private fun getOkHttpClient(
        okHttpLogger: HttpLoggingInterceptor = getHttpLogger(),
        okHttpNetworkInterceptor: Interceptor = getOkHttpNetworkInterceptor()
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(okHttpLogger)
            .addInterceptor(okHttpNetworkInterceptor)
            .build()
    }


}