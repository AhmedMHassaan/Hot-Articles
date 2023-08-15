package com.ahmedmhassaan.data.di

import com.ahmedmhassaan.data.local.datasources.FavouritesDataSource
import com.ahmedmhassaan.data.local.datasources.LanguageDataSource
import com.ahmedmhassaan.data.local.datasources.LocalArticlesDataSource
import com.ahmedmhassaan.data.remote.datasources.RemoteArticlesDataSource
import com.ahmedmhassaan.data.repository.ArticlesRepositoryImpl
import com.ahmedmhassaan.data.repository.FavouritesRepositoryImpl
import com.ahmedmhassaan.data.repository.LanguageRepositoryImpl
import com.ahmedmhassaan.data.repository.LocalCacheRepositoryImpl
import com.ahmedmhassaan.domain.repos.ArticlesRepository
import com.ahmedmhassaan.domain.repos.FavouritesRepository
import com.ahmedmhassaan.domain.repos.LanguageRepository
import com.ahmedmhassaan.domain.repos.LocalCacheArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReposModule {


    @Provides
    @Singleton
    fun provideRemoteArticlesRepository(articlesDataSource: RemoteArticlesDataSource): ArticlesRepository {
        return ArticlesRepositoryImpl(articlesDataSource)
    }

    @Provides
    @Singleton
    fun provideFavArticlesRepository(favouritesDataSource: FavouritesDataSource): FavouritesRepository {
        return FavouritesRepositoryImpl(favouritesDataSource)
    }

    @Provides
    @Singleton
    fun provideLocalCacheArticlesRepository(dataSource: LocalArticlesDataSource): LocalCacheArticlesRepository {
        return LocalCacheRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideLanguagesRepository(dataSource: LanguageDataSource): LanguageRepository {
        return LanguageRepositoryImpl(dataSource)
    }


}