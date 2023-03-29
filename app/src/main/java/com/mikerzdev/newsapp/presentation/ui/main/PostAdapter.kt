package com.mikerzdev.newsapp.presentation.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.mikerzdev.newsapp.databinding.AdapterPostListBinding
import com.mikerzdev.newsapp.databinding.LayoutEmptyBinding
import com.mikerzdev.newsapp.domain.model.Post
import com.mikerzdev.newsapp.presentation.util.getHtmlSpannedText

private const val TYPE_EMPTY = 0
private const val TYPE_ITEM = 1

class PostAdapter(
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private val itemList: ArrayList<Post> = arrayListOf()

    val items : List<Post>
        get() = itemList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_ITEM -> {
                val binding =
                    AdapterPostListBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ViewHolder(binding)
            }
            else -> {
                val binding =
                    LayoutEmptyBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (itemList.isNotEmpty()) {
            val item = itemList[position]
            holder.bind(item)
        }

    }

    override fun getItemCount(): Int {
        return if (itemList.isEmpty()) {
            1
        } else {
            itemList.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemList.isEmpty()) {
            TYPE_EMPTY
        } else {
            TYPE_ITEM
        }
    }

    fun setAllItems(items: List<Post>) {
        itemList.clear()
        itemList.addAll(items)
        notifyItemRangeChanged(0, items.size)
    }

    fun sortItemsBy(order: SortBy) {
        when (order) {
            SortBy.DATE -> {
                itemList.sortBy { it.dateTime }
            }
            SortBy.TITLE -> {
                itemList.sortBy { it.title }
            }
        }
        notifyItemRangeChanged(0, itemList.size)
    }

    inner class ViewHolder(private val binding: ViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            val postBinding = binding as AdapterPostListBinding
            postBinding.apply {
                titleTextView.text = post.title
                contentTextView.text = getHtmlSpannedText(post.content)
                excerptTextView.text = post.excerpt
                dateTextView.text = post.date
            }
            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(adapterPosition, post)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(index: Int, post: Post)
    }

    enum class SortBy {
        DATE,
        TITLE
    }
}