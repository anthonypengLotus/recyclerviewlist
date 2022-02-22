package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
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


    private val articlesViewModel: ArticleListViewModel by activityViewModels()

    private lateinit var articlesAdapter: ArticlesAdapter

    companion object {
        fun newInstance(api: String) = ArticleListFragment(api)
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
        articlesViewModel.requestData(api)
    }
}