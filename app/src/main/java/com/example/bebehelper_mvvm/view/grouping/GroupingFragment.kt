package com.example.bebehelper_mvvm.view.grouping

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bebehelper_mvvm.R
import com.example.bebehelper_mvvm.databinding.FragmentGroupingBinding
import com.example.bebehelper_mvvm.view.base.BaseFragment
import com.example.bebehelper_mvvm.view.grouping.adapter.GroupingAdapter
import com.example.bebehelper_mvvm.viewModel.GroupingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupingFragment : BaseFragment<FragmentGroupingBinding>(R.layout.fragment_grouping) {
    private val viewModel: GroupingViewModel by viewModels()
    private lateinit var groupingAdapter: GroupingAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickEvent()
        setAdapter()
        viewModel.getGroupingList()
        setupViewModel()
    }

    private fun setClickEvent() {
        binding.apply {
            ivCreate.setOnClickListener {
                val intent = Intent(activity, GroupingCreateActivity::class.java)
                startActivity(intent)
            }
        }
    }

    /** 리사이클러뷰 어댑터 셋팅 */
    private fun setAdapter() {
        groupingAdapter = GroupingAdapter()
        binding.rvGrouping.apply {
            // 기존 코드
            layoutManager = LinearLayoutManager(requireContext())
            adapter = groupingAdapter
        }
    }

    private fun setupViewModel() {
        viewModel.groupingList.observe(viewLifecycleOwner) {
            groupingAdapter.addData(it)
        }
    }

    /** 리스트 더보기 */
    fun apiListMore() {

    }
}