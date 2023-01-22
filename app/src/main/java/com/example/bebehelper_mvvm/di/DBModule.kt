package com.example.bebehelper_mvvm.di

import android.content.Context
import androidx.room.Room
import com.example.bebehelper_mvvm.data.room.dao.GroupingDao
import com.example.bebehelper_mvvm.data.room.database.GroupingDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {
    @Singleton
    @Provides
    fun provideGroupingDatabase(@ApplicationContext context: Context): GroupingDB {
        return Room.databaseBuilder(
            context.applicationContext,
            GroupingDB::class.java, "grouping.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideGroupingDao(groupDB: GroupingDB): GroupingDao {
        return groupDB.groupingDao()
    }
}