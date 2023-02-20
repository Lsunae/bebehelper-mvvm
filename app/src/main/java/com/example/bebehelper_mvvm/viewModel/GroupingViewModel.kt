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

    private val _groupingList = MutableLiveData<List<GroupingItem>>()
    val groupingList: LiveData<List<GroupingItem>> = _groupingList

    fun getGroupingList() {
        repository.getGroupingList(object : Callback<List<Grouping>> {
            override fun onSuccess(response: List<Grouping>) {
                Log.i("[${javaClass.name}]", "$response")

                val items = mutableListOf<GroupingItem>()
                /*
                for (i in response.indices) {
                    items.add(response[i].toGroupingItem())
                }
                 */

                // testCode_
                items.add(GroupingItem(100, "함께 육아하실 분들 모여주세요~~", "잠실동", "4세", 3, "함께 육아해요 ㅎㅎ", 1004, "천사"))

                _groupingList.postValue(items)
            }

            override fun onFailure(message: String) {
                Log.i("[${javaClass.name}]", message)
            }
        })
    }
}