package com.example.beretta.owoxgallary.arch;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

/**
 * Created by beretta on 12.10.2017.
 */

public class BaseArchViewModel<V extends ArchViewContract> extends ViewModel implements ArchViewModelContract<V>{

    @Nullable
    private V mView;
    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }
}
