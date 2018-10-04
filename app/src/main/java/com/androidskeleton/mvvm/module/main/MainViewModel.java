package com.androidskeleton.mvvm.module.main;

import android.arch.lifecycle.ViewModel;

import com.androidskeleton.mvvm.data.datasource.base.BaseViewModel;
import com.androidskeleton.mvvm.data.datasource.base.DataSource;
import com.androidskeleton.mvvm.data.repository.Repository;

import io.reactivex.Observable;

public class MainViewModel extends BaseViewModel implements DataSource.Greetings {

    private Repository repository;

    public MainViewModel(Repository repository) {

        this.repository = repository;
    }

    public Observable<String> greetings() {
        return repository.greetings();
        //return "Hola! I am ViewModel at your service!";
    }
}
