package com.ahmedmhassaan.orangetask.presentation.ui.news.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.ahmedmhassaan.domain.models.DomainArticle
import com.ahmedmhassaan.orangetask.presentation.base.fragment.BaseBindFragment
import com.ahmedmhassaan.orangetask.R
import com.ahmedmhassaan.orangetask.databinding.FragmentArticleDetailsBinding
import com.ahmedmhassaan.orangetask.utils.ToastMessage

class FragmentArticleDetails : BaseBindFragment<FragmentArticleDetailsBinding>(),
    View.OnClickListener {

    private lateinit var article: DomainArticle
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.let {
            article = FragmentArticleDetailsArgs.fromBundle(it).article
            binding.article = article

        } ?: kotlin.run {
            ToastMessage.error(getString(R.string.no_articles_founded))
            findNavController()
                .popBackStack()
        }

        binding.listener = this
    }

    override fun getLayoutId() = R.layout.fragment_article_details
    override fun onClick(p0: View?) {
        when (p0) {
            binding.btnOpenInBrowser -> {
                val intent = Intent()
                    .setAction(Intent.ACTION_VIEW)
                    .setData(Uri.parse(article.url))
                startActivity(intent)

            }
        }
    }
}