package com.anago.twitchmod.xposed

import com.anago.twitchmod.xposed.utils.Logger
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam

object XposedUtil {
    var loadPackageParam: LoadPackageParam? = null
    fun setPackage(loadPackageParam: LoadPackageParam) {
        this.loadPackageParam = loadPackageParam
    }

    fun findClass(className: String): Class<*>? {
        if (loadPackageParam == null) {
            throw NullPointerException("loadPackageParam must not be null")
        }
        val clazz: Class<*>? = XposedHelpers.findClassIfExists(className, loadPackageParam!!.classLoader)
        Logger.d("<<< loading class >>>")
        Logger.d(className)
        Logger.d(" found: ${clazz?.simpleName}")
        Logger.d("<<< loaded class >>>")
        return clazz
    }
}