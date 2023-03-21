package com.wrlus.xposed.util;

public class DebugTool {
    public static void printStackTrace(String tag) {
        try {
            throw new NullPointerException("get stack");
        } catch (NullPointerException e) {
            android.util.Log.d(tag, "[StackTrace] ", e);
        }
    }
}
