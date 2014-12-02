package com.whutec.android.util.device;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.http.conn.util.InetAddressUtils;

/**
 * @author keshuangjie
 * @date 2014-12-2 下午3:53:56
 * @version 1.0
 * 获取网络相关信息
 */
public class NetworkInfo {
	
	/**
	 * 获取当前本地ip地址
	 * @return
	 */
	public static String getLocalIP(){
    	String ip;
	    try {  
	         Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); 
			while (en.hasMoreElements()) {
				NetworkInterface intf = en.nextElement();
				Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses();
				while (enumIpAddr.hasMoreElements()) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress() && InetAddressUtils.isIPv4Address(ip = inetAddress.getHostAddress())) {
						return ip;
					}
	            }  
	        }  
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return "获取失败";
    }

}
