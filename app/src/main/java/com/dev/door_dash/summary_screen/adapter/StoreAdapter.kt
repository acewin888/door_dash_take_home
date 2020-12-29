package com.dev.door_dash.summary_screen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.door_dash.data.DashStoreItem
import com.dev.door_dash.databinding.StoreItemListBinding
import com.dev.door_dash.summary_screen.adapter.viewHolder.StoreViewHolder
import io.reactivex.subjects.PublishSubject

/**
 * Adapter for RecyclerView
 */
class StoreAdapter(
    private val stores: MutableList<DashStoreItem> = mutableListOf(),
    private val publishSubject: PublishSubject<DashStoreItem>
) : RecyclerView.Adapter<StoreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val binding =
            StoreItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoreViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return stores.size
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        holder.bind(stores[position], publishSubject)
    }

    /**
     * update list in the adapter
     */
    fun updateList(list: List<DashStoreItem>) {
        stores.clear()
        stores.addAll(list)
        notifyDataSetChanged()
    }
}