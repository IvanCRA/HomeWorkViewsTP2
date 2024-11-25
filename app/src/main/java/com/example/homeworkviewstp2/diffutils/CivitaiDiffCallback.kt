package com.example.homeworkviewstp2.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.homeworkviewstp2.model.Civitai

class CivitaiDiffCallback(
    private val oldList: List<Civitai>,
    private val newList: List<Civitai>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}