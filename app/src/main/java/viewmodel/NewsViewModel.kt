package com.sample.newsdemo.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.newsdemo.model.NewsResponse
import com.sample.newsdemo.repository.DataRepository
import org.koin.standalone.KoinComponent


class NewsViewModel(val dataRepository: DataRepository) : ViewModel(), KoinComponent {

    var listOfProducts = MutableLiveData<NewsResponse>()

    init {
        listOfProducts.value = NewsResponse()
    }

    fun getProducts() {
        dataRepository.getProducts(object : DataRepository.OnNewsData {
            override fun onSuccess(data: NewsResponse) {
                listOfProducts.value = data
            }

            override fun onFailure(e: String) {
                Log.d("TAG", "onFailure: "+e)
                //REQUEST FAILED
            }
        })
    }
}