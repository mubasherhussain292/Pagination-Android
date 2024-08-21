package com.example.my.project.paginationandroid.view.remote

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my.project.paginationandroid.R
import com.example.my.project.paginationandroid.view.remote.adapter.RemoteDoggoImageAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@ExperimentalPagingApi
class RemoteFragment : Fragment(R.layout.fragment_remote) {
    private lateinit var rvDoggoRemote: RecyclerView
    private lateinit var remoteViewModel: RemoteViewModel
    private lateinit var adapter: RemoteDoggoImageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMembers()
        setUpViews(view)
        fetchDoggoImages()

    }

    private fun fetchDoggoImages() {
        lifecycleScope.launch {
            remoteViewModel.fetchDoggoImages().distinctUntilChanged().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun initMembers() {
        remoteViewModel = defaultViewModelProviderFactory.create(RemoteViewModel::class.java)
        adapter = RemoteDoggoImageAdapter()
    }

    private fun setUpViews(view: View) {
        rvDoggoRemote = view.findViewById(R.id.rvDoggoRemote)
        rvDoggoRemote.layoutManager = GridLayoutManager(context, 2)
        rvDoggoRemote.adapter = adapter
    }


}


