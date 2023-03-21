package com.anago.adblocker.hooks

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.anago.adblocker.util.Logger
import com.anago.adblocker.util.XposedUtil.findClass
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge

object All {
    fun hook() {
        try {
            val adActivityClass = findClass("com.google.android.gms.ads.AdActivity")
            XposedBridge.hookAllMethods(adActivityClass, "onCreate", object : XC_MethodHook() {
                override fun beforeHookedMethod(methodHookParam: MethodHookParam) {
                    val activity = methodHookParam.thisObject as Activity
                    activity.finish()
                }
            })

            val mobileAdsClass = findClass("com.google.android.gms.ads.MobileAds")
            XposedBridge.hookAllMethods(
                mobileAdsClass,
                "registerWebView",
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(methodHookParam: MethodHookParam) {
                        val webView = methodHookParam.args[0] as WebView
                        webView.visibility = View.GONE
                    }
                })

            XposedBridge.hookAllMethods(ViewGroup::class.java, "addView", object : XC_MethodHook() {
                override fun beforeHookedMethod(methodHookParam: MethodHookParam) {
                    val view = methodHookParam.args[0] as View
                    hideIfAd(view)
                }
            })

            XposedBridge.hookAllMethods(
                View::class.java,
                "setVisibility",
                object : XC_MethodHook() {
                    override fun beforeHookedMethod(methodHookParam: MethodHookParam) {
                        if (methodHookParam.args[0] != View.GONE) {
                            val view = methodHookParam.thisObject as View
                            hideIfAd(view)
                        }
                    }
                })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun hideIfAd(view: View) {
        if (view.javaClass.simpleName.contains("AdView", true)) {
            view.visibility = View.GONE
        }
    }
}