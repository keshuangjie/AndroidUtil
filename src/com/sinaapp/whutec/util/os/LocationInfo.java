package com.sinaapp.whutec.util.os;

import android.content.Context;
import android.location.LocationManager;

import com.sinaapp.whutec.util.Initializer;

/**
 * @author keshuangjie
 * @date 2015-2-28 上午10:54:51
 * 获取位置相关信息
 */
public class LocationInfo implements Info {
    private int mGPSOn = -1;
    private int mNetOn = -1;

    @Override
    public void init(Context context) {
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            mGPSOn = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ? 1 : 0;
            mNetOn = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ? 1 : 0;
        } catch (Exception ex) {
        }
    }

    public int getGPSOn() {
        if (mGPSOn == -1) {
            init(Initializer.getContext());
        }
        return mGPSOn;
    }

    public int getNetOn() {
        if (mNetOn == -1) {
            init(Initializer.getContext());
        }
        return mNetOn;
    }
}
