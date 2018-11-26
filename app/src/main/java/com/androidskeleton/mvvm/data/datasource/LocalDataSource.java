package com.androidskeleton.mvvm.data.datasource;


import com.androidskeleton.mvvm.data.datasource.base.BaseDataSource;
import com.androidskeleton.mvvm.data.datasource.base.DataSource;

import io.reactivex.Observable;

/**
 * Created by Buddha Saikia on 06-10-2018.
 */

public class LocalDataSource extends BaseDataSource implements DataSource.Greetings {

    @Override
    public Observable<String> greetings() {
        return null;
    }
}
