package com.mikerzdev.newsapp.presentation.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.PopupMenu
import com.mikerzdev.newsapp.R
import com.mikerzdev.newsapp.databinding.ActivityMainBinding
import com.mikerzdev.newsapp.domain.model.Post
import com.mikerzdev.newsapp.presentation.ui.detail.DetailActivity
import com.mikerzdev.newsapp.presentation.util.bind
import com.mikerzdev.newsapp.presentation.util.createErrorDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter = PostAdapter(object : PostAdapter.OnItemClickListener {
        override fun onItemClick(index: Int, post: Post) {
            val detailIntent = DetailActivity.newIntent(this@MainActivity, post)
            startActivity(detailIntent)
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews(binding)
        initLiveData()
    }

    private fun initViews(binding: ActivityMainBinding) {
        binding.recyclerViewPostList.adapter = adapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getFeed()
        }
    }

    private fun initLiveData() {
        viewModel.feedLiveData.bind(this) { feed ->
            feed?.posts?.let {
                binding.swipeRefreshLayout.isRefreshing = false
                adapter.setAllItems(it)
            }
        }
        viewModel.appError.bind(this) { error ->
            binding.swipeRefreshLayout.isRefreshing = false
            error?.let {
                val errorDialog = createErrorDialog(it)
                errorDialog.show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main_sort, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_sort -> {
                val popupMenu = PopupMenu(this, findViewById(R.id.menu_sort))
                popupMenu.menuInflater.inflate(R.menu.menu_main_sort_options, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.sort_by_title -> {
                            adapter.sortItemsBy(PostAdapter.SortBy.TITLE)
                            true
                        }
                        R.id.sort_by_date -> {
                            adapter.sortItemsBy(PostAdapter.SortBy.DATE)
                            true
                        }
                        else -> false
                    }
                }
                popupMenu.show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}