package com.example.ejercicio_peliculas.common

import android.content.Context
import androidx.room.Room
import com.example.ejercicio_peliculas.common.Constants.MOVIES_DATABASE
import com.example.ejercicio_peliculas.dashboard.data.repository.api.client.MoviesApiClient
import com.example.ejercicio_peliculas.dashboard.data.repository.database.MoviesDatabase
import com.example.ejercicio_peliculas_cencosud.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HiltModule {
    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(1L, TimeUnit.MINUTES)
            .readTimeout(1L, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.FILMS_API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideSubscriptionService(retrofit: Retrofit): MoviesApiClient =
        retrofit.create(MoviesApiClient::class.java)

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context): MoviesDatabase{
        return  Room.databaseBuilder(context, MoviesDatabase::class.java, MOVIES_DATABASE).build()
    }

    @Provides
    @Singleton
    fun provideMovieDatabase(database: MoviesDatabase) = database.getMovieDao()

    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext app: Context): PreferencesRepository = PreferencesRepository(app)

}