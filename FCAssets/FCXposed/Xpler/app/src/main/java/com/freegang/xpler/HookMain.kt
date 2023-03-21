package com.freegang.xpler

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.freegang.xpler.xp.hookMethod
import com.freegang.xpler.xp.xposedLog
import com.tencent.mm.plugin.webview.ui.tools.WebViewUI
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage

class HookMain : IXposedHookLoadPackage {

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        // do`t write the same hook logic in two methods at the same time, and do not call each other in the same way.
    }

    fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam, application: Application) {
        when (lpparam.packageName) {
            "com.tencent.mm" -> {
                testWechatHook(application)
            }
        }
    }

    // 对微信WebView的Hook测试
    // 打开任意一篇公众号文章就可以看到 `Hello Xpler!!` 的toast提示
    private fun testWechatHook(application: Application) {
        WebViewUI::class.java.hookMethod("onCreate", Bundle::class.java) {
            onAfter {
                val context = thisObject as Context
                val log = "currentApplication=${application}, Hello Xpler!!"
                Toast.makeText(context, log, Toast.LENGTH_SHORT).show()
                xposedLog(log)
            }
        }
    }
}