package com.example.bebehelper_mvvm.data.repository.grouping

import com.example.bebehelper_mvvm.data.repository.Callback
import com.example.bebehelper_mvvm.data.room.entity.Grouping

interface GroupingRepository {
    fun createGrouping(
        title: String,
        area: String,
        ageLimit: String,
        childCount: Int,
        content: String,
        writerId: Int,
        writerNickname: String,
        callback: Callback<Boolean>
    )

    fun updateGrouping(
        id: Int,
        title: String,
        area: String,
        ageLimit: String,
        childCount: String,
        content: String,
        callback: Callback<Boolean>
    )

    fun getGrouping(email: String, callback: Callback<Grouping>)

    fun getGroupingList(callback: Callback<List<Grouping>>)

    fun deleteGrouping(id: Int, callback: Callback<String>)
}