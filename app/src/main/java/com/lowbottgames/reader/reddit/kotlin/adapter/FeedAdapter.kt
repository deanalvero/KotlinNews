package com.lowbottgames.reader.reddit.kotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lowbottgames.reader.reddit.kotlin.R
import com.lowbottgames.reader.reddit.kotlin.model.Feed
import com.squareup.picasso.Picasso

class FeedAdapter : RecyclerView.Adapter<FeedViewHolder>() {

    var items: List<Feed>? = null
    var listener: FeedAdapterListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed, parent, false)
        return FeedViewHolder(view, object : FeedViewHolder.OnClickListener {
            override fun onClick(position: Int) {
                items?.get(position)?.let {
                    listener?.onClickItem(it)
                }
            }
        })
    }

    override fun getItemCount(): Int = items?.size ?: 0

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val item = items?.get(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    interface FeedAdapterListener {
        fun onClickItem(feed: Feed)
    }

}

class FeedViewHolder(itemView: View, onClickListener: OnClickListener?) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: Feed) {
        textView.text = item.title

        val thumbnail = item.thumbnail
        if (thumbnail != null && thumbnail.isNotEmpty()) {
            imageView.visibility = View.VISIBLE
            Picasso.get()
                .load(thumbnail)
                .into(imageView)
        } else {
            imageView.visibility = View.GONE
        }
    }

    private val textView: TextView = itemView.findViewById(R.id.textView)
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)

    init {
        itemView.setOnClickListener {
            onClickListener?.onClick(adapterPosition)
        }
    }

    interface OnClickListener {
        fun onClick(position: Int)
    }
}