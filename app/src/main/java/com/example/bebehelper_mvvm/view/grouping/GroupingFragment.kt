package com.example.bebehelper_mvvm.view.grouping

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bebehelper_mvvm.R
import com.example.bebehelper_mvvm.databinding.FragmentGroupingBinding
import com.example.bebehelper_mvvm.view.base.BaseFragment
import com.example.bebehelper_mvvm.view.grouping.adapter.GroupingAdapter

class GroupingFragment : BaseFragment<FragmentGroupingBinding>(R.layout.fragment_grouping) {
    private lateinit var groupingAdapter: GroupingAdapter

    /** 리사이클러뷰 어댑터 셋팅 */
    private fun setAdapter() {
        groupingAdapter = GroupingAdapter()
        binding.rvGrouping.apply {
            // 기존 코드
            layoutManager = LinearLayoutManager(requireContext())
            adapter = groupingAdapter
        }
    }

    /** 리스트 더보기 */
    fun apiListMore() {

    }
}