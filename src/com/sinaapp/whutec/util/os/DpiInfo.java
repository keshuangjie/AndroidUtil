package com.sinaapp.whutec.util.os;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;

import com.sinaapp.whutec.util.Initializer;

/**
 * @author keshuangjie
 * @date 2015-2-28 上午10:46:58
 * 获取屏幕dpi信息
 */
public class DpiInfo implements Info {
    private int mScreenWidth = -1;
    private int mScreenHeight = -1;
    private float mDensity = -1.0f;
    private int mXDpi = -1;
    private int mYDpi = -1;
    private int mDensityDpi = -1;
    private double mDpiRatio = -1.0;
    public static final int DENSITY_DEFAULT = 160;

    @Override
    public void init(Context context) {
        DisplayMetrics outMetrics = context.getResources().getDisplayMetrics();
        mScreenWidth = outMetrics.widthPixels;
        mScreenHeight = outMetrics.heightPixels;
        mDensity = outMetrics.density;
        mXDpi = (int) outMetrics.xdpi;
        mYDpi = (int) outMetrics.ydpi;

        if (Build.VERSION.SDK_INT > 3) { // Android 1.6以上版本 支持DensityDpi参数
            mDensityDpi = outMetrics.densityDpi;
        } else {
            mDensityDpi = DENSITY_DEFAULT;
        }
        if (mDensityDpi == 0) {
            mDensityDpi = 160;
        }

        mDpiRatio = mDensityDpi / 240.;
    }

    public int getScreenWidth() {
        if (mScreenWidth == -1) {
            init(Initializer.getContext());
        }
        return mScreenWidth;
    }

    public int getScreenHeight() {
        if (mScreenHeight == -1) {
            init(Initializer.getContext());
        }
        return mScreenHeight;
    }

    public float getDensity() {
        if (mDensity == -1.0f) {
            init(Initializer.getContext());
        }
        return mDensity;
    }

    public int getXDpi() {
        if (mXDpi == -1) {
            init(Initializer.getContext());
        }
        return mXDpi;
    }

    public int getYDpi() {
        if (mYDpi == -1) {
            init(Initializer.getContext());
        }
        return mYDpi;
    }

    public int getDensityDpi() {
        if (mDensityDpi == -1) {
            init(Initializer.getContext());
        }
        return mDensityDpi;
    }

    public double getDpiRatio() {
        if (mDpiRatio == -1.0) {
            init(Initializer.getContext());
        }
        return mDpiRatio;
    }
}
