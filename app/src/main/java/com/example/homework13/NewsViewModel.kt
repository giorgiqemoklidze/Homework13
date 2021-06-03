package com.example.homework13


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val itemsLiveData = MutableLiveData<Result<List<NewsModel>>>().apply {
        mutableListOf<NewsModel>()
    }

    val _itemsLiveData : MutableLiveData<Result<List<NewsModel>>> = itemsLiveData


    fun init(){
        CoroutineScope(Dispatchers.IO).launch {
            getItems()
        }
    }

    private suspend fun  getItems(){

        val itemsList = RetrofitService.RetrofitService().getList()

        if (itemsList.isSuccessful){
            val news = itemsList.body()
            itemsLiveData.postValue(Result.isSucsessfull(news!!))
        }else {
            itemsLiveData.postValue(Result.error("ragaca_eroriaaa"))
        }



    }







}