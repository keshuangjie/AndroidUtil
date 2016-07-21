package io.github.keshuangjie.androidutil;

import android.content.Context;

/**
 * @author keshuangjie
 * @date 2015-2-28 上午10:42:33
 * 初始化器，需在主程序的Application onCreate()内调用init()初始化mContext
 */
public class Initializer {
	
	private static Context mContext;
	
	public static void init(Context context){
		mContext = context;
	}
	
	public static Context getContext(){
		return mContext;
	}

}
