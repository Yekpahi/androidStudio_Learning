package com.example.relativapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class PostsAdapter(
    var mContext: Context,
    var resource: Int,
    var values: ArrayList<Post>
) : ArrayAdapter<Post>(mContext, resource, values) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val post = values[position]
        val itemView = LayoutInflater.from(mContext).inflate(resource, parent, false)
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        var tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        val imagePost = itemView.findViewById<ImageView>(R.id.imagePost)
        tvTitle.text = post.title
        tvDescription.text = post.description
        imagePost.setImageResource(post.image)
        return itemView
    }
}