package com.androidskeleton.mvvm.data.datasource;


import com.androidskeleton.mvvm.data.api.ApiService;
import com.androidskeleton.mvvm.data.datasource.base.BaseDataSource;
import com.androidskeleton.mvvm.data.datasource.base.DataSource;

/**
 * Created by Buddha Saikia on 03-06-2017.
 */

public class RemoteDataSource extends BaseDataSource implements DataSource {

    private ApiService apiService;

    public RemoteDataSource(ApiService apiService) {
        this.apiService = apiService;
    }

}
