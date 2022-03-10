package com.kumar.test.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kumar.test.R
import com.kumar.test.domain.model.PhotoDomainModel

class PictureAdapter(private val clickListener: (Int) -> Unit) :
    RecyclerView.Adapter<PictureAdapter.PictureViewHolder>() {

    private val pictureList: MutableList<PhotoDomainModel> =
        emptyList<PhotoDomainModel>().toMutableList()

    fun setPictureList(pictureList: List<PhotoDomainModel>) {
        this.pictureList.clear()
        this.pictureList.addAll(pictureList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_item_picture, parent, false)
        return PictureViewHolder(view)
    }

    override fun getItemCount(): Int = pictureList.size

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        val photo = pictureList[position]

        holder.layout.setOnClickListener {
            clickListener(photo.id)
        }

        holder.title.text = photo.title

        holder.thumbnail.load(photo.thumbnailUrl) {
            placeholder(R.drawable.ic_launcher_foreground)
        }
    }

    class PictureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout: ConstraintLayout = itemView.findViewById(R.id.item_layout_picture)
        val thumbnail: ImageView = itemView.findViewById(R.id.item_thumbnail)
        val title: TextView = itemView.findViewById(R.id.item_title)
    }
}