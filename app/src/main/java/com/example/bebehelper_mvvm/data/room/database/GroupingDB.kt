package com.example.bebehelper_mvvm.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bebehelper_mvvm.data.room.dao.GroupingDao
import com.example.bebehelper_mvvm.data.room.entity.Grouping

@Database(entities = [Grouping::class], version = 1)
abstract class GroupingDB : RoomDatabase() {
    abstract fun groupingDao(): GroupingDao
}