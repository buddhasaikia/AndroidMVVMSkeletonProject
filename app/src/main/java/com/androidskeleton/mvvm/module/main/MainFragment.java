package com.androidskeleton.mvvm.module.main;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidskeleton.mvvm.R;
import com.androidskeleton.mvvm.di.scope.PerActivity;
import com.androidskeleton.mvvm.module.base.DaggerBaseFragment;
import com.androidskeleton.mvvm.util.CustomViewModelFactory;
import com.androidskeleton.mvvm.util.ErrorMessageFactory;
import com.androidskeleton.mvvm.util.Utils;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

@PerActivity
public class MainFragment extends DaggerBaseFragment {

    @Inject
    CustomViewModelFactory viewModelFactory;
    @Inject
    ErrorMessageFactory errorMessageFactory;
    @Inject
    Utils utils;
    private MainViewModel mainViewModel;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
        final TextView lblGreetings = view.findViewById(R.id.lbl_greetings);
        mainViewModel.greetings()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mainViewModel.addSubscription(d);
                    }

                    @Override
                    public void onNext(String s) {
                        lblGreetings.setText(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        utils.showToast(errorMessageFactory.getError(e));
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
