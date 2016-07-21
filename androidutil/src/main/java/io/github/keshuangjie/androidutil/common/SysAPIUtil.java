package io.github.keshuangjie.androidutil.common;

import io.github.keshuangjie.androidutil.os.AppInfo;
import io.github.keshuangjie.androidutil.os.DpiInfo;
import io.github.keshuangjie.androidutil.os.LocationInfo;
import io.github.keshuangjie.androidutil.os.MacAddressInfo;

/**
 * 系统相关api封装
 *
 * @author keshuangjie
 * @date 2015-2-28 上午11:47:18
 */
public class SysAPIUtil {

    private boolean mIsInited;

    private AppInfo mAppInfo = new AppInfo();

    private DpiInfo mDpiInfo = new DpiInfo();

    private MacAddressInfo macAddressInfo = new MacAddressInfo();

    private LocationInfo mLocationInfo = new LocationInfo();

    private String mNetType = "";
    
    public static SysAPIUtil getInstance() {
        return Holder.SYSOSAPI_V2;
    }

    private static class Holder {
        private static final SysAPIUtil SYSOSAPI_V2 = new SysAPIUtil();
    }

    private SysAPIUtil() {
    }

    public void init() {
        if (!mIsInited) {
            mIsInited = true;
        }
    }

    public void destroy() {
        mIsInited = false;
    }

    public int getGPSOn() {
        return mLocationInfo.getGPSOn();
    }

    public int getNetOn() {
        return mLocationInfo.getNetOn();
    }

    public String getMacAddress() {
        return macAddressInfo.getMacAddress();
    }

    public String getVersionName() {
        return mAppInfo.getVersionName();
    }

    public int getVersionCode() {
        return mAppInfo.getVersionCode();
    }

    public int getScreenWidth() {
        return mDpiInfo.getScreenWidth();
    }

    public int getScreenHeight() {
        return mDpiInfo.getScreenHeight();
    }

    public float getDensity() {
        return mDpiInfo.getDensity();
    }

    public int getXDpi() {
        return mDpiInfo.getXDpi();
    }

    public int getYDpi() {
        return mDpiInfo.getYDpi();
    }

    public int getDensityDpi() {
        return mDpiInfo.getDensityDpi();
    }

    public double getDpiRatio() {
        return mDpiInfo.getDpiRatio();
    }

//    /**
//     * 获取网络类型，首次时主动获取，其他时候由网络监听更新
//     * @return
//     */
//    public String getNetType() {
//        if(TextUtils.isEmpty(mNetType)) {
//            mNetType = NetworkUtil.getCurrentNetMode(Initializer.getContext());
//        }
//        return mNetType;
//    }

}