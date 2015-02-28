package com.sinaapp.whutec.util.common;

import android.content.Context;
import android.os.Environment;

/**
 * @author keshuangjie
 * @date 2015-2-28 上午11:45:47
 * 常用工具类
 */
public class CommonUtil {
	
	/**
	 * 是否有sdcard挂载
	 * @return
	 */
	public static boolean isSdcardMounted(){
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
	
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}


}
