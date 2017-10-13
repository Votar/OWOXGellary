package com.example.beretta.owoxgallary.ui.list

import com.example.beretta.owoxgallary.R
import com.example.beretta.owoxgallary.ui.list.contract.ImageListContract
import com.example.beretta.owoxgallary.arch.viewmodel.BaseArchViewModel
import com.example.beretta.owoxgallary.models.network.response.PhotoRest
import com.example.beretta.owoxgallary.models.network.response.SearchResponse
import com.example.beretta.owoxgallary.network.ApiUnsplash
import com.example.beretta.owoxgallary.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageListViewModel : BaseArchViewModel<ImageListContract.View>(),
        ImageListContract.ViewModel {

    val api = ApiUnsplash.getInstance().service!!
    var lastPhotoResult: List<PhotoRest>? = null

    override fun attachView(view: ImageListContract.View) {
        super.attachView(view)
        if (lastPhotoResult == null)
            refreshList("")
        else
            showResult(lastPhotoResult!!)

    }

    override fun refreshList(query: String?) {
        mView?.showProgress()
        if (query.isNullOrEmpty())
            api.listPhotos(Constant.userId).enqueue(photosListCallback)
        else
            api.searchPhotos(Constant.userId, query).enqueue(searchListCallback)
    }

    private fun showResult(list: List<PhotoRest>) {
        if (list.isEmpty())
            mView?.showEmptyView()
        else {
            mView?.showList()
            mView?.bindResult(list)
        }
    }


    val searchListCallback = object : Callback<SearchResponse> {
        override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>) {
            mView?.hideProgress()
            if (response.isSuccessful) {
                when (response.code()) {
                    200 -> response.body().apply {
                        lastPhotoResult = this.results
                        if (this.results.isEmpty())
                            mView?.showEmptyView()
                        else
                            showResult(this.results)
                    }
                    403 -> mView?.showError(R.string.er_rate_limit)

                    else -> {

                    }
                }
            } else
                mView?.showError(R.string.er_default_inner_error)
        }

        override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
            mView?.showError(R.string.er_default_inner_error)
            mView?.hideProgress()
        }
    }


    val photosListCallback = object : Callback<List<PhotoRest>> {
        override fun onFailure(call: Call<List<PhotoRest>>?, t: Throwable?) {
            mView?.showError(R.string.er_default_inner_error)
            mView?.hideProgress()
        }

        override fun onResponse(call: Call<List<PhotoRest>>?, response: Response<List<PhotoRest>>) {
            mView?.hideProgress()
            if (response.isSuccessful) {
                when (response.code()) {
                    200 -> response.body().apply {
                        lastPhotoResult = this
                        if (this.isEmpty())
                            mView?.showEmptyView()
                        else
                            showResult(this)
                    }
                    403 -> mView?.showError(R.string.er_rate_limit)

                    else -> {

                    }
                }
            } else
                mView?.showError(R.string.er_default_inner_error)
        }
    }
}