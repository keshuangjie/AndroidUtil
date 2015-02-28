package com.sinaapp.whutec.util.common;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.apache.http.conn.util.InetAddressUtils;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

/**
 * @author keshuangjie
 * @date 2015-2-28 上午11:11:03
 * 网络工具类
 * TODO 代理设置需要修改
 */
public class NetworkUtil {
    public static final int NETYPE_NOCON = -1; //无连接，用于区分断网和未知类型，方便统计
    public static final int NETYPE_UNKNOWN = 0; //位置网络类型
    public static final int NETYPE_WIFI = 1; //WiFi连接
    public static final int NETYPE_2G = 2; //2G
    public static final int NETYPE_3G = 3; //3G
    public static final int NETYPE_4G = 4; //4G
    public static final int NETYPE_TELECOM_2G = 5; //电信2G(IS95A或者IS95B)
    public static final int NETYPE_MOBILE_UNICOM_2G = 6; //移动或联通2G
    public static final int NETYPE_TELECOM_3G = 7; //电信3G
    public static final int NETYPE_MOBILE_3G = 8; //移动3G
    public static final int NETYPE_UNICOM_3G = 9; //联通3G
    public static final int NETYPE_4G_UNKNOWN = 10;//4G?

    //wap 代理
    public static boolean mUseProxy = false;
    public static String mProxyHost = "";
    public static int mProxyPort = 0;

    /**
     * 更新引擎的网络代理设置
     *
     * @param context
     */
    public static void updateNetworkProxy(Context context) {

        NetworkInfo info = NetworkUtil.getActiveNetworkInfo(context);

        if (info != null && info.isAvailable()) {
            String typeName = info.getTypeName().toLowerCase(); // WIFI/MOBILE

            if (typeName.equals("wifi") && info.isConnected()) { // wifi 连通状态                
            	//设置代理为空
                mUseProxy = false;
                return;
            }

            if (typeName.equals("mobile") || (typeName.equals("wifi") && !isWifiConnected(info))) { // wifi断开或mobile

                String extraInfo = info.getExtraInfo(); //3gnet/3gwap/uninet/uniwap/cmnet/cmwap/ctnet/ctwap 
                mUseProxy = false;
                if (extraInfo != null) {
                    String extraInfoName = extraInfo.toLowerCase();
                    if (extraInfoName.startsWith("cmwap") || extraInfoName.startsWith("uniwap")
                            || extraInfoName.startsWith("3gwap")) {
                        mProxyHost = "10.0.0.172";
                        mProxyPort = 80;
                        mUseProxy = true;
                    } else if (extraInfoName.startsWith("ctwap")) {
                        mProxyHost = "10.0.0.200";
                        mProxyPort = 80;
                        mUseProxy = true;
                    } else if (extraInfoName.startsWith("cmnet") || extraInfoName.startsWith("uninet")
                            || extraInfoName.startsWith("ctnet") || extraInfoName.startsWith("3gnet")) {
                        mUseProxy = false;
                    } else {
                    }
                } else {
                    // 如果没有 apn 信息，则根据 proxy代理判断。
                    // 由于android 4.2 对 "content://telephony/carriers/preferapn" 读取进行了限制，我们通过系统接口获取。

                    // 绝大部分情况下不会走到这里
                    // 此两个方法是deprecated的，但在4.2下仍可用
                    String defaultProxyHost = android.net.Proxy.getDefaultHost();
                    int defaultProxyPort = android.net.Proxy.getDefaultPort();
                    if (defaultProxyHost != null && defaultProxyHost.length() > 0) {
                        /*
                         * 无法根据  proxy host 还原 apn 名字 这里不设置  mApn
                         */
                        if ("10.0.0.172".equals(defaultProxyHost.trim())) {
                            // 当前网络连接类型为cmwap || uniwap
                            mProxyHost = "10.0.0.172";
                            mProxyPort = defaultProxyPort;
                            mUseProxy = true;
                        } else if ("10.0.0.200".equals(defaultProxyHost.trim())) {
                            mProxyHost = "10.0.0.200";
                            mProxyPort = 80;
                            mUseProxy = true;
                        } else {
                        }
                    } else {
                        // 其它网络都看作是net                        
                    }
                }
                if (mUseProxy == true) {
                    //更新代理信息mProxyHost、mProxyPort
                } else{
                	//设置代理为空
                }
            }
        }
    }

    /**
     * 获取当前活动的网络连接
     *
     * @param context
     * @return 活动的连接信息，可能为null
     */
    public static NetworkInfo getActiveNetworkInfo(Context context) {
        if(context == null)
            return null;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = null;
        try {
            activeInfo = manager.getActiveNetworkInfo();
        } catch (Exception e) {
        }
        return activeInfo;
    }

    /**
     * 获取所有NetworkInfo
     *
     * @param context
     * @return
     */
    public static NetworkInfo[] getAllNetworkInfo(Context context) {
        if (context == null)
            return null;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfos[] = null;
        try {
            networkInfos = manager.getAllNetworkInfo();
        } catch (Exception e) {
        }
        return networkInfos;
    }

    public static String getCurrentNetMode(Context context) {
        int netype = NETYPE_NOCON;

        NetworkInfo info = NetworkUtil.getActiveNetworkInfo(context);

        if (null != info) {
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {//使用WiFi联网
                netype = NETYPE_WIFI;
            } else {//使用mobile联网
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                int type = tm.getNetworkType();//mobile types
                switch (type) {
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    netype = NETYPE_2G;
                    break;
                /*case TelephonyManager.NETNETWORK_TYPE_HSPAP:
                    netype = NETYPE_3G;
                	break;*/
                case TelephonyManager.NETWORK_TYPE_LTE:
                    netype = NETYPE_4G;
                    break;
                case TelephonyManager.NETWORK_TYPE_CDMA:
                    netype = NETYPE_TELECOM_2G;
                    break;
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_GPRS:
                    netype = NETYPE_MOBILE_UNICOM_2G;
                    break;
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    netype = NETYPE_TELECOM_3G;
                    break;
                case TelephonyManager.NETWORK_TYPE_HSDPA://HSDPA
                    netype = NETYPE_MOBILE_3G;
                    break;
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_HSPAP://HSPA+
                    netype = NETYPE_UNICOM_3G;
                    break;
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                    netype = NETYPE_4G_UNKNOWN;
                    break;
                default:
                    netype = NETYPE_UNKNOWN;
                    break;
                }
            }
        } else
            netype = NETYPE_NOCON;
        return Integer.toString(netype);
    }

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return true 网络可用
     */
    public static boolean isNetworkAvailable(Context context) {
        try {
            if (isWifiState(context)) {
                return true;
            } else {
                TelephonyManager mTelephonyManager = (TelephonyManager) context
                        .getSystemService(Service.TELEPHONY_SERVICE);
                if (mTelephonyManager.getSimState() != TelephonyManager.SIM_STATE_READY) { // SIM卡没有就绪
                    return false;
                } else {
                    ConnectivityManager cManager = (ConnectivityManager) context
                            .getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo info = cManager.getActiveNetworkInfo();
                    if (info != null && info.isConnectedOrConnecting()) {
                        // 能联网
                        return true;
                    } else {
                        // 不能联网
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * WIFI是否连接
     *
     * @param context
     * @return WIFI已连接 返回 true,否则 false
     */
    public static boolean isWifiConnected(Context context) {

        if (context == null)
            return false;
        boolean isWifiConnected = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            try {
                NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();

                if (activeNetInfo != null) {

                    if (ConnectivityManager.TYPE_WIFI == activeNetInfo.getType() && activeNetInfo.isConnected()) {
                        isWifiConnected = true;
                    } else {
                        isWifiConnected = false;
                    }
                }
            } catch (Exception e) {

            }
        }

        return isWifiConnected;
    }

    /**
     * 判断wifi状态
     * @param context
     * @return true 为可用
     */
    public static boolean isWifiState(Context context) {
        if(context == null)
            return false;
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        int wifiState = -1;
        try {
            wifiState = wifiManager.getWifiState();
        } catch (Exception e) {
            // 防止 NPE 错误
        }
        if (wifiState == WifiManager.WIFI_STATE_ENABLED) { // wifi可用
            return true;
        }
        return false;
    }

    /**
     * 判断传入的 NetWorkInfo是否是wifi已连接
     *
     * @param activeNetInfo NetworkInfo
     * @return 是WIFI且已连接 返回true，否则false
     */
    private static boolean isWifiConnected(NetworkInfo activeNetInfo) {
        boolean isWifiConnected = false;
        try {
            if (activeNetInfo != null) {

                if (ConnectivityManager.TYPE_WIFI == activeNetInfo.getType() && activeNetInfo.isConnected()) {
                    isWifiConnected = true;
                } else {
                    isWifiConnected = false;
                }
            }
        } catch (Exception e) {

        }
        return isWifiConnected;
    }
    
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
