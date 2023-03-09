package com.example.bebehelper_mvvm.view

import android.os.Bundle
import com.example.bebehelper_mvvm.R
import com.example.bebehelper_mvvm.constant.MenuType
import com.example.bebehelper_mvvm.databinding.ActivityMainBinding
import com.example.bebehelper_mvvm.view.grouping.GroupingFragment
import com.example.bebehelper_mvvm.view.myPage.MyPageFragment
import com.example.bebehelper_mvvm.view.recommendedActivity.RecommendedActivityFragment
import com.example.bebehelper_mvvm.view.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private var groupingFragment: GroupingFragment? = null
    private var recommendedActivityFragment: RecommendedActivityFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNavigationBar()
    }

    private fun initNavigationBar() {
        println("initNavigationBar_ ")
        binding.bottomNavigationView.run {
            setOnItemSelectedListener {
                println("initNavigationBar_ ${it.itemId}")
                when (it.itemId) {
                    R.id.action_grouping -> {
                        println("initNavigationBar_grouping_ ${it.itemId}")
                        changeFragment(MenuType.GROUPING)
                    }
                    R.id.action_recommended_activity -> {
                        println("initNavigationBar_recommended_ ${it.itemId}")
                        changeFragment(MenuType.RECOMMENDED_ACTIVITY)
                    }
                }
                true
            }
            selectMenuTabIndex(MenuType.GROUPING)
        }
    }

    /** 메뉴 탭 선택 */
    private fun selectMenuTabIndex(type: MenuType?) {
        val navigation = binding.bottomNavigationView
        runOnUiThread {
            println("select_tab_ $type")
            when (type) {
                MenuType.RECOMMENDED_ACTIVITY -> {

                    println("select_tab_1 $type")
                    navigation.selectedItemId = R.id.action_recommended_activity
                }
                else -> {

                    println("select_tab_2 $type")
                    navigation.selectedItemId = R.id.action_grouping
                }
            }
        }
    }

    /**
     * 프래그먼트 변경
     * 각 프래그먼트 화면 유지를 위해 hide, show 사용
     */
    private fun changeFragment(type: MenuType) {
        println("type_ $type")
        when (type) {
            MenuType.GROUPING -> {
                println("type_groupingFragment_ $groupingFragment")
                println("type_1 $type")
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
                println("type_recommendedActivityFragment_ $recommendedActivityFragment")
                println("type_2 $type")
                if (recommendedActivityFragment == null) {
                    recommendedActivityFragment = RecommendedActivityFragment()
                    supportFragmentManager.beginTransaction()
                        .add(binding.mainContainer.id, recommendedActivityFragment!!).commit()
                } else {
                    supportFragmentManager.beginTransaction().show(recommendedActivityFragment!!).commit()
                    recommendedActivityFragment!!.onResume()
                }

                if (groupingFragment != null) supportFragmentManager.beginTransaction()
                    .hide(groupingFragment!!).commit()
            }
        }
    }
}