package com.example.myapplication.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.data.database.NavigationEntity
import com.example.myapplication.fragment.ArticleListFragment
import com.example.myapplication.fragment.HomeFragment


class HomePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private var  tabFragmentsCreators: List<NavigationEntity> = arrayListOf()

//    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
//        1 to { ArticleListFragment() },
//        2 to { ArticleListFragment() }
//    )
    override fun getItemCount(): Int {
        return tabFragmentsCreators.size
    }

    override fun createFragment(position: Int): Fragment {
        return ArticleListFragment(tabFragmentsCreators[position].key)
//        return ArticleListFragment.newInstance("")
    }

    fun putData(list: List<NavigationEntity>) {
        tabFragmentsCreators = list
    }
}
