package com.example.homeworkviewstp2.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.homeworkviewstp2.R
import com.example.homeworkviewstp2.diffutils.CivitaiDiffCallback
import com.example.homeworkviewstp2.holder.CivitaiViewHolder
import com.example.homeworkviewstp2.model.Civitai
import java.util.Collections.addAll

class CivitaiAdapter(private val context: Context, private var civitaiList: MutableList<Civitai>, val listener: Listener) : RecyclerView.Adapter<CivitaiViewHolder>() {
    private val set = ConstraintSet()
    private val requestOptions = RequestOptions().placeholder(R.drawable.baseline_photo_24)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CivitaiViewHolder {
        return CivitaiViewHolder(parent)
    }

    override fun getItemCount() = civitaiList.size
    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: CivitaiViewHolder, position: Int) {
        val listItem = civitaiList[position]
            Glide.with(holder.itemView.context)
                .setDefaultRequestOptions(requestOptions)
                .load(listItem.url)
                .into(holder.image)
        val ratio = String.format("%d:%d", listItem.width,listItem.height)
        set.clone(holder.parentContsraint)
        set.setDimensionRatio(holder.image.id, ratio)
        set.applyTo(holder.parentContsraint)
        holder.bind(civitaiList[position], listener)
    }
    fun updateData(newList: List<Civitai>) {
        val diffCallback = CivitaiDiffCallback(civitaiList, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        civitaiList.clear()
        civitaiList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    interface Listener {
        fun onClick(civitai: Civitai)
    }
}