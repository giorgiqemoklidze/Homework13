package com.example.homework13


import android.view.LayoutInflater
import android.view.VerifiedInputEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework13.databinding.ItemNewsRecyclerviewLayoutBinding



class NewsRecyclerViewAdapter( ) : RecyclerView.Adapter<NewsRecyclerViewAdapter.WallPostsViewHolder>() {

    val news = mutableListOf<NewsModel>()

    private lateinit var model: NewsModel







    inner class WallPostsViewHolder(private val binding: ItemNewsRecyclerviewLayoutBinding) : RecyclerView.ViewHolder(binding.root) {


        fun onBind() {

            model = news[adapterPosition]
            binding.titleTextView.text = model.titleKA.toString()
            getImg(model.cover.toString())

        }
        fun getImg (url:String){
            Glide.with(binding.root.context).load(url).into(binding.newsCoverImageView)
        }





    }


    fun delete(){
        this.news.clear()
        notifyDataSetChanged()
    }

    fun paginationSetData(items : MutableList<NewsModel>){
        this.news.addAll(items)
        notifyDataSetChanged()
    }

        fun setData(items : MutableList<NewsModel>){
        this.news.clear()
        this.news.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallPostsViewHolder {

        return WallPostsViewHolder(ItemNewsRecyclerviewLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))}

    override fun onBindViewHolder(holder: WallPostsViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount()=news.size




}


















