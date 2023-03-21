package com.anago.twitchmod.xposed.hooks

import com.anago.twitchmod.xposed.Settings
import com.anago.twitchmod.xposed.XposedUtil
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge

object HookLiveAdsCoordinatorPresenter {
    var liveAdsCoordinatorPresenterClass: Class<*>? = null

    fun init(): HookLiveAdsCoordinatorPresenter {
        liveAdsCoordinatorPresenterClass =
            XposedUtil.findClass("tv.twitch.android.feature.theatre.ads.LiveAdsCoordinatorPresenter")
        return this
    }

    fun hook() {
        XposedBridge.hookAllMethods(liveAdsCoordinatorPresenterClass, "attachForRxLiveStream", adBlock)
        XposedBridge.hookAllMethods(liveAdsCoordinatorPresenterClass, "attachForLiveStream", adBlock)
        XposedBridge.hookAllMethods(liveAdsCoordinatorPresenterClass, "attachForLiveStream", adBlock)
        XposedBridge.hookAllMethods(liveAdsCoordinatorPresenterClass, "inflateAndBindPbyp", adBlock)
        XposedBridge.hookAllMethods(liveAdsCoordinatorPresenterClass, "bindAudioAds", adBlock)
    }

    private val adBlock: XC_MethodHook = object : XC_MethodHook() {
        override fun beforeHookedMethod(param: MethodHookParam) {
            if (Settings.getBoolean(Settings.AD_BLOCK)) {
                param.result = null
                return
            }
        }
    }
}