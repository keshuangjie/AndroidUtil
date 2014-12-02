package com.whutec.android.util.thread;

import android.util.Log;

/**
 * 线程工具类
 * @author keshuangjie
 *
 */
public class ThreadUtil {
	
	/**
	 * 获取当前线程id
	 * @return
	 */
	public static long getTreadId(){
		Thread t = Thread.currentThread();
		return t.getId();
	}
	
	/***
	 * 获取当前线程签名
	 * @return
	 */
	public static String getThreadSignature(){
		Thread t = Thread.currentThread();
		long id = t.getId();
		String name = t.getName();
		long priority = t.getPriority();
		String groupName = t.getThreadGroup().getName();
		return ("name=" + name + " id=" + id + " priority=" + priority
				+ " groupName=" + groupName);
	}
	
	public static void logThreadSignature(String tag){
		Log.d(tag, getThreadSignature());
	}
	
	public static void sleepForInSecs(int secs){
		try{
			Thread.sleep(secs * 1000);
		}catch(InterruptedException x){
			throw new RuntimeException("interrupted",x);
		}
	}
	
}