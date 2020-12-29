package com.dev.door_dash.summary_screen.adapter.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.door_dash.data.DashStore
import com.dev.door_dash.databinding.StoreItemListBinding
import io.reactivex.subjects.PublishSubject

/**
 * Store ViewHolder for RecyclerView
 */
class StoreViewHolder(
    private val binding: StoreItemListBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(store: DashStore, publishSubject: PublishSubject<DashStore>) {
        binding.storeName.text = store.name
        binding.storeDescription.text = "Description"
        binding.storeDistance.text = "1 mile"
        Glide.with(binding.root.context).load(store.cover_img_url).into(binding.storeIcon)

        binding.root.setOnClickListener {
            publishSubject.onNext(store)
        }
    }
}