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


class ArticlesAdapter : ListAdapter<ArticleEntity, RecyclerView.ViewHolder>(PlayersDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlayersViewHolder(
            ListItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val player = getItem(position)
        (holder as PlayersViewHolder).bind(player)
    }


    class PlayersViewHolder(
        private val binding: ListItemArticleBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.article?.let { player ->
                    navigateToPlayerDetail(player, it)
                }
            }
        }

        private fun navigateToPlayerDetail(player: ArticleEntity, view: View) {
            val direction =
                HomeFragmentDirections.actionHomeFragmentToPlayerDetailFragment(player.web_view)
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

class PlayersDiffCallback : DiffUtil.ItemCallback<ArticleEntity>() {

    override fun areItemsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
        return oldItem.api == newItem.api
    }

    override fun areContentsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
        return oldItem == newItem
    }
}