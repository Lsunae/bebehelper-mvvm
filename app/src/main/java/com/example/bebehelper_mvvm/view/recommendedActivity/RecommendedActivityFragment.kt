package com.example.bebehelper_mvvm.view.recommendedActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bebehelper_mvvm.R
import com.example.bebehelper_mvvm.data.model.PlayItem
import com.example.bebehelper_mvvm.databinding.FragmentRecomendedActivityBinding
import com.example.bebehelper_mvvm.util.Utils
import com.example.bebehelper_mvvm.view.base.BaseFragment
import com.example.bebehelper_mvvm.view.recommendedActivity.adapter.PlayImageAdapter

class RecommendedActivityFragment : BaseFragment<FragmentRecomendedActivityBinding>(R.layout.fragment_recomended_activity) {
    private lateinit var playImageAdapter: PlayImageAdapter
    private var playList = mutableListOf<PlayItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setPlayItemList()
        setAdapter()
        playImageAdapter.addData(playList)
        getIsFirstTextVisibility(playList.isNotEmpty(), playList)
        setUpView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun setUpView() {
        binding.incActionbar.tvTitle.text =
            Utils.string(requireContext(), R.string.recommended_activity)
        setClickListener()
    }

    /** 리사이클러뷰 어댑터 셋팅 */
    private fun setAdapter() {
        playImageAdapter = PlayImageAdapter()
        binding.rvPlayImg.apply {
            // 기존 코드
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = playImageAdapter
        }
    }

    private fun setClickListener() {
        playImageAdapter.setOnItemClickListener(object : PlayImageAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, item: PlayItem) {
                binding.apply {
                    tvPlayTitle.text = item.name
                    when (position) {
                        PLAY_BLOCK -> tvPlayExplanation.text =
                            Utils.string(requireContext(), R.string.play_block_explanation)
                        PLAY_DRAWING -> binding.tvPlayExplanation.text =
                            Utils.string(requireContext(), R.string.play_drawing_explanation)
                        PLAY_COOKING -> binding.tvPlayExplanation.text =
                            Utils.string(requireContext(), R.string.play_cooking_explanation)
                        PLAY_WATER -> binding.tvPlayExplanation.text =
                            Utils.string(requireContext(), R.string.play_water_explanation)
                        PLAY_PLANTS -> binding.tvPlayExplanation.text =
                            Utils.string(requireContext(), R.string.play_plants_explanation)
                    }
                }
            }
        })
    }

    private fun setPlayItemList() {
        playList.add(
            PlayItem(
                ContextCompat.getDrawable(requireContext(), R.drawable.play_block),
                Utils.string(requireContext(), R.string.play_block)
            )
        )   // 블럭놀이
        playList.add(
            PlayItem(
                ContextCompat.getDrawable(requireContext(), R.drawable.play_drawing),
                Utils.string(requireContext(), R.string.play_drawing)
            )
        )   // 그림 그리기
        playList.add(
            PlayItem(
                ContextCompat.getDrawable(requireContext(), R.drawable.play_cooking),
                Utils.string(requireContext(), R.string.play_cooking)
            )
        )   // 요리하기
        playList.add(
            PlayItem(
                ContextCompat.getDrawable(requireContext(), R.drawable.play_water),
                Utils.string(requireContext(), R.string.play_water)
            )
        )   // 물놀이
        playList.add(
            PlayItem(
                ContextCompat.getDrawable(requireContext(), R.drawable.play_plants),
                Utils.string(requireContext(), R.string.play_plants)
            )
        )   // 식물 심기
    }

    private fun getIsFirstTextVisibility(isVisibility: Boolean, items: MutableList<PlayItem>) {
        if (isVisibility && items[0].name == Utils.string(requireContext(), R.string.play_block)) {
            binding.apply {
                tvPlayTitle.text = items[0].name
                tvPlayExplanation.text =
                    Utils.string(requireContext(), R.string.play_block_explanation)
            }
        }
    }

    companion object {
        private const val PLAY_BLOCK = 0
        private const val PLAY_DRAWING = 1
        private const val PLAY_COOKING = 2
        private const val PLAY_WATER = 3
        private const val PLAY_PLANTS = 4
    }
}