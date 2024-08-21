package com.example.my.project.paginationandroid.view.Room

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my.project.paginationandroid.R
import com.example.my.project.paginationandroid.view.loader.LoaderStateAdapter
import com.example.my.project.paginationandroid.view.loader.adapter.LoaderDoggoImageAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@ExperimentalPagingApi
class RoomFragment : Fragment(R.layout.fragment_room2) {
    private lateinit var rvDoggoRoom: RecyclerView
    private lateinit var roomViewModel: RoomViewModel
    private lateinit var adapter: LoaderDoggoImageAdapter
    private lateinit var loaderStateAdapter: LoaderStateAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        initMembers()
        setUpViews(view)
        fetchDoggoImages()
    }

    private fun fetchDoggoImages() {
        Log.d(TAG, "fetchDoggoImages: ")
        lifecycleScope.launch {
            Log.d(TAG, "lifecycleScope: ")
            roomViewModel.fetchImages().distinctUntilChanged().collectLatest {
                Log.d(TAG, "fetchImages: $it")
                adapter.submitData(it)
            }
        }
    }

    private fun initMembers() {
        roomViewModel = defaultViewModelProviderFactory.create(RoomViewModel::class.java)
        adapter = LoaderDoggoImageAdapter()
        loaderStateAdapter = LoaderStateAdapter { adapter.retry() }
    }

    private fun setUpViews(view: View) {
        rvDoggoRoom = view.findViewById(R.id.rvDoggoRoom)
        rvDoggoRoom.layoutManager = GridLayoutManager(context, 2)
        rvDoggoRoom.adapter = adapter.withLoadStateFooter(loaderStateAdapter)
    }

}

private const val TAG = "RoomFragment"