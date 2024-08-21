package com.example.my.project.paginationandroid.view.loader.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.my.project.paginationandroid.R
import com.example.my.project.paginationandroid.model.ImagesModel

class LoaderDoggoImageAdapter :
    PagingDataAdapter<ImagesModel, RecyclerView.ViewHolder>(REPO_COMPARATOR) {

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<ImagesModel>() {
            override fun areItemsTheSame(oldItem: ImagesModel, newItem: ImagesModel) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ImagesModel, newItem: ImagesModel) =
                oldItem.id == newItem.id
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? DoggoImageViewHolder)?.bind(item = getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DoggoImageViewHolder.getInstance(parent)
    }

    /**
     * view holder class for doggo item
     */
    class DoggoImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {
            //get instance of the DoggoImageViewHolder
            fun getInstance(parent: ViewGroup): DoggoImageViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.item_doggo_image_view, parent, false)
                return DoggoImageViewHolder(view)
            }
        }

        var ivDoggoMain: ImageView = view.findViewById(R.id.ivDoggoMain)

        fun bind(item: ImagesModel?) {
            //loads image from network using coil extension function
            ivDoggoMain.load(item?.url) {
                placeholder(R.drawable.doggo_placeholder)
            }
        }

    }

}