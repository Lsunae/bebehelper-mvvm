package com.example.bebehelper_mvvm.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bebehelper_mvvm.data.model.GroupingItem
import com.example.bebehelper_mvvm.data.repository.Callback
import com.example.bebehelper_mvvm.data.repository.grouping.GroupingRepository
import com.example.bebehelper_mvvm.data.room.entity.Grouping
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GroupingViewModel @Inject constructor(private val repository: GroupingRepository) :
    ViewModel() {
    private val _groupingCreateResult = MutableLiveData<Boolean>()
    val groupingCreateResult: LiveData<Boolean> = _groupingCreateResult
    private val _groupingList = MutableLiveData<List<GroupingItem>>()
    val groupingList: LiveData<List<GroupingItem>> = _groupingList

    fun createGrouping(groupingItem: GroupingItem) {
        repository.createGrouping(groupingItem.title, groupingItem.area!!, groupingItem.ageLimit!!, groupingItem.childCount!!, groupingItem.content!!, groupingItem.writerId, groupingItem.writerNickname,
            object : Callback<Boolean> {
                override fun onSuccess(response: Boolean) {
                    Log.i("[${javaClass.name}]", "$response")
                    _groupingCreateResult.postValue(response)
                }

                override fun onFailure(message: String) {
                    Log.i("[${javaClass.name}]", message)
                    _groupingCreateResult.postValue(false)
                }
            })
    }

    fun getGroupingList() {
        repository.getGroupingList(object : Callback<List<Grouping>> {
            override fun onSuccess(response: List<Grouping>) {
                Log.i("[${javaClass.name}]", "$response")

                val items = mutableListOf<GroupingItem>()
                for (i in response.indices) {
                    items.add(response[i].toGroupingItem())
                }
                _groupingList.postValue(items)
            }

            override fun onFailure(message: String) {
                Log.i("[${javaClass.name}]", message)
            }
        })
    }
}