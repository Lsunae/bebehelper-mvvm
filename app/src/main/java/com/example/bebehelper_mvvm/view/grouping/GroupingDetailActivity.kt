package com.example.bebehelper_mvvm.view.grouping

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.bebehelper_mvvm.R
import com.example.bebehelper_mvvm.databinding.ActivityGroupingDetailBinding
import com.example.bebehelper_mvvm.util.OnSingleClickListener
import com.example.bebehelper_mvvm.util.Utils
import com.example.bebehelper_mvvm.view.base.BaseActivity
import com.example.bebehelper_mvvm.viewModel.GroupingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupingDetailActivity : BaseActivity<ActivityGroupingDetailBinding>(R.layout.activity_grouping_detail) {
    private val viewModel: GroupingViewModel by viewModels()
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = intent.getIntExtra(ID, 0)

        setEventListener()
        viewModel.getGrouping(id)
        setUpViewModel()
    }

    private fun setEventListener() {
        binding.incActionbar.ivBack.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View) {
                finish()
            }
        })
    }

    private fun setUpViewModel() {
        viewModel.grouping.observe(this) {
            val areaText = String.format(Utils.string(this@GroupingDetailActivity, R.string.group_detail_area), it.area)
            val childAgeText = String.format(Utils.string(this@GroupingDetailActivity, R.string.group_detail_child_age), it.ageLimit)
            val childCountText = String.format(Utils.string(this@GroupingDetailActivity, R.string.group_detail_child_count), it.childCount)
            binding.apply {
                title = it.title
                content = it.content
                area = areaText
                childLimitAge = childAgeText
                childLimitCount = childCountText
            }
        }
    }

    companion object {
       private const val ID = "id"
    }
}