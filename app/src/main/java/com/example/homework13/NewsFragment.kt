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
        val manager =  LinearLayoutManager(binding.root.context, LinearLayoutManager.VERTICAL, false)
        binding.newsRecyclerView.layoutManager = manager
        newsRecyclerViewAdapter = NewsRecyclerViewAdapter()
        binding.newsRecyclerView.adapter = newsRecyclerViewAdapter

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

                var currentItems :Int = manager.childCount
                var totalItems :Int = manager.itemCount
                var scrollOUtItems :Int = manager.findFirstVisibleItemPosition()



                if (isScroling && (currentItems + scrollOUtItems == totalItems)){
                    binding.progressbar.visibility = View.VISIBLE



                    viewModel._itemsLiveData.observe(viewLifecycleOwner, Observer {

                        when(it.status){
                            Result.Status.SUCCESS ->{
                                newsRecyclerViewAdapter.paginationSetData(it.data!!.toMutableList())
                                binding.progressbar.visibility = View.GONE
                            }

                            Result.Status.ERROR ->{
                                Toast.makeText(requireActivity(),it.message,Toast.LENGTH_LONG).show()

                            }

                        }

                    })
                    isScroling = false



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