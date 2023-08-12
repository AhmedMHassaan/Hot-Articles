package com.ahmedmhassaan.data.di

import com.ahmedmhassaan.data.local.datasources.FavouritesDataSource
import com.ahmedmhassaan.data.local.datasources.LocalArticlesDataSource
import com.ahmedmhassaan.data.remote.datasources.RemoteArticlesDataSource
import com.ahmedmhassaan.data.repository.ArticlesRepositoryImpl
import com.ahmedmhassaan.data.repository.FavouritesRepositoryImpl
import com.ahmedmhassaan.data.repository.LocalCacheRepositoryImpl
import com.ahmedmhassaan.domain.repos.ArticlesRepository
import com.ahmedmhassaan.domain.repos.FavouritesRepository
import com.ahmedmhassaan.domain.repos.LocalCacheArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ReposModule {


    @Provides
    fun provideRemoteArticlesRepository(articlesDataSource: RemoteArticlesDataSource): ArticlesRepository {
        return ArticlesRepositoryImpl(articlesDataSource)
    }

    @Provides
    fun provideFavArticlesRepository(favouritesDataSource: FavouritesDataSource): FavouritesRepository {
        return FavouritesRepositoryImpl(favouritesDataSource)
    }

    @Provides
    fun provideLocalCacheArticlesRepository(dataSource: LocalArticlesDataSource): LocalCacheArticlesRepository {
        return LocalCacheRepositoryImpl(dataSource)
    }


}