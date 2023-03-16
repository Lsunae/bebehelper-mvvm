package com.example.bebehelper_mvvm.view.grouping

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.bebehelper_mvvm.R
import com.example.bebehelper_mvvm.data.model.GroupingItem
import com.example.bebehelper_mvvm.databinding.ActivityGroupingCreateBinding
import com.example.bebehelper_mvvm.util.CustomDialog
import com.example.bebehelper_mvvm.util.OnSingleClickListener
import com.example.bebehelper_mvvm.util.Utils
import com.example.bebehelper_mvvm.view.base.BaseActivity
import com.example.bebehelper_mvvm.viewModel.GroupingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupingCreateActivity :
    BaseActivity<ActivityGroupingCreateBinding>(R.layout.activity_grouping_create) {
    private val viewModel: GroupingViewModel by viewModels()
    private lateinit var dialog: CustomDialog
    private var groupingItem = GroupingItem()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dialog = CustomDialog(this)
        setupView()
        setupViewModel()
    }

    private fun setupView() {
        binding.apply {
            incActionbar.apply {
                tvTitle.text = Utils.string(this@GroupingCreateActivity, R.string.group_write_title)
                ivBack.setOnClickListener(object : OnSingleClickListener() {
                    override fun onSingleClick(v: View) {
                        finish()
                    }
                })
            }

            tvCreate.setOnClickListener(object : OnSingleClickListener() {
                override fun onSingleClick(v: View) {
                    groupingItem.title = inputTitle.text.toString()
                    groupingItem.content = inputContent.text.toString()
                    groupingItem.apply {
                        if (!this.area.isNullOrEmpty() && !this.ageLimit.isNullOrEmpty() && this.childCount != null && !this.content.isNullOrEmpty()) {
                            viewModel.createGrouping(this)
                        } else {
                            Toast.makeText(
                                this@GroupingCreateActivity,
                                Utils.string(
                                    this@GroupingCreateActivity,
                                    R.string.group_create_fail
                                ),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })

            //note 지역 선택
            val areaArray = resources.getStringArray(R.array.area_array)
            val areaAdapter: ArrayAdapter<String> =
                ArrayAdapter(this@GroupingCreateActivity, R.layout.item_select_text, areaArray)
            tvSelectArea.setAdapter(areaAdapter)
            tvSelectArea.onItemClickListener =
                AdapterView.OnItemClickListener { adapterView, view, position, id ->
                    groupingItem.area = adapterView.getItemAtPosition(position).toString()
                }

            //note 아이 나이 선택
            val ageArray = resources.getStringArray(R.array.child_age_array)
            val ageAdapter: ArrayAdapter<String> =
                ArrayAdapter(this@GroupingCreateActivity, R.layout.item_select_text, ageArray)
            tvSelectChildAge.setAdapter(ageAdapter)
            tvSelectChildAge.onItemClickListener =
                AdapterView.OnItemClickListener { adapterView, view, position, id ->
                    groupingItem.ageLimit =
                        adapterView.getItemAtPosition(position).toString().replace("세", "")
                }

            //note 아이 인원 선택
            val childCountArray = resources.getStringArray(R.array.child_count_array)
            val childCountAdapter: ArrayAdapter<String> =
                ArrayAdapter(
                    this@GroupingCreateActivity,
                    R.layout.item_select_text,
                    childCountArray
                )
            tvSelectChildCount.setAdapter(childCountAdapter)
            tvSelectChildCount.onItemClickListener =
                AdapterView.OnItemClickListener { adapterView, view, position, id ->
                    groupingItem.childCount =
                        adapterView.getItemAtPosition(position).toString().replace("명", "").toInt()
                }
        }
    }

    private fun setupViewModel() {
        viewModel.groupingCreateResult.observe(this) {
            if (it == true) {
                Toast.makeText(
                    this,
                    Utils.string(this, R.string.group_create_success),
                    Toast.LENGTH_SHORT
                ).show()
                this.finish()
            } else {
                Toast.makeText(
                    this,
                    Utils.string(this, R.string.group_create_fail),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}