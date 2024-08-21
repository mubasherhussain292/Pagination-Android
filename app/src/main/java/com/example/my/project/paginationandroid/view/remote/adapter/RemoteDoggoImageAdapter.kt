package com.example.my.project.paginationandroid.view.remote.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.my.project.paginationandroid.R

class RemoteDoggoImageAdapter : PagingDataAdapter<String, RecyclerView.ViewHolder>(RemoteDoggoList) {

    companion object {
        private val RemoteDoggoList = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem


        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? DoggoImageViewHolder)?.bind(item = getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DoggoImageViewHolder.getInstance(parent)
    }

    class DoggoImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            //get instance of the DoggoImageViewHolder
            fun getInstance(parent: ViewGroup): DoggoImageViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_doggo_image_view, parent, false)
                return DoggoImageViewHolder(view)
            }
        }

        private var ivDoggoMain: ImageView = view.findViewById(R.id.ivDoggoMain)

        fun bind(item: String?) {
            ivDoggoMain.load(item) {
                placeholder(R.drawable.doggo_placeholder)
            }
        }
    }


}
