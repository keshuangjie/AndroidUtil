package com.sinaapp.whutec.util.os;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;

import com.sinaapp.whutec.util.Initializer;

/**
 * @author keshuangjie
 * @date 2015-2-28 上午10:56:01
 * 获取mac地址
 */
public class MacAddressInfo implements Info {
    private String mMacAddress;

    @Override
    public void init(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (wifi != null) {
            WifiInfo info = wifi.getConnectionInfo();
            if (info != null) {
                mMacAddress = info.getMacAddress();
            }
        }
    }

    public String getMacAddress() {
        if (TextUtils.isEmpty(mMacAddress)) {
            init(Initializer.getContext());
        }
        return mMacAddress;
    }
}
