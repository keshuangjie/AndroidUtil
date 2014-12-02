package com.whutec.android.util.device;

import android.os.Environment;

/**
 * 获取设备相关信息
 * @author keshuangjie
 * @date 2013-7-12 下午2:59:55
 */
public class DeviceInfo {
	
	/**
	 * 是否有sdcard挂载
	 * @return
	 */
	public static boolean isSdcardMounted(){
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

}
