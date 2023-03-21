package com.anago.adblocker

import com.anago.adblocker.hooks.All
import com.anago.adblocker.util.XposedUtil.setPackage
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage


class MainHook : IXposedHookLoadPackage {
    override fun handleLoadPackage(loadPackageParam: XC_LoadPackage.LoadPackageParam) {
        setPackage(loadPackageParam)

        All.hook()
    }
}