package io.github.keshuangjie.androidutil.message.dispatch;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;
import android.os.Message;
import android.util.SparseArray;

/**
 * 消息代理
 *
 * @author keshuangjie
 * @date 2014-12-29 下午5:08:06
 */
public class MessageProxy {
	
    private static SparseArray<List<Handler>> mHandlers = new SparseArray<List<Handler>>();

    /**
     * 注册消息监听
     *
     * @param msgId
     * @param handler
     */
    public static void registerMessageHandler(int msgId, Handler handler) {
        if (handler == null)
            return;
        synchronized (mHandlers) {
            List<Handler> handlers = mHandlers.get(msgId);
            if (handlers != null) {
                if (!handlers.contains(handler)) {
                    handlers.add(handler);
                }
            } else {
                handlers = new ArrayList<Handler>();
                handlers.add(handler);
                mHandlers.put(msgId, handlers);
            }
        }
    }

    /**
     * 取消消息监听
     *
     * @param msgId
     * @param handler
     */
    public static void unRegisterMessageHandler(int msgId, Handler handler) {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            synchronized (mHandlers) {
                List<Handler> handlers = mHandlers.get(msgId);
                if (handlers != null)
                    handlers.remove(handler);
            }
        }
    }

    /**
     * 消息分发
     *
     * @param what
     * @param arg1
     * @param arg2
     * @param target
     */
    public static void dispatchMessage(int what, int arg1, int arg2,int target) {

        synchronized (mHandlers) {
            final List<Handler> handlers = mHandlers.get(what);
            if (handlers != null && !handlers.isEmpty()) {
                for (Handler handler : handlers) {
                    Message msg = Message.obtain(handler, what, arg1, arg2, target);
                    msg.sendToTarget();
                }
            }
        }
    }

    /**
     * 清除所有消息监听
     */
    public static void destory() {
        int size = mHandlers.size();
        for (int i = 0; i < size; i++) {
            List<Handler> handlers = mHandlers.get(mHandlers.keyAt(i));
            if (handlers != null) {
            	handlers.clear();
            	handlers = null;
            }
        }
        mHandlers.clear();
        mHandlers = null;
    }

}
