package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.database.ArticleEntity
import com.example.myapplication.databinding.ListItemArticleBinding
import com.example.myapplication.fragment.HomeFragmentDirections


class ArticlesAdapter : ListAdapter<ArticleEntity, RecyclerView.ViewHolder>(ArticlesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArticlesViewHolder(
            ListItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        (holder as ArticlesViewHolder).bind(item)
    }

    class ArticlesViewHolder(
        private val binding: ListItemArticleBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.article?.let { item ->
                    navigateToArticlesDetail(item, it)
                }
            }
        }

        private fun navigateToArticlesDetail(article: ArticleEntity, view: View) {
            val direction =
                HomeFragmentDirections.actionHomeFragmentToArticlesDetailFragment(article.web_view)
            view.findNavController().navigate(direction)
        }

        fun bind(item: ArticleEntity) {
            binding.apply {
                article = item
                executePendingBindings()
            }
        }
    }
}

class ArticlesDiffCallback : DiffUtil.ItemCallback<ArticleEntity>() {

    override fun areItemsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
        return oldItem.api == newItem.api
    }

    override fun areContentsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
        return oldItem == newItem
    }
}