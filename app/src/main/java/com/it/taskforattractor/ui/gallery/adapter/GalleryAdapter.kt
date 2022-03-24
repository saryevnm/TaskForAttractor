package com.it.taskforattractor.ui.gallery.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.it.taskforattractor.databinding.ItemPhotoBinding
import com.it.taskforattractor.util.loadImage

class GalleryAdapter :
    RecyclerView.Adapter<GalleryAdapter.ItemPhotoViewHolder>() {
    var list = emptyList<Uri>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPhotoViewHolder {
        return ItemPhotoViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemPhotoViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ItemPhotoViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(uri: Uri) {
            binding.imgPhoto.loadImage(uri)
        }
    }
}