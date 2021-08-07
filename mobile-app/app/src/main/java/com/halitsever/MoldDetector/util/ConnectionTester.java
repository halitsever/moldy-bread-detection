package com.halitsever.MoldDetector.util;

import android.content.Context;
import android.net.ConnectivityManager;

public class ConnectionTester {

    private Context mContext;

    public ConnectionTester(Context context){
        this.mContext = context;
    }

    public boolean isConnectingToInternet(){

        ConnectivityManager cm = (ConnectivityManager)mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected() == true)
        {
            return true;
        }

        return false;

    }
}