package com.it.taskforattractor.ui.greeting.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.it.taskforattractor.databinding.ItemCompanyBinding
import com.it.taskforattractor.model.Company

class GreetingAdapter :
    RecyclerView.Adapter<GreetingAdapter.ItemCompanyViewHolder>() {
    var list = emptyList<Company>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemCompanyViewHolder {
        return ItemCompanyViewHolder(
            ItemCompanyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemCompanyViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class ItemCompanyViewHolder(private val binding: ItemCompanyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(company: Company) {
            binding.apply {
                name.text = company.name
                position.text = company.position
            }
        }
    }
}