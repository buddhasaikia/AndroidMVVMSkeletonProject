package com.androidskeleton.mvvm.data.repository;

import android.support.annotation.NonNull;

import com.androidskeleton.mvvm.data.datasource.LocalDataSource;
import com.androidskeleton.mvvm.data.datasource.RemoteDataSource;
import com.androidskeleton.mvvm.data.datasource.base.BaseDataSource;
import com.androidskeleton.mvvm.data.datasource.base.DataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by Buddha Saikia on 06-10-2018.
 */
@Singleton
public class Repository extends BaseDataSource implements DataSource.Greetings {

    @NonNull
    private final RemoteDataSource remoteDataSource;
    @NonNull
    private final LocalDataSource localDataSource;

    @Inject
    public Repository(@NonNull RemoteDataSource awRemoteDataSource,
                      @NonNull LocalDataSource awLocalDataSource) {
        this.remoteDataSource = checkNotNull(awRemoteDataSource);
        this.localDataSource = checkNotNull(awLocalDataSource);
    }

    @Override
    public Observable<String> greetings() {
        return remoteDataSource.greetings();
    }
}
