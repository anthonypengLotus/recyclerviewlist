package com.example.myapplication.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.R
import com.example.myapplication.glidemodeule.GlideApp

@BindingAdapter("backgroundFromArticle")
fun backgroundFromArticle(view: ImageView, code: String?) {
    code?.let {
        GlideApp.with(view.context)
            .load(code)
            .error(R.drawable.ic_launcher_background)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}