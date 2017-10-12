package com.example.beretta.owoxgallary.arch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by beretta on 12.10.2017.
 */

public abstract class BaseArchActivity<V extends ArchViewContract, VM extends ArchViewModelContract<V>> extends AppCompatActivity
        implements ArchViewContract {

    protected abstract VM getViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().attachView((V) this);
    }

    @Override
    protected void onDestroy() {
        getViewModel().detachView();
        super.onDestroy();
    }
}
