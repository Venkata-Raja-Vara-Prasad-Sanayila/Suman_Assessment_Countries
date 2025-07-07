package com.venkata.org.common

import com.venkata.org.model.remote.ApiService
import com.venkata.org.model.repository.IRepository
import com.venkata.org.model.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Module for app level scoped dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    /**
     * Provides the singleton instance of [Retrofit] with logging interceptor
     * added to Okhttp Client abd used Gson converter factory for Json Serialization
     */
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{

        //logging interceptor for logging the APi request and response
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        //OkHttp client for adding the interceptor
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    /**
     * Provides an implementation of the [ApiService] interface using Retrofit.
     */
    @Provides
    fun provideAiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    /**
     * Provides the [IRepository] implementation
     */
    @Provides
    fun provideIRepository(apiService: ApiService): IRepository = Repository(apiService)



}