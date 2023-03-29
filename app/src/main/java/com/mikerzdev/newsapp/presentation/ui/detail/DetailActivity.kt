package com.mikerzdev.newsapp.presentation.ui.detail

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mikerzdev.newsapp.databinding.ActivityDetailBinding
import com.mikerzdev.newsapp.domain.model.Post
import com.mikerzdev.newsapp.presentation.model.PostParcelable
import com.mikerzdev.newsapp.presentation.model.toParcelable
import com.mikerzdev.newsapp.presentation.util.getHtmlSpannedText

private const val EXTRA_POST = "EXTRA_POST"

class DetailActivity : AppCompatActivity() {
    companion object {
        fun newIntent(mainActivity: Activity, post: Post) =
            Intent(mainActivity, DetailActivity::class.java).apply {
                putExtra(EXTRA_POST, post.toParcelable())
            }
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews(binding)
    }

    private fun initViews(binding: ActivityDetailBinding) {

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val post: PostParcelable? = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_POST, PostParcelable::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_POST)
        }

        binding.titleTextView.text = post?.title
        post?.let {
            binding.contentTextView.text = getHtmlSpannedText(it.content)
        }
        binding.excerptTextView.text = post?.excerpt
        binding.dateTextView.text = post?.date
    }


}