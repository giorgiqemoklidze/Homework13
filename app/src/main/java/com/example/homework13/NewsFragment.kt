package com.example.homework13

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework13.databinding.NewsFragmentBinding
import kotlin.properties.Delegates


class NewsFragment : Fragment() {

    private lateinit var binding : NewsFragmentBinding

    private lateinit var newsRecyclerViewAdapter: NewsRecyclerViewAdapter

    private var isScroling = false

     var currentItems :Int = 7
     var totalItems :Int = 10
     var scrollOUtItems :Int = 3


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
        newsRecyclerViewAdapter = NewsRecyclerViewAdapter()
        binding.newsRecyclerView.adapter = newsRecyclerViewAdapter
        val manager = LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        observes()
        binding.newsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){

                    isScroling = true

                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)


                currentItems = manager.childCount
                totalItems = manager.itemCount
                scrollOUtItems = manager.findFirstVisibleItemPosition()


                if (isScroling && (currentItems + scrollOUtItems == totalItems)){

                    isScroling = false

                    newsRecyclerViewAdapter.delete()

                }

            }

        } )
    }

    private fun observes(){
        viewModel._itemsLiveData.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Result.Status.SUCCESS ->{
                    newsRecyclerViewAdapter.setData(it.data!!.toMutableList())
                    binding.progressbar.visibility = View.GONE
                }

                Result.Status.ERROR ->{
                    Toast.makeText(requireActivity(),it.message,Toast.LENGTH_LONG).show()

                }

            }

        })



    }



    private fun pagination(){

    }








}