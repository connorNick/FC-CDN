package com.anago.twitchmod.xposed.hooks

import android.view.ViewGroup
import com.anago.twitchmod.xposed.Settings
import com.anago.twitchmod.xposed.XposedUtil
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers

object HookCommunityPointsButtonViewDelegate {
    var communityPointsButtonViewDelegate: Class<*>? = null
    fun init(): HookCommunityPointsButtonViewDelegate {
        communityPointsButtonViewDelegate =
            XposedUtil.findClass(
                "tv.twitch.android.shared.community.points.viewdelegate.CommunityPointsButtonViewDelegate",
            )
        return this
    }

    fun hook() {
        XposedBridge.hookAllMethods(communityPointsButtonViewDelegate, "showClaimAvailable", object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                if (Settings.getBoolean(Settings.AUTO_CLAIM_POINTS)) {
                    val buttonLayout = XposedHelpers.findField(communityPointsButtonViewDelegate, "buttonLayout")
                        .get(param.thisObject) as ViewGroup
                    buttonLayout.performClick()
                }
            }
        })
    }
}