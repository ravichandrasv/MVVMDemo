package com.sample.newsdemo


import com.sample.newsdemo.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface NetWorkApi{

    @GET("everything?q=tesla&from=2022-05-17&sortBy=publishedAt&apiKey=044395600895459fa4de8ca680104a97")
    fun getNews(): Call<NewsResponse>

}