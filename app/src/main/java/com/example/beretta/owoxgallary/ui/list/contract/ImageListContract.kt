package com.example.beretta.owoxgallary.ui.list.contract

import com.example.beretta.owoxgallary.arch.view.ArchViewContract
import com.example.beretta.owoxgallary.arch.viewmodel.ArchViewModelContract
import com.example.beretta.owoxgallary.models.network.response.PhotoRest

/**
 * Created by beretta on 12.10.2017.
 */
interface ImageListContract {
    interface View : ArchViewContract {
        fun showProgress()
        fun hideProgress()
        fun showEmptyView()
        fun hideEmptyView()
        fun bindResult(result: List<PhotoRest>)
    }

    interface ViewModel : ArchViewModelContract<View> {
        fun refreshList(query : String?)
        fun onRetryClick()
    }

}