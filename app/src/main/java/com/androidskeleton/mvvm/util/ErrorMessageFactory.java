package com.androidskeleton.mvvm.util;

import android.accounts.NetworkErrorException;
import android.content.Context;

import com.androidskeleton.mvvm.BuildConfig;
import com.androidskeleton.mvvm.R;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class ErrorMessageFactory {
    private final Context context;

    public ErrorMessageFactory(Context context) {
        this.context = context;
    }

    public String getError(Throwable t) {
        t.printStackTrace();
        String msg = "";
        if (context != null) {
            if (t instanceof ConnectException | t instanceof UnknownHostException
                    | t instanceof NetworkErrorException) {
                msg = context.getResources().getString(R.string.internet_error);
            } else if (t instanceof SocketTimeoutException || t instanceof NoRouteToHostException) {
                msg = context.getResources().getString(R.string.error_unable_to_connect_to_the_server_try_again);
            } else {
                msg = context.getString(R.string.error_something_went_wrong_try_again);
            }
        }
        //Firebase crash reporting
        if (!BuildConfig.DEBUG) {
            //FirebaseCrash.report(t);
        }
        return msg;
    }
}
