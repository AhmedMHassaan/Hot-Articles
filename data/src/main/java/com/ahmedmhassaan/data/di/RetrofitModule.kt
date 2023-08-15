package com.ahmedmhassaan.data.di

import com.ahmedmhassaan.data.remote.adapters.ApiCallAdapterFactory
import com.ahmedmhassaan.data.remote.adapters.AuthHeaderInterceptor
import com.ahmedmhassaan.data.remote.api.ArticleService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object
RetrofitModule {


    @Provides
    @Singleton
    fun provideRetrofit(
        factory: ApiCallAdapterFactory,
        authHeaderIterator: AuthHeaderInterceptor
    ): Retrofit {

        val baseServerLink = "http://newsapi.org/v2/"

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        return Retrofit
            .Builder()
            .baseUrl(baseServerLink)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(factory)
            .client(
                OkHttpClient
                    .Builder()
                    .apply {
                        this.callTimeout(1, TimeUnit.MINUTES)
                            .connectTimeout(1, TimeUnit.MINUTES)
                            .readTimeout(1, TimeUnit.MINUTES)
                            .writeTimeout(1, TimeUnit.MINUTES)
                            .addInterceptor(interceptor)
                            .addInterceptor(authHeaderIterator)

                    }.build()
            )
            .build()
    }


    @Provides
    @Singleton
    fun provideArticlesService(retrofit: Retrofit): ArticleService =
        retrofit.create(ArticleService::class.java)
}