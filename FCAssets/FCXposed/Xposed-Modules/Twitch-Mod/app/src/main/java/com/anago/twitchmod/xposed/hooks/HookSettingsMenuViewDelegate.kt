package com.anago.twitchmod.xposed.hooks

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.anago.twitchmod.R
import com.anago.twitchmod.xposed.ModuleManager
import com.anago.twitchmod.xposed.Settings
import com.anago.twitchmod.xposed.XposedUtil
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge


object HookSettingsMenuViewDelegate {
    var settingsMenuViewDelegateClass: Class<*>? = null

    fun init(): HookSettingsMenuViewDelegate {
        settingsMenuViewDelegateClass =
            XposedUtil.findClass("tv.twitch.android.feature.settings.menu.SettingsMenuViewDelegate")
        return this
    }

    fun hook() {
        XposedBridge.hookAllMethods(settingsMenuViewDelegateClass, "createGroupView", object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                val viewGroup = param.args[0] as ViewGroup
                val context = viewGroup.context
                viewGroup.findViewWithTag<View?>("ModSettings") ?: run {
                    viewGroup.addView(Button(context).apply {
                        tag = "ModSettings"
                        text = ModuleManager.getString(R.string.mod_settings)
                        setOnClickListener {
                            Settings.showSettingsDialog()
                        }
                    })
                }
            }
        })
    }
}