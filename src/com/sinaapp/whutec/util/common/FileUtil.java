package com.sinaapp.whutec.util.common;

import java.io.File;

/**
 * @author keshuangjie
 * @date 2014-12-2 下午3:43:25
 * 文件工具类
 */
public class FileUtil {
	
	/**
	 * 获取文件后缀
	 * @param file
	 * @return
	 */
	public static String getSuffix(File file) {
		final String path = file.getAbsolutePath();
		int lastDot = path.lastIndexOf('.');

		return (lastDot >= 0) ? path.substring(lastDot+1) : "";
	}

	/**
	 * 获取文件后缀
	 * @param filename
	 * @return
	 */
	public static String getSuffix(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}
	
}
