package com.gcrabtree.rctsocketio;

import android.util.Log;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableNativeMap;

import java.util.HashMap;

import io.socket.client.IO;

/**
 * Created by Greg Crabtree on 5/17/16.
 */

public class SocketIoReadableNativeMap extends ReadableNativeMap {
    private static final String TAG = "SIOReadableNativeMap";

    protected SocketIoReadableNativeMap(HybridData hybridData) {
      super(hybridData);
    }

    /**
     * This converts a SocketIoReadableNativeMap to a SocketIO Option object.
     * @param options ReadableNativeMap that is a JS bridged hash of options.
     * @return IO.Options object that has been populated. Currently incomplete. PRs welcome.
     */
    public static IO.Options mapToOptions(ReadableNativeMap options) {
        ReadableMapKeySetIterator iterator = options.keySetIterator();
        IO.Options opts = new IO.Options();

        while (iterator.hasNextKey()) {
            String key = iterator.nextKey().toLowerCase();
            switch (key) {
                case "force new connection":
                case "forcenew":
                    opts.forceNew = options.getBoolean(key);
                    break;
                case "multiplex":
                    opts.multiplex = options.getBoolean(key);
                    break;
                case "reconnection":
                    opts.reconnection = options.getBoolean(key);
                    break;
                case "connect_timeout":
                    opts.timeout = options.getInt(key);
                    break;
                default:
                    Log.e(TAG, "Could not convert object with key: " + key + ".");
            }
        }
        return opts;
    }
}
