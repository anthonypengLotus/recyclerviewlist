package com.example.myapplication.viewModel

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.adapter.HomePagerAdapter
import com.example.myapplication.data.database.NavigationEntity
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.databinding.ListItemArticleBinding
import com.example.myapplication.databinding.TabArticlesBinding
import com.example.myapplication.repository.HomeRepository
import com.example.myapplication.widget.IndicatorDrawable
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    homeRepository: HomeRepository
) : ViewModel() {

     private val categoryResponseData: LiveData<List<NavigationEntity>> = homeRepository.getPlayers().asLiveData()
    suspend fun requestData(
        owner: LifecycleOwner,
        tabLayout: TabLayout,
        viewpager2: ViewPager2,
        context: Fragment

    ) {
        articleCaegoryObserver(owner, tabLayout,viewpager2,context)
    }
    private fun articleCaegoryObserver(
        owner: LifecycleOwner,
        tabLayout: TabLayout,
        viewpager2: ViewPager2,
        context:Fragment
    ) = Observer<List<NavigationEntity>> { data ->
        val homeAdapter = HomePagerAdapter(context)
        viewpager2.adapter = homeAdapter
        homeAdapter.putData(data)
        tabLayout.addOnTabSelectedListener(object :OnTabSelectedListener{
            override fun onTabSelected(tab1: TabLayout.Tab?) {

                if (tab1 != null) {
                    Log.e("",tab1.text.toString())
                    val title = tab1.view.findViewById<TextView>(R.id.title)
                    title.setTextColor(Color.BLACK)

                    val indicatorDrawable = tab1.view.findViewById<ImageView>(R.id.image)
                    indicatorDrawable.visibility = View.VISIBLE
                }
            }

            override fun onTabUnselected(tab1: TabLayout.Tab?) {
                if (tab1 != null) {
                    Log.e("",tab1.text.toString())
                    val title = tab1.view.findViewById<TextView>(R.id.title)
                    title.setTextColor(Color.GRAY)

                    val indicatorDrawable = tab1.view.findViewById<ImageView>(R.id.image)
                    indicatorDrawable.visibility = View.INVISIBLE
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        TabLayoutMediator(tabLayout, viewpager2) { tab, position ->
//            tab.text =data[position].name

            var binding = TabArticlesBinding.inflate(LayoutInflater.from(context.context))
            tab.setCustomView(binding.root)
            binding.title.text = data[position].name

            val customView = tab.customView;



        }.attach()

    }.apply {
        categoryResponseData.observe(owner, this)
    }

}

