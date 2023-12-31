package com.ilisi.myimagesapplication.data

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ilisi.myimagesapplication.R
import com.ilisi.myimagesapplication.model.MyImageResponse
import com.squareup.picasso.Picasso

class ImageAdapter(private val images: List<MyImageResponse>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        val userTextView: TextView = itemView.findViewById(R.id.userTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_card_layout, parent, false)
        return ImageViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        Picasso.get().load(image.urls?.small).into(holder.imageView)
        holder.descriptionTextView.text = (image.description ?: image.altDescription ?: "No description available").toString()
        holder.userTextView.text = "@" + (image.user?.name ?: "Unknown")
    }

    override fun getItemCount() = images.size
}