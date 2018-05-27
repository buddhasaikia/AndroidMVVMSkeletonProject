package com.androidskeleton.mvvm.module.main;


import com.androidskeleton.mvvm.di.scope.FragmentScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract MainFragment mainFragment();
}
