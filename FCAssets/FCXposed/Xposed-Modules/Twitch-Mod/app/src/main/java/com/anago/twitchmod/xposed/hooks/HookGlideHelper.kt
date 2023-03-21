package com.anago.twitchmod.xposed.hooks

import android.content.Context
import android.text.Spanned
import android.widget.TextView
import com.anago.twitchmod.xposed.XposedUtil
import de.robv.android.xposed.XposedHelpers

object HookGlideHelper {
    var glideHelperClass: Class<*>? = null

    fun init(): HookGlideHelper {
        glideHelperClass = XposedUtil.findClass("tv.twitch.android.shared.ui.elements.GlideHelper")
        return this
    }

    fun loadImagesFromSpanned(context: Context, spanned: Spanned, textView: TextView) {
        XposedHelpers.callStaticMethod(
            glideHelperClass, "loadImagesFromSpanned", context, spanned, textView
        )
    }
}