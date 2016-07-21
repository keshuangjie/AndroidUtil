package io.github.keshuangjie.androidutil.os;

import android.content.Context;
import android.location.LocationManager;

import io.github.keshuangjie.androidutil.Initializer;

/**
 * 获取位置相关信息
 *
 * @author keshuangjie
 * @date 2015-2-28 上午10:54:51
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
