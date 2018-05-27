package com.androidskeleton.mvvm.module.main;

import android.arch.lifecycle.ViewModel;

import com.androidskeleton.mvvm.data.repository.Repository;

public class MainViewModel extends ViewModel {

    private Repository repository;

    public MainViewModel(Repository repository) {

        this.repository = repository;
    }
}
