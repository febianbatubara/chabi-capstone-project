package com.chabi.android.chabiapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chabi.android.chabiapp.R
import com.chabi.android.chabiapp.databinding.ListItemBinding

class ListVideoAdapter(private val context: Context) :
    RecyclerView.Adapter<ListVideoAdapter.VideoViewHolder>() {

    private var listUrl = listOf<String>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setData(newList: List<String>) {
        listUrl = newList
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): VideoViewHolder {
        val binding =
            ListItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return VideoViewHolder(binding)
    }

    override fun getItemCount() = listUrl.size - 1

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoItem = listUrl[position]

        val videoId = videoItem.split("=")[1].split("&")[0]
        val thumbnailUrl = "https://img.youtube.com/vi/$videoId/0.jpg"

        with(holder) {
            Glide.with(context)
                .load(thumbnailUrl)
                .centerCrop()
                .placeholder(R.color.colorPrimary)
                .into(binding.ivVideoThumbnail)

            binding.card.setOnClickListener {
                onItemClickCallback?.onItemClicked(videoItem)
            }
        }
    }

    inner class VideoViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onItemClicked(data: String)
    }
}