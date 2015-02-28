package com.sinaapp.whutec.util.common;

import com.sinaapp.whutec.util.Initializer;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author keshuangjie
 * @date 2014-12-2 下午1:33:35
 * SharedPreferences封装
 */
public class SharedPreUtil {
	
	private SharedPreferences sp;
	
	public SharedPreUtil(String fileKey){
		sp = Initializer.getContext().getSharedPreferences(fileKey, Context.MODE_PRIVATE);
	}
	
	public void putString(String key, String value){
		Editor edit = getEditor();
		edit.putString(key, value);
		edit.commit();
	}
	
	public String getString(String key){
		return sp.getString(key, "");
	}
	
	public void putInt(String key, int value){
		Editor edit = getEditor();
		edit.putInt(key, value);
		edit.commit();
	}
	
	public boolean getBoolean(String key){
		return getBoolean(key, false);
	}
	
	public boolean getBoolean(String key, boolean defaultValue){
		return sp.getBoolean(key, defaultValue);
	}
	
	public void putBoolean(String key, boolean value){
		Editor edit = getEditor();
		edit.putBoolean(key, value);
		edit.commit();
	}
	
	public int getInt(String key){
		return sp.getInt(key, -1);
	}
	
	public Editor getEditor(){
		return sp.edit();
	}

}
