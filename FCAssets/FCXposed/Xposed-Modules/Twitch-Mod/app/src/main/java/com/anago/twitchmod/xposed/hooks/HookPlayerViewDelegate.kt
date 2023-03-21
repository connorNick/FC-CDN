package com.anago.twitchmod.xposed.hooks

import android.widget.FrameLayout
import com.anago.twitchmod.xposed.Settings
import com.anago.twitchmod.xposed.XposedUtil
import com.anago.twitchmod.xposed.view.ChatView
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge

object HookPlayerViewDelegate {
    var playerViewDelegateClass: Class<*>? = null

    fun init(): HookPlayerViewDelegate {
        playerViewDelegateClass =
            XposedUtil.findClass("tv.twitch.android.shared.player.viewdelegates.PlayerViewDelegate")
        return this
    }

    fun hook() {
        XposedBridge.hookAllConstructors(playerViewDelegateClass, object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                super.afterHookedMethod(param)
                val layout = param.args[1] as FrameLayout

                ChatView.instance = null
                if (!Settings.getBoolean(Settings.HIDE_FLOATING_CHAT)) {
                    ChatView.instance!!.setPlayerView(layout)
                    layout.addView(ChatView.instance!!.getChatView())
                }
            }
        })
    }
}