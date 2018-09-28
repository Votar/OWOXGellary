package com.example.beretta.owoxgallary.ui.details

import com.example.beretta.owoxgallary.R
import com.example.beretta.owoxgallary.arch.viewmodel.BaseArchViewModel
import com.example.beretta.owoxgallary.models.network.response.DownloadLinkResult
import com.example.beretta.owoxgallary.network.ApiUnsplash
import com.example.beretta.owoxgallary.utils.Constant
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by beretta on 13.10.2017.
 */
class DetailsViewModel : BaseArchViewModel<DetailsContract.View>(), DetailsContract.ViewModel {

    override fun requestLink(id: String) {
        ApiUnsplash.service.getPhotosLink(id, Constant.userId).enqueue(responseCallback)
    }

    private val responseCallback = object : Callback<DownloadLinkResult> {
        override fun onResponse(call: Call<DownloadLinkResult>?, response: Response<DownloadLinkResult>) {
            if (response.isSuccessful) {
                when (response.code()) {
                    200 -> view?.startDownloading(response.body().url)
                    else -> view?.showError(R.string.er_default_inner_error)
                }
            } else view?.showError(R.string.er_to_get_download_link)

        }

        override fun onFailure(call: Call<DownloadLinkResult>?, t: Throwable?) {
            view?.showError(R.string.er_to_get_download_link)
        }
    }
}