package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.myapplication.adapter.ArticlesAdapter
import com.example.myapplication.databinding.FragmentArticleListBinding
import com.example.myapplication.viewModel.ArticleListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ArticleListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ArticleListFragment(private var api:String) : Fragment() {

    private lateinit var articlesBinding: FragmentArticleListBinding

    private var playersJob: Job? = null

    private val articlesViewModel: ArticleListViewModel by viewModels()

    private lateinit var articlesAdapter: ArticlesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        articlesBinding = FragmentArticleListBinding.inflate(inflater, container, false)
        return articlesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUI()
        requestData()
    }

    override fun onDestroyView() {
        playersJob?.cancel()
        super.onDestroyView()
    }

    private fun subscribeUI() {
        articlesAdapter = ArticlesAdapter()
        articlesBinding.playerList.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )
        articlesBinding.playerList.adapter = articlesAdapter
    }

    private fun requestData() {
        playersJob?.cancel()
        playersJob = lifecycleScope.launchWhenResumed {
            articlesViewModel.requestData(viewLifecycleOwner, articlesAdapter,api)
        }
    }
}