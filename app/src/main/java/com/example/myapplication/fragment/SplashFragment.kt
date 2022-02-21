package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.myapplication.databinding.FragmentRefreshTokenBinding
import com.example.myapplication.viewModel.ArticleListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var refreshBinding: FragmentRefreshTokenBinding

    private var splashJob: Job? = null

    private val splashViewModel: ArticleListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        refreshBinding = FragmentRefreshTokenBinding.inflate(inflater, container, false)
        refreshBinding.lifecycleOwner = viewLifecycleOwner
        refreshBinding.viewModel = splashViewModel

        return refreshBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUi()
        requestData()
    }

    private fun subscribeUi() {
        splashViewModel.token.observe(viewLifecycleOwner){
            splashJob = lifecycleScope.launchWhenResumed {
                splashViewModel.getCategoryList(it)
            }
        }
        splashViewModel.categoryList.observe(viewLifecycleOwner){
            val direction = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
            view?.findNavController()?.navigate(direction)
        }
    }

    override fun onDestroyView() {
        splashJob?.cancel()
        super.onDestroyView()
    }
    private fun requestData() {
        splashJob?.cancel()
        splashJob = lifecycleScope.launchWhenResumed {
            splashViewModel.reFresh()
        }
    }
}