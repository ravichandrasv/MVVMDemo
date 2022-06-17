package com.sample.newsdemo.view

import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.newsdemo.R
import com.sample.newsdemo.model.NewsResponse
import com.sample.newsdemo.viewmodel.NewsViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class NewsListFragment : Fragment() {

    private val newsListModel: NewsViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }


    override fun onStart() {
        super.onStart()

        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(requireActivity())


        newsListModel.getProducts()
        newsListModel.listOfProducts.observe(
            this,
            Observer(function = fun(newsList: NewsResponse?) {
                newsList?.let {
                    Log.d("TAG", "dd: " + newsList.articles?.size)
                    var newsListAdapter: NewsListAdapter =
                        NewsListAdapter(newsList.articles, requireActivity())
                    if (newsList.articles != null) {
                        recyclerView.adapter = newsListAdapter
                    }
                    newsListAdapter.setItemClickListener(object :
                        NewsListAdapter.ItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
                            val newFragment =
                                NewsDetailFragment.newInstance(newsList.articles!![position])
                            val transaction = parentFragmentManager!!.beginTransaction()
                            transaction.replace(R.id.frag_container, newFragment)
                            transaction.addToBackStack(null)
                            transaction.commit()
                        }
                    })
                }
            })
        )
    }


}
