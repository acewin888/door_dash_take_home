package com.dev.door_dash.summary_screen.adapter.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev.door_dash.data.DashStoreItem
import com.dev.door_dash.databinding.StoreItemListBinding
import io.reactivex.subjects.PublishSubject

/**
 * Store ViewHolder for RecyclerView
 */
class StoreViewHolder(
    private val binding: StoreItemListBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(store: DashStoreItem, publishSubject: PublishSubject<DashStoreItem>) {
        binding.storeName.text = store.name
        binding.storeDescription.text = store.short_description
        binding.storeDistance.text = store.location_status
        Glide.with(binding.root.context).load(store.image_url).into(binding.storeIcon)

        binding.root.setOnClickListener {
            publishSubject.onNext(store)
        }
    }
}