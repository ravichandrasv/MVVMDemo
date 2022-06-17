package com.sample.newsdemo.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sample.newsdemo.R
import com.sample.newsdemo.model.ArticlesItem

class NewsDetailFragment : Fragment() {

    lateinit var newList: ArticlesItem

    companion object {
        const val KEY_NEWS = "KEY_NEWS"

        fun newInstance(newsList: ArticlesItem?): NewsDetailFragment {
            val args = Bundle()
            args.putSerializable(KEY_NEWS, newsList)
            val fragment = NewsDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { newList = (it.getSerializable(KEY_NEWS) as ArticlesItem?)!! }
        Log.d("TAG", "onCreate: " + newList.author)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val titleText = view?.findViewById<TextView>(R.id.titleText)
        val descriptionText = view?.findViewById<TextView>(R.id.descriptionText)
        val image = view?.findViewById<ImageView>(R.id.newsImage)
        titleText.text = newList.title
        descriptionText.text = newList.description
        Glide.with(requireActivity())
            .load(newList.urlToImage)
            .into(image)
    }
}