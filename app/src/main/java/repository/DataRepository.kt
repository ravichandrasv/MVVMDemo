package com.sample.newsdemo.repository

import com.sample.newsdemo.NetWorkApi
import com.sample.newsdemo.model.NewsResponse
import retrofit2.Call
import retrofit2.Response

class DataRepository(val netWorkApi: NetWorkApi) {

    fun getProducts(onNewsData: OnNewsData) {
        netWorkApi.getNews().enqueue(object : retrofit2.Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                onNewsData.onSuccess((response.body() as NewsResponse))
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                onNewsData.onFailure(t.toString())
            }
        })
    }

    interface OnNewsData {
        fun onSuccess(data: NewsResponse)
        fun onFailure(e: String)
    }
}

