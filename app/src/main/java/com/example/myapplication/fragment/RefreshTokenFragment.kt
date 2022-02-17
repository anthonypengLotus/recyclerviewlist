package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentRefreshTokenBinding
import com.example.myapplication.viewModel.RefreshViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

/**
 * A simple [Fragment] subclass.
 * Use the [RefreshTokenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class RefreshTokenFragment : Fragment() {
    private lateinit var refreshBinding: FragmentRefreshTokenBinding

    private var splashJob: Job? = null

    private val splashViewModel: RefreshViewModel by viewModels()

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
        requestData()
    }

    override fun onDestroyView() {
        splashJob?.cancel()
        super.onDestroyView()
    }
    private fun requestData() {
        splashJob?.cancel()
        splashJob = lifecycleScope.launchWhenResumed {
            splashViewModel.requestData(requireContext(), viewLifecycleOwner, view)
        }
    }
}