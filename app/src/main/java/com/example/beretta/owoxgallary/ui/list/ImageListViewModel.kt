package com.example.beretta.owoxgallary.ui.list

import com.example.beretta.owoxgallary.R
import com.example.beretta.owoxgallary.arch.viewmodel.BaseArchViewModel
import com.example.beretta.owoxgallary.models.network.response.PhotoRest
import com.example.beretta.owoxgallary.models.network.response.SearchResponse
import com.example.beretta.owoxgallary.network.ApiUnsplash
import com.example.beretta.owoxgallary.ui.list.contract.ImageListContract
import com.example.beretta.owoxgallary.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageListViewModel : BaseArchViewModel<ImageListContract.View>(),
        ImageListContract.ViewModel {

    val api = ApiUnsplash.service
    var lastPage: Int = 0
    var lastQuery: String = ""
    private val lastPhotoResult: MutableList<PhotoRest> = mutableListOf()

    override fun attachView(view: ImageListContract.View) {
        super.attachView(view)
        showResult(lastPhotoResult, lastPage)
    }

    override fun refreshList(query: String?) {
        view?.showProgress()
        if (query != null && query.isNotEmpty())
            searchPhotos(query)
        else
            loadPhotos()

    }


    private fun loadPhotos() {
        lastPage += 1
        api.listPhotos(Constant.userId, lastPage).enqueue(object : Callback<List<PhotoRest>> {

            override fun onResponse(call: Call<List<PhotoRest>>?, response: Response<List<PhotoRest>>) {
                view?.hideProgress()
                when (response.code()) {
                    200 -> showResult(response.body(), lastPage)
                    403 -> view?.showError(R.string.er_rate_limit)
                    else -> view?.showError(R.string.er_default_inner_error)
                }
            }

            override fun onFailure(call: Call<List<PhotoRest>>?, t: Throwable?) {
                view?.showError(R.string.er_default_inner_error)
                view?.hideProgress()
            }
        })
    }

    private fun searchPhotos(query: String) {
        if (lastQuery != query) {
            lastQuery = query
            lastPage = 1
            lastPhotoResult.clear()
        } else {
            this.lastPage += 1
        }
        api.searchPhotos(Constant.userId, query, lastPage).enqueue(object : Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>) {
                view?.hideProgress()
                when (response.code()) {
                    200 -> showResult(response.body().results, lastPage)
                    403 -> view?.showError(R.string.er_rate_limit)
                    else -> view?.showError(R.string.er_default_inner_error)
                }
            }

            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                view?.showError(R.string.er_default_inner_error)
                view?.hideProgress()
            }
        })
    }

    override fun onRetryClick() {
        refreshList(lastQuery)
    }

    private fun showResult(list: List<PhotoRest>, page: Int) {
        if (list.isEmpty())
            view?.showEmptyView()
        else {
            if (page == 1)
                lastPhotoResult.clear()
            lastPhotoResult.addAll(list)
            view?.hideEmptyView()
            view?.bindResult(lastPhotoResult)
        }
    }
}