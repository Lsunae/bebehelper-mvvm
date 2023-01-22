package com.example.bebehelper_mvvm.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "grouping")
data class Grouping(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "area")
    var area: String? = "",

    @ColumnInfo(name = "ageLimit")
    var ageLimit: String? = "",

    @ColumnInfo(name = "childCount")
    var childCount: Int? = 0,

    @ColumnInfo(name = "content")
    var content: String? = "",

    @ColumnInfo(name = "writerId")
    var writerId: Int? = 0,

    @ColumnInfo(name = "writerNickname")
    var writerNickname: String? = ""
)