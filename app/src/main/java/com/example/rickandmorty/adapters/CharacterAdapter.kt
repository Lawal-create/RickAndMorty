package com.example.rickandmorty.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.databinding.RecyclerListItemBinding
import com.example.rickandmorty.models.Results

class CharacterAdapter(val context:Context):
        PagingDataAdapter<Results, CharacterAdapter.CharacterViewHolder>(CharacterComparator){

    object CharacterComparator : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.id == newItem.id
        }

    }


    class CharacterViewHolder(val binding:RecyclerListItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, character:Results)
        {
            Glide.with(context)
                    .load(character.image!!)
                    .into(binding.image)
        }

    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(context,getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding:RecyclerListItemBinding = RecyclerListItemBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
        )
        return CharacterViewHolder(binding)
    }


}