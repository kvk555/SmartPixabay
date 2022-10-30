package com.example.smartpixabay.presentation.home.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.smartpixabay.R
import com.example.smartpixabay.databinding.HomeImageItemBinding
import com.example.smartpixabay.domain.entities.ImageEntity

class ImageClick(val block: (ImageEntity) -> Unit) {
    fun onClick(video: ImageEntity) = block(video)
}

class HomeImageAdapter(private val callBack: ImageClick) : RecyclerView.Adapter<ImageViewHolder>() {
    private val images = mutableListOf<ImageEntity>()

    fun setItems(newItems: List<ImageEntity>) {
        images.clear()
        images.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val withDataBinding: HomeImageItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ImageViewHolder.LAYOUT,
            parent,
            false
        )
        return ImageViewHolder(withDataBinding)
    }

    override fun getItemCount() = images.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.image = images[position]
            it.imageCallback = callBack
        }
    }
}

class ImageViewHolder(val viewDataBinding: HomeImageItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {

    companion object {
        @LayoutRes
        val LAYOUT = R.layout.home_image_item
    }
}

/**
 * Binding adapter used to display images from URL using Glide
 */
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    Glide.with(imageView.context)
        .load(url)
        .placeholder(R.drawable.baseline_image)
        .error(R.drawable.baseline_image)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(imageView)
}
