package com.example.homeworkviewstp2.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.homeworkviewstp2.R
import com.example.homeworkviewstp2.model.Civitai

class CivitaiViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)) {

    val image = itemView.findViewById<ImageView>(R.id.image_view)
    val parentContsraint = itemView.findViewById<ConstraintLayout>(R.id.parent_contsraint)
    fun bind(listItem: Civitai) {

    }

}