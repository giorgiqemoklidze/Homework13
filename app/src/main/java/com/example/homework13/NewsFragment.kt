package com.example.homework13

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework13.databinding.NewsFragmentBinding


class NewsFragment : Fragment() {

    private lateinit var binding : NewsFragmentBinding

    private lateinit var newsRecyclerViewAdapter: NewsRecyclerViewAdapter



    var page = 1
    private var isLoading = false
    val limit = 10


    private val viewModel: NewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewsFragmentBinding.inflate(layoutInflater,container,false)
        init()
        return binding.root
    }

    private fun init(){

        viewModel.init()
        initRecycler()
    }

    private fun initRecycler(){
        binding.newsRecyclerView.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        newsRecyclerViewAdapter = NewsRecyclerViewAdapter(binding.newsRecyclerView)
        binding.newsRecyclerView.adapter = newsRecyclerViewAdapter
        observes()
        binding.newsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){

        } )
    }

    private fun observes(){
        viewModel._itemsLiveData.observe(viewLifecycleOwner, Observer {
            newsRecyclerViewAdapter.setData(it as MutableList<NewsModel>)
        })



    }



    private fun pagination(){

    }








}