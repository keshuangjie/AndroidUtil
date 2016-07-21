package io.github.keshuangjie.androidutil.common;

import io.github.keshuangjie.androidutil.Initializer;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * SharedPreferences封装
 *
 * @author keshuangjie
 * @date 2014-12-2 下午1:33:35
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
	}

    public void putFloat(String key, float value) {
        Editor edit = getEditor();
        edit.putFloat(key, value);
    }

    public float getFloat(String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }
	
	public int getInt(String key){
		return sp.getInt(key, -1);
	}
	
	public Editor getEditor(){
		return sp.edit();
	}

}
