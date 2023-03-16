package com.example.bebehelper_mvvm.view

import android.os.Bundle
import com.example.bebehelper_mvvm.R
import com.example.bebehelper_mvvm.constant.MenuType
import com.example.bebehelper_mvvm.databinding.ActivityMainBinding
import com.example.bebehelper_mvvm.view.grouping.GroupingFragment
import com.example.bebehelper_mvvm.view.recommendedActivity.RecommendedActivityFragment
import com.example.bebehelper_mvvm.view.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private var groupingFragment: GroupingFragment? = null
    private var recommendedActivityFragment: RecommendedActivityFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNavigationBar()
    }

    private fun initNavigationBar() {
        binding.bottomNavigationView.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.action_grouping -> changeFragment(MenuType.GROUPING)
                    R.id.action_recommended_activity -> changeFragment(MenuType.RECOMMENDED_ACTIVITY)
                }
                true
            }
            selectMenuTabIndex(MenuType.GROUPING)
        }
    }

    private fun selectMenuTabIndex(type: MenuType?) {
        val navigation = binding.bottomNavigationView
        runOnUiThread {
            when (type) {
                MenuType.RECOMMENDED_ACTIVITY -> navigation.selectedItemId =
                    R.id.action_recommended_activity
                else -> navigation.selectedItemId = R.id.action_grouping
            }
        }
    }

    private fun changeFragment(type: MenuType) {
        when (type) {
            MenuType.GROUPING -> {
                if (groupingFragment == null) {
                    groupingFragment = GroupingFragment()
                    supportFragmentManager.beginTransaction()
                        .add(binding.mainContainer.id, groupingFragment!!).commit()
                } else {
                    supportFragmentManager.beginTransaction().show(groupingFragment!!).commit()
                    groupingFragment!!.onResume()
                }
                if (recommendedActivityFragment != null) supportFragmentManager.beginTransaction()
                    .hide(recommendedActivityFragment!!).commit()
            }
            MenuType.RECOMMENDED_ACTIVITY -> {
                if (recommendedActivityFragment == null) {
                    recommendedActivityFragment = RecommendedActivityFragment()
                    supportFragmentManager.beginTransaction()
                        .add(binding.mainContainer.id, recommendedActivityFragment!!).commit()
                } else {
                    supportFragmentManager.beginTransaction().show(recommendedActivityFragment!!)
                        .commit()
                    recommendedActivityFragment!!.onResume()
                }
                if (groupingFragment != null) supportFragmentManager.beginTransaction()
                    .hide(groupingFragment!!).commit()
            }
        }
    }
}