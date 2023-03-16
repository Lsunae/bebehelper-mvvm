package com.example.bebehelper_mvvm.view.grouping

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bebehelper_mvvm.R
import com.example.bebehelper_mvvm.data.model.GroupingItem
import com.example.bebehelper_mvvm.databinding.FragmentGroupingBinding
import com.example.bebehelper_mvvm.util.Utils
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

        setUpView()
        setAdapter()
        setEventListener()
        viewModel.getGroupingList()
        setupViewModel()
    }

    private fun setUpView() {
        binding.incActionbar.tvTitle.text = Utils.string(requireContext(), R.string.grouping)
    }

    private fun setEventListener() {
        binding.apply {
            ivCreate.setOnClickListener {
                val intent = Intent(activity, GroupingCreateActivity::class.java)
                startActivity(intent)
            }
        }

        groupingAdapter.setOnItemClickListener(object : GroupingAdapter.OnItemClickListener {
            override fun onItemClick(item: GroupingItem) {
                val intent = Intent(activity, GroupingDetailActivity::class.java)
                intent.putExtra(ID, item.id)
                startActivity(intent)
            }
        })
    }

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

    companion object {
        private const val ID = "id"
    }
}