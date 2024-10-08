package com.project.imagesearchingadvancedapplication.presentation.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.project.imagesearchingadvancedapplication.R
import com.project.imagesearchingadvancedapplication.domain.model.ImageData
import com.project.imagesearchingadvancedapplication.databinding.ImageRecyclerItemBinding


class ImageRvAdapter(private val clickListener: ClickListener): ListAdapter<ImageData, ImageRvAdapter.ImageViewHolder>(
    object: DiffUtil.ItemCallback<ImageData>() {
        override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            //Log.d("DiffUtil", "old: ${oldItem.imageUrl}, new: ${newItem.imageUrl}}")
            return oldItem.imageUrl == newItem.imageUrl
        }
        override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            //Log.d("DiffUtil", "old is same with new: ${oldItem == newItem}")
            return oldItem == newItem
        }

    }
) {

    inner class ImageViewHolder(private val binding: ImageRecyclerItemBinding): ViewHolder(binding.root) {
        fun bind(item: ImageData) {
            GlideApp.with(binding.root)
                .load(item.imageUrl)
                .into(binding.image)
            with(binding) {
                //TODO: 질문1) 어떻게 resource에 접근하여 string을 가져올까?
                fromText.text = "[${item.category.name}] ${item.from}"
                timeText.text = item.time
                favorite.visibility = if(item.isLiked) View.VISIBLE
                                    else View.INVISIBLE

                root.setOnClickListener {
                    clickListener.onImageClick(item)
                    favorite.visibility = if(item.isLiked) View.VISIBLE
                                        else View.INVISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_recycler_item, parent, false)
        return ImageViewHolder(ImageRecyclerItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface ClickListener {
        fun onImageClick(imageData: ImageData)
    }
}