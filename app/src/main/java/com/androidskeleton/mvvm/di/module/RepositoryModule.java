package com.androidskeleton.mvvm.di.module;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.androidskeleton.mvvm.BuildConfig;
import com.androidskeleton.mvvm.data.api.ApiService;
import com.androidskeleton.mvvm.data.datasource.LocalDataSource;
import com.androidskeleton.mvvm.data.datasource.RemoteDataSource;
import com.androidskeleton.mvvm.data.repository.Repository;
import com.androidskeleton.mvvm.util.CustomViewModelFactory;
import com.androidskeleton.mvvm.util.ErrorMessageFactory;
import com.androidskeleton.mvvm.util.NullOnEmptyConverterFactory;
import com.androidskeleton.mvvm.util.Utils;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RepositoryModule {
    @Singleton
    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    @Singleton
    @Provides
    StethoInterceptor provideStethoInterceptor() {
        return new StethoInterceptor();
    }

    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    @Named("ok-1")
    OkHttpClient provideOkHttpClient1(HttpLoggingInterceptor loggingInterceptor,
                                      StethoInterceptor stethoInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(stethoInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    @Singleton
    @Provides
    @Named("ok-2")
    OkHttpClient provideOkHttpClient2(HttpLoggingInterceptor loggingInterceptor,
                                      StethoInterceptor stethoInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(stethoInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
    }


    @Singleton
    @Provides
    RxJava2CallAdapterFactory provideRxJavaCallAdapterFactory() {
        return RxJava2CallAdapterFactory.create();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(@Named("ok-1") OkHttpClient okHttpClient,
                             GsonConverterFactory converterFactory,
                             RxJava2CallAdapterFactory adapterFactory) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.URL_HOST_API)
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(okHttpClient)
                .build();
    }


    @Singleton
    @Provides
    ErrorMessageFactory provideErrorMessageFactory() {
        return new ErrorMessageFactory();
    }

    @Singleton
    @Provides
    ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Singleton
    @Provides
    LocalDataSource provideLocalDataSource() {
        return new LocalDataSource();
    }

    @Singleton
    @Provides
    RemoteDataSource provideRemoteDataSource(ApiService apiService) {
        return new RemoteDataSource(apiService);
    }

    @Singleton
    @Provides
    Repository provideRepository(LocalDataSource localDatasource, RemoteDataSource remoteDatasource) {
        return new Repository(remoteDatasource, localDatasource);
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(Repository repository) {
        return new CustomViewModelFactory(repository);
    }

    @Singleton
    @Provides
    Utils provideUtils(Context context){
        return new Utils(context);
    }
}
