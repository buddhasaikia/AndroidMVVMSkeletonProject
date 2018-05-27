package com.androidskeleton.mvvm.util;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.androidskeleton.mvvm.data.repository.Repository;
import com.androidskeleton.mvvm.module.main.MainViewModel;

import javax.inject.Singleton;

@Singleton
public class CustomViewModelFactory implements ViewModelProvider.Factory {
    private final Repository repository;

    public CustomViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class))
            return (T) new MainViewModel(repository);
        else
            throw new IllegalArgumentException("ViewModel not found!");
    }
}
