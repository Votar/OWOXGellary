package com.example.beretta.owoxgallary.ui.list.contract;

import com.example.beretta.owoxgallary.arch.ArchViewContract;
import com.example.beretta.owoxgallary.arch.ArchViewModelContract;

public interface ImageListContract {

    public interface View extends ArchViewContract {
    }

    public interface ViewModel extends ArchViewModelContract<View> {

    }

}
