package com.sample.newsdemo.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.newsdemo.R
import com.sample.newsdemo.model.ArticlesItem


class NewsListAdapter(private val newsList: List<ArticlesItem?>?, val context: Context) :
    RecyclerView.Adapter<NewsListAdapter.ViewHolder>() {
    private var onItemClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.adapter_list, p0, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return newsList?.size!!
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.newsContent?.text = newsList!![position]?.title
        viewHolder.newsDetailContent?.text = newsList!![position]?.description
        Glide.with(context)
            .load(newsList!![position]?.urlToImage)
            .into(viewHolder.image)
        viewHolder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(viewHolder.itemView, position)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsContent = itemView.findViewById<TextView>(R.id.newsText)
        val newsDetailContent = itemView.findViewById<TextView>(R.id.newsDetailText)
        val image = itemView.findViewById<ImageView>(R.id.imageView)

    }


    fun setItemClickListener(clickListener: ItemClickListener) {
        onItemClickListener = clickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
}