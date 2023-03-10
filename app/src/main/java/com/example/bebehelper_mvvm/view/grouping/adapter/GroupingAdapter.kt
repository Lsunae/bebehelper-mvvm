package com.example.bebehelper_mvvm.view.grouping.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bebehelper_mvvm.R
import com.example.bebehelper_mvvm.data.model.GroupingItem
import com.example.bebehelper_mvvm.databinding.ItemGroupingBinding
import com.example.bebehelper_mvvm.view.grouping.GroupingFragment
import com.example.bebehelper_mvvm.util.Utils
import java.lang.ref.WeakReference

class GroupingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var context: Context
    private var items = mutableListOf<GroupingItem>()
    lateinit var groupingFragment: WeakReference<GroupingFragment>

    fun addData(addDataList: List<GroupingItem>) {
        this.items.clear()
        items.addAll(addDataList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        this.context = parent.context
        return GroupingHolder(
            ItemGroupingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        settingPosition(holder, position)
    }

    private fun settingPosition(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GroupingHolder -> {
                holder.bind(items[position], position)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class GroupingHolder(private val binding: ItemGroupingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var item: GroupingItem

        fun bind(item: GroupingItem, position: Int) {
            this.item = item

            binding.apply {
                title = item.title
                content = item.content
                area = item.area
                childLimitAge =
                    String.format(Utils.string(context, R.string.age_count), item.ageLimit)
                childLimitCount =
                    String.format(Utils.string(context, R.string.user_count), item.childCount)
            }
        }
    }
}