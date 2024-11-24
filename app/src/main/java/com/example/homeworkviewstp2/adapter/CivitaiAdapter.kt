package com.example.homeworkviewstp2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkviewstp2.holder.CivitaiViewHolder
import com.example.homeworkviewstp2.model.Civitai
import com.squareup.picasso.Picasso

class CivitaiAdapter(private val context: Context, private val civitaiList: MutableList<Civitai>) : RecyclerView.Adapter<CivitaiViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CivitaiViewHolder {
        return CivitaiViewHolder(parent)
    }

    override fun getItemCount() = civitaiList.size

    override fun onBindViewHolder(holder: CivitaiViewHolder, position: Int) {
        val listItem = civitaiList[position]
        holder.bind(listItem)

        Picasso.get().load(civitaiList[position].url).into(holder.image)


    }
}