package com.islam.listing.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.islam.listing.databinding.ItemUniversityBinding
import com.islam.listing.domain.entity.UniversityEntity


class UniversityListAdapter(val onUniversityClicked: (UniversityEntity) -> Unit) :
    ListAdapter<UniversityEntity, UniversityListAdapter.UniversityViewHolder>(UniversityDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        val binding =
            ItemUniversityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UniversityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        val university = getItem(position)
        holder.bind(university)
        holder.itemView.setOnClickListener {
            onUniversityClicked(university)
        }
    }

    inner class UniversityViewHolder(private val binding: ItemUniversityBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(university: UniversityEntity) {
            binding.apply {
                tvUniversityName.text = university.name
                tvUniversityState.text = university.stateProvince
            }
        }
    }
}

class UniversityDiffCallback : DiffUtil.ItemCallback<UniversityEntity>() {
    override fun areItemsTheSame(oldItem: UniversityEntity, newItem: UniversityEntity): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: UniversityEntity, newItem: UniversityEntity): Boolean {
        return oldItem == newItem
    }
}
