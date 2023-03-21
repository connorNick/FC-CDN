package com.anago.twitchmod.xposed.hooks

import android.annotation.SuppressLint
import android.app.Activity
import com.anago.twitchmod.xposed.XposedUtil
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

@SuppressLint("StaticFieldLeak")
object HookApplication {
    var applicationClass: Class<*>? = null

    fun init(): HookApplication {
        applicationClass = XposedUtil.findClass("android.app.Application")
        return this
    }

    var mCurrentActivity: Activity? = null

    fun hook() {
        XposedBridge.hookAllMethods(applicationClass, "dispatchActivityResumed", object : XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                mCurrentActivity = param.args[0] as Activity
            }
        })
    }
}