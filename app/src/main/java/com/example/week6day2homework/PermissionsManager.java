package com.example.week6day2homework;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public class PermissionsManager {

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 423;
    IPermissionManager iPermissionManager;
    Context context;

    public PermissionsManager(Context context){
        this.iPermissionManager = (IPermissionManager) context;
        this.context = context;

    }
    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {

                requestPermission();
            }
        } else {
            iPermissionManager.onPermissionResult(true);


        }
    }

    public void requestPermission() {

        ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                MY_PERMISSIONS_REQUEST_LOCATION);

    }

    public void checkResult(int requestCode, String permissions[], int[] grantResults) {
        switch(requestCode) {

            case MY_PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.d("TAG", "checkResult: PERMISSION WAS GRANTED");
                    iPermissionManager.onPermissionResult(true);
                } else{

                    iPermissionManager.onPermissionResult(false);
                    Log.d("TAG", "checkResult: PERMISSION DENIED");
                }
                return;
            }
        }


    }

    public interface IPermissionManager{
        void onPermissionResult(boolean isGranted);
    }

}
