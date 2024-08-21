package com.example.my.project.paginationandroid.view.loader

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.my.project.paginationandroid.R
import com.example.my.project.paginationandroid.view.loader.adapter.LoaderDoggoImageAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@ExperimentalPagingApi
class LoaderFragment : Fragment(R.layout.fragment_loader) {
    private lateinit var rvDoggoLoader: RecyclerView
    private lateinit var loaderViewModel: LoaderViewModel
    private lateinit var adapter: LoaderDoggoImageAdapter
    private lateinit var loaderStateAdapter: LoaderStateAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMembers()
        setUpViews(view)
        fetchDoggoImages()
    }


    private fun fetchDoggoImages() {
        lifecycleScope.launch {
            loaderViewModel.fetchDoggoImages().distinctUntilChanged().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun initMembers() {
        loaderViewModel = defaultViewModelProviderFactory.create(LoaderViewModel::class.java)
        adapter = LoaderDoggoImageAdapter()
        loaderStateAdapter = LoaderStateAdapter { adapter.retry() }
    }

    private fun setUpViews(view: View) {
        rvDoggoLoader = view.findViewById(R.id.rvDoggoLoader)
        rvDoggoLoader.layoutManager = GridLayoutManager(context, 2)
        rvDoggoLoader.adapter = adapter.withLoadStateFooter(loaderStateAdapter)
    }

}