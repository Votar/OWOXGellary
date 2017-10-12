package com.example.beretta.owoxgallary.ui.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.beretta.owoxgallary.R;
import com.example.beretta.owoxgallary.arch.BaseArchActivity;
import com.example.beretta.owoxgallary.ui.list.contract.ImageListContract;
import com.example.beretta.owoxgallary.ui.list.contract.ImageListViewModel;

public class ImageListActivity extends BaseArchActivity<ImageListContract.View, ImageListContract.ViewModel>
        implements ImageListContract.View {

    @Override
    protected ImageListContract.ViewModel getViewModel() {
        return ViewModelProviders.of(this).get(ImageListViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public Context getActivityContext() {
        return this;
    }
}
