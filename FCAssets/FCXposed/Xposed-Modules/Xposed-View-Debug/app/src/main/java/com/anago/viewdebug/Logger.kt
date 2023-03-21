package com.anago.viewdebug

import android.util.Log
import de.robv.android.xposed.XposedBridge

object Logger {
    private const val TAG = "ANAGO_XPOSED"

    fun d(msg: String?){
        Log.d(TAG, msg.toString())
        XposedBridge.log(msg.toString())
    }
}