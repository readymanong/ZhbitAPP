package com.example.dm.zhbit.utiltools;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.widget.Toast;


/**
 * 系统工具类
 */
public class SystemUtils {
    public static boolean checkNetworkConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activityNetwork = cm.getActiveNetworkInfo();
        return activityNetwork != null && activityNetwork.isConnected();
    }

    public static void noNetworkAlert(Context context) {
        Toast.makeText(context, "No Network", Toast.LENGTH_SHORT).show();
    }
}
