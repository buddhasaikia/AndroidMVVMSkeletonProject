package com.androidskeleton.mvvm.util;

import android.accounts.NetworkErrorException;

import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class ErrorMessageFactory {

    public String getError(Throwable t) {
        t.printStackTrace();
        String msg;
        if (t instanceof ConnectException || t instanceof UnknownHostException
                || t instanceof NetworkErrorException) {
            msg = "Could not connect to sever, Please check your internet connection or try again later.";
        } else if (t instanceof SocketTimeoutException || t instanceof NoRouteToHostException) {
            msg = "Something snapped. Please check internet connection and try again.";
        } else {
            msg = "Something went wrong. Please try again later.";
        }
        return msg;
    }
}
