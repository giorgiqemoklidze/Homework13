package com.example.homework13


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val itemsLiveData = MutableLiveData<List<NewsModel>>().apply {
        mutableListOf<NewsModel>()
    }

    val _itemsLiveData : MutableLiveData<List<NewsModel>> = itemsLiveData

    private val loading = MutableLiveData<Boolean>()

    fun init(){
        CoroutineScope(Dispatchers.IO).launch {
            getItems()
        }
    }

    private suspend fun  getItems(){

        val itemsList = RetrofitService.RetrofitService().getList()

        if (itemsList.isSuccessful){
            val news = itemsList.body()
            itemsLiveData.postValue(news)
        }else {
            itemsList.code()
        }



    }







}