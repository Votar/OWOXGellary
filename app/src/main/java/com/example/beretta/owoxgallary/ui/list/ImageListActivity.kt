package com.example.beretta.owoxgallary.ui.list

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.View
import com.example.beretta.owoxgallary.R
import com.example.beretta.owoxgallary.arch.view.BaseArchActivity
import com.example.beretta.owoxgallary.models.network.response.PhotoRest
import com.example.beretta.owoxgallary.ui.details.DetailsActivity
import com.example.beretta.owoxgallary.ui.list.adapter.ListPhotoAdapter
import com.example.beretta.owoxgallary.ui.list.contract.ImageListContract
import kotlinx.android.synthetic.main.activity_main.*

class ImageListActivity : BaseArchActivity<ImageListContract.View, ImageListContract.ViewModel>(), ImageListContract.View {

    private val onPhotoClickListener = object : ListPhotoAdapter.OnItemClickListener {
        override fun photoClicked(photo: PhotoRest) {
            showDetails(photo)
        }
    }
    val adapter by lazy { ListPhotoAdapter(this, onPhotoClickListener) }
    var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_main)
        super.onCreate(savedInstanceState)
        setSupportActionBar(activity_list_photo_toolbar)
        setupLayout()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView
        searchView?.setOnQueryTextListener(searchCallbackListener)
        return true
    }

    private fun setupLayout() {
        activity_list_photo_swipe_refresh.setOnRefreshListener(refreshListener)
        activity_list_photo_try_again_bth.setOnClickListener { getViewModel().refreshList("") }
        activity_list_photo_images_list_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        activity_list_photo_images_list_recycler.adapter = adapter
    }

    override fun showProgress() {
        activity_list_photo_swipe_refresh.isRefreshing = true
    }

    override fun hideProgress() {
        activity_list_photo_swipe_refresh.isRefreshing = false

    }

    override fun showEmptyView() {
        activity_list_photo_empty_view.visibility = View.VISIBLE
        activity_list_photo_images_list_recycler.visibility = View.GONE
    }

    override fun showList() {
        activity_list_photo_empty_view.visibility = View.GONE
        activity_list_photo_images_list_recycler.visibility = View.VISIBLE
    }

    override fun bindResult(result: List<PhotoRest>) {
        adapter.swapData(result)
    }

    override fun getRootView(): View = activity_list_photo_main_content

    override fun getViewModel(): ImageListContract.ViewModel = ViewModelProviders.of(this).get(ImageListViewModel::class.java)

    private val refreshListener = SwipeRefreshLayout.OnRefreshListener {
        getViewModel().refreshList(searchView?.query?.toString())
    }

    private val searchCallbackListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            Log.d("DEBUG", "SEARCH")
            getViewModel().refreshList(query)
            searchView?.clearFocus()
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {

            return false
        }

    }


    private fun showDetails(photo: PhotoRest) {
        val intent = DetailsActivity.getIntent(this, photo)
        startActivity(intent)
    }


}
