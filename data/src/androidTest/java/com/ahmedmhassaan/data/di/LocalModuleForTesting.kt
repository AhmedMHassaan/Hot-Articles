package com.ahmedmhassaan.data.di

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.ahmedmhassaan.data.local.database.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

//@VisibleForTesting(otherwise = VisibleForTesting.NONE)
@Module
@InstallIn(SingletonComponent::class)
class LocalModuleForTesting {

    @Singleton
    @Provides
    @Named("test_database")
    fun provideRoomDatabaseForTest(): NewsDatabase {
        return Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Context?>().applicationContext,
            NewsDatabase::class.java,
        )
            .allowMainThreadQueries()
            .build();
    }

}