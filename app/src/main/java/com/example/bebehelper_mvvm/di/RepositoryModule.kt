package com.example.bebehelper_mvvm.di

import com.example.bebehelper_mvvm.data.repository.grouping.GroupingRepository
import com.example.bebehelper_mvvm.data.repository.grouping.GroupingRepositoryImpl
import com.example.bebehelper_mvvm.data.source.local.grouping.GroupingLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @Provides
    @ViewModelScoped
    fun provideGroupingRepository(
        groupingLocalDataSourceImpl: GroupingLocalDataSourceImpl
    ): GroupingRepository {
        return GroupingRepositoryImpl(groupingLocalDataSourceImpl)
    }
}