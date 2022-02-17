package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.adapter.HomePagerAdapter
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.viewModel.HomeViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var playersJob: Job? = null
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        tabLayout = binding.tabs
        viewPager = binding.viewPager

//        homeAdapter = HomePagerAdapter(this)
//        viewPager.adapter = homeAdapter


//        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//            tab.text = "data[position].title"
//        }.attach()


        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestData()
    }
    override fun onDestroyView() {
        playersJob?.cancel()
        super.onDestroyView()
    }

    private fun requestData() {
        playersJob?.cancel()
        playersJob = lifecycleScope.launchWhenResumed {
            homeViewModel.requestData(viewLifecycleOwner, tabLayout, viewPager,this@HomeFragment)
        }
    }
}