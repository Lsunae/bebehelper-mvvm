package com.example.bebehelper_mvvm.view.recommendedActivity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.bebehelper_mvvm.R
import com.example.bebehelper_mvvm.data.model.PlayItem
import com.example.bebehelper_mvvm.databinding.ItemPlayBinding
import com.example.bebehelper_mvvm.util.Utils

class PlayImageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var context: Context
    private var items = mutableListOf<PlayItem>()
    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int, item: PlayItem)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        this.context = parent.context
        return ImageHolder(
            ItemPlayBinding.inflate(
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
            is ImageHolder -> {
                holder.bind(items[position], position)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addData(addDataList: MutableList<PlayItem>) {
        this.items.clear()
        items.addAll(addDataList)
    }

    inner class ImageHolder(private val binding: ItemPlayBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PlayItem, position: Int) {
            binding.apply {
                ivPlay.apply {
                    setImageDrawable(item.image)
                    setOnClickListener { onItemClickListener?.onItemClick(position, item) }

                    val width = (Utils.getDeviceWidth() / 15) * 8
                    val params = ConstraintLayout.LayoutParams(
                        width,
                        width
                    ).apply {
                        topToTop = ConstraintSet.PARENT_ID
                        startToStart = ConstraintSet.PARENT_ID
                        endToEnd = ConstraintSet.PARENT_ID

                    }
                    layoutParams = params
                }

                tvPlay.apply {
                    val nameParams = ConstraintLayout.LayoutParams(
                        ConstraintLayout.LayoutParams.WRAP_CONTENT,
                        ConstraintLayout.LayoutParams.WRAP_CONTENT
                    ).apply {
                        topToBottom = R.id.iv_play
                        startToStart = ConstraintSet.PARENT_ID
                        endToEnd = ConstraintSet.PARENT_ID
                    }
                    layoutParams = nameParams
                    text = item.name
                }
            }
        }
    }
}