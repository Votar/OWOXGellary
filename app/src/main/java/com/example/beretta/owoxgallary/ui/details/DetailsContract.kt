package com.example.beretta.owoxgallary.ui.details

import com.example.beretta.owoxgallary.arch.view.ArchViewContract
import com.example.beretta.owoxgallary.arch.viewmodel.ArchViewModelContract

/**
 * Created by beretta on 13.10.2017.
 */
interface DetailsContract {
    interface View : ArchViewContract{
        fun startDownloading(url: String)
    }
    interface ViewModel : ArchViewModelContract<View>{
        fun requestLink(id: String)
    }
}