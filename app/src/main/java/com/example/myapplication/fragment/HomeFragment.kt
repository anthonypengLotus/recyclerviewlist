package com.example.myapplication.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.adapter.HomePagerAdapter
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.databinding.TabArticlesBinding
import com.example.myapplication.viewModel.ArticleListViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var playersJob: Job? = null
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    private val homeViewModel: ArticleListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        tabLayout = binding.tabs
        viewPager = binding.viewPager

        subscribeUi(viewPager)
        return binding.root
    }

    private fun subscribeUi(viewpager2: ViewPager2) {
        homeViewModel.categoryResponseData.observe(viewLifecycleOwner) { data ->
            val homeAdapter = HomePagerAdapter(this)
            viewpager2.adapter = homeAdapter
            homeAdapter.putData(data)
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab1: TabLayout.Tab?) {
                    if (tab1 != null) {
                        val title = tab1.view.findViewById<TextView>(R.id.title)
                        title.setTextColor(Color.BLACK)
                        val indicatorDrawable = tab1.view.findViewById<ImageView>(R.id.image)
                        indicatorDrawable.visibility = View.VISIBLE
                    }
                }

                override fun onTabUnselected(tab1: TabLayout.Tab?) {
                    if (tab1 != null) {
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
                var binding = TabArticlesBinding.inflate(LayoutInflater.from(requireContext()))
                tab.customView = binding.root
                binding.title.text = data[position].name
            }.attach()
        }
    }

    override fun onDestroyView() {
        playersJob?.cancel()
        super.onDestroyView()
    }

}