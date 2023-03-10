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

    private val _grouping = MutableLiveData<GroupingItem>()
    val grouping: LiveData<GroupingItem> = _grouping

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

    fun getGrouping(id: Int) {
        repository.getGrouping(id, object : Callback<Grouping> {
            override fun onSuccess(response: Grouping) {
                Log.i("[${javaClass.name}]", "$response")
                val groupingItem = GroupingItem(
                    response.id,
                    response.title,
                    response.area,
                    response.ageLimit,
                    response.childCount,
                    response.content,
                    response.writerId,
                    response.writerNickname)
                _grouping.value = groupingItem
            }

            override fun onFailure(message: String) {
                Log.i("[${javaClass.name}]", message)
            }
        })
    }
}