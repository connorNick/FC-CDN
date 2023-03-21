package com.anago.twitchmod.xposed.hooks

import android.text.Spanned
import com.anago.twitchmod.xposed.XposedUtil
import com.anago.twitchmod.xposed.view.ChatView
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge

object HookMessageRecyclerItem {
    private var messageRecyclerItemClass: Class<*>? = null

    fun init(): HookMessageRecyclerItem {
        messageRecyclerItemClass =
            XposedUtil.findClass("tv.twitch.android.shared.chat.adapter.item.MessageRecyclerItem",)
        return this
    }

    fun hook(){
        XposedBridge.hookAllConstructors(messageRecyclerItemClass, object : XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val message = param.args[6] ?: return
                HookApplication.mCurrentActivity?.runOnUiThread {
                    ChatView.instance!!.addMessage(message as Spanned)
                }
            }
        })
    }
}