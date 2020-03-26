package com.study.carton.http

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.study.carton.App
import com.study.carton.BuildConfig
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit



object RetrofitClient {

    val service by lazy { getService(ComicService::class.java, ComicService.BASE_URL) }

    private val cookieJar by lazy { PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(App.CONTEXT)) }

    private val client: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            val logging = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
            } else {
                logging.level = HttpLoggingInterceptor.Level.BASIC
            }

            builder.addInterceptor(logging)
                .connectTimeout(10, TimeUnit.SECONDS)
            val httpCacheDirectory = File(App.CONTEXT.cacheDir, "responses")
            val cacheSize = 10 * 1024 * 1024L // 10 MiB
            val cache = Cache(httpCacheDirectory, cacheSize)
            builder.cache(cache)
                .cookieJar(cookieJar)
                .addInterceptor(CommonInterceptor)

            return builder.build()
        }


    private fun <S> getService(serviceClass: Class<S>, baseUrl: String): S {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .baseUrl(baseUrl)
            .build().create(serviceClass)
    }


    private val CommonInterceptor: Interceptor
        get() {
            return Interceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("charset", "UTF-8")
                    .build()

                chain.proceed(request)
            }
        }
}


