package com.example.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var playerDetailBinding: FragmentDetailBinding

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Bind layout views to Architecture Components.
        // https://developer.android.com/topic/libraries/data-binding/architecture
        playerDetailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        playerDetailBinding.lifecycleOwner = viewLifecycleOwner

        return playerDetailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val webView = playerDetailBinding.webView
        webView.loadUrl(args.uri)
        webView.webViewClient= object :WebViewClient(){
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                view?.loadUrl(request.toString())
                return true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}