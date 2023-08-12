package com.ahmedmhassaan.data.di

import com.ahmedmhassaan.data.repository.ArticlesRepositoryImpl
import com.ahmedmhassaan.domain.repos.LocalCacheArticlesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ReposModule {


}