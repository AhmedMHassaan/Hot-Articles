package com.ahmedmhassaan.data.di

import android.content.Context
import androidx.room.Room.databaseBuilder
import com.ahmedmhassaan.data.local.database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {


    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }
/*
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): NewsDatabase {
        return databaseBuilder(
            context,
            NewsDatabase ::class.java,
            "news.db"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build();
    }*/




}