package com.androidskeleton.mvvm.di.module;


import com.androidskeleton.mvvm.di.scope.PerActivity;
import com.androidskeleton.mvvm.module.main.MainActivity;
import com.androidskeleton.mvvm.module.main.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @PerActivity
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();
}
