package io.github.keshuangjie.androidutil.os;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;

import io.github.keshuangjie.androidutil.Initializer;

/**
 * 获取版本号、签名等app相关信息
 *
 * @author keshuangjie
 * @date 2015-2-28 上午10:49:17
 */
public class AppInfo implements Info {
    private String mVersionName;
    private int mVersionCode;
    private byte[] mSignture;

    @Override
    public void init(Context context) {
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            mVersionName = info.versionName;
            mVersionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            mVersionName = "1.0.0";// todo 版本号读取失败时，应该赋何值合适？
            mVersionCode = 1;
        }

        try {
            Signature[] sigs = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES).signatures;
            mSignture = sigs[0].toByteArray();
        } catch (PackageManager.NameNotFoundException e) {
            mSignture = new byte[0];
        }
    }

    public String getVersionName() {
        if (TextUtils.isEmpty(mVersionName)) {
            init(Initializer.getContext());
        }
        return mVersionName;
    }

    public int getVersionCode() {
        if (mVersionCode == 0) {
            init(Initializer.getContext());
        }
        return mVersionCode;
    }

    public byte[] getSignture() {
        if (mSignture == null) {
            init(Initializer.getContext());
        }
        return mSignture;
    }
}
