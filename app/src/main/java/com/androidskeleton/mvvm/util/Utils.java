package com.androidskeleton.mvvm.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Utils {
    private Context context;

    @Inject
    public Utils(Context context) {
        this.context = context;
    }

    public void showToast(String message) {
        if (message == null) return;
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void showToast(int messageId) {
        if (messageId == 0) return;
        Toast.makeText(context, context.getString(messageId), Toast.LENGTH_SHORT).show();
    }

    public void showErrorDialog(String message) {
        AlertDialog.Builder builder =new AlertDialog.Builder(context);
        builder.setTitle("OOPS");
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }
}
