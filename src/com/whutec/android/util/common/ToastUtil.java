package com.whutec.android.util.common;

import com.whutec.android.util.MyApplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

/**
 * @author keshuangjie
 * @date 2014-10-21 下午1:48:24
 * 单例Toast，第一次初始化时不能在非主线程内，最保险是在Application onCreate初始化
 */
@SuppressLint("ShowToast")
public class ToastUtil {
	
	private Toast mToast;
	private Context mContext;
	
	private static ToastUtil mInstance;
	
	static{
		mInstance = new ToastUtil();
	}
	
	private ToastUtil(){
		mContext = MyApplication.getInstance().getApplicationContext();
		mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
	}
	
	public static ToastUtil getInstance(){
		return mInstance;
	}
	
	public void toast(String message){
		toast(message, Toast.LENGTH_SHORT);
	}
	
	public void toast(final String message, final int duration){
		if(isMainThread()){
			showToast(message, duration);
		}else{
			MyApplication.getInstance().post(new Runnable() {
				
				public void run() {
					showToast(message, duration);
				}
			});
		}
	}
	
	private void showToast(final String message, final int duration){
//		mToast.cancel();
		mToast.setText(message);
		mToast.setDuration(duration);
		mToast.show();
	}
	
	public void cancleToast(){
		mToast.cancel();
	}
	
	/**
	 * 判断当前是否是主线程
	 * @return
	 */
	private boolean isMainThread(){
		return Thread.currentThread() == Looper.getMainLooper().getThread();
	}
}
