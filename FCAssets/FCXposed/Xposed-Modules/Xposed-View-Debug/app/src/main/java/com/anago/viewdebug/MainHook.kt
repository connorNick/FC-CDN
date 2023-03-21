package com.anago.viewdebug

import android.app.Activity
import android.app.AlertDialog
import android.app.Application
import android.app.Instrumentation
import android.content.DialogInterface
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_UP
import android.view.View
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.callbacks.XC_LoadPackage


class MainHook : IXposedHookLoadPackage {
    private var dialog: AlertDialog? = null
    private lateinit var mApplication: Application
    private lateinit var mCurrentActivity: Activity

    override fun handleLoadPackage(loadPackageParam: XC_LoadPackage.LoadPackageParam) {
        XposedBridge.hookAllMethods(Application::class.java, "dispatchActivityResumed", object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                super.afterHookedMethod(param)

                mCurrentActivity = param.args[0] as Activity
            }
        })

        XposedBridge.hookAllMethods(Instrumentation::class.java, "callApplicationOnCreate", object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                super.afterHookedMethod(param)

                mApplication = param.args[0] as Application
            }
        })

        XposedBridge.hookAllMethods(View::class.java, "dispatchTouchEvent", object : XC_MethodHook() {
            override fun beforeHookedMethod(param: MethodHookParam) {
                val event = param.args[0] as MotionEvent
                if (event.action == ACTION_UP) {
                    val view = param.thisObject as View
                    if (view.tag == "ANAGO_VIEW")
                        return
                    showSizeDialog(view.javaClass.name, view.width, view.height)
                }
            }
        })
    }

    private fun showSizeDialog(viewName: String, width: Int, height: Int) {
        dialog?.dismiss()
        val density = mApplication.resources.displayMetrics.density
        val dpWidth = (width / density).toInt()
        val dpHeight = (height / density).toInt()
        dialog = AlertDialog.Builder(mCurrentActivity)
            .setTitle(viewName)
            .setMessage("Width: ${dpWidth}dp\nHeight: ${dpHeight}dp")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
        dialog?.getButton(DialogInterface.BUTTON_POSITIVE)?.tag = "ANAGO_VIEW"
    }
}