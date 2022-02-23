package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.myapplication.adapter.ArticlesAdapter
import com.example.myapplication.databinding.FragmentArticleListBinding
import com.example.myapplication.viewModel.ArticleListViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_API = "param1"

@AndroidEntryPoint
class ArticleListFragment() : Fragment() {

    private var api: String? = null

    private lateinit var articlesBinding: FragmentArticleListBinding

    private val articlesViewModel: ArticleListViewModel by viewModels()

    private lateinit var articlesAdapter: ArticlesAdapter

    companion object {

        @JvmStatic
        fun newInstance(api: String) =
            ArticleListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_API, api)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            api = it.getString(ARG_API)
        }
    }

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

    }


    private fun subscribeUI() {
        articlesAdapter = ArticlesAdapter()
        articlesBinding.articlesList.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )
        articlesBinding.articlesList.adapter = articlesAdapter
        if (api=="ccc"){
            articlesViewModel.listFeed.observe(viewLifecycleOwner){
                    data->articlesAdapter.submitList(data)
            }
        }else{
            articlesViewModel.articles.observe(viewLifecycleOwner){
                    data->articlesAdapter.submitList(data)
            }
            requestData()
        }

    }

    private fun requestData() {
        api?.let { articlesViewModel.requestData(it) }
    }
}