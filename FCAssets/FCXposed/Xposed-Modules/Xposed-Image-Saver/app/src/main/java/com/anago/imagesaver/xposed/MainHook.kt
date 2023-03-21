package com.anago.imagesaver.xposed

import android.app.AndroidAppHelper
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import android.os.Environment
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmapOrNull
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import java.io.File
import java.io.FileOutputStream

class MainHook : IXposedHookLoadPackage {
    private var saveDir =
        File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "ImageSaver").apply { mkdirs() }

    override fun handleLoadPackage(loadPackageParam: XC_LoadPackage.LoadPackageParam) {
        val imageViewClass = XposedHelpers.findClass("android.widget.ImageView", loadPackageParam.classLoader)
        XposedBridge.hookAllMethods(imageViewClass, "updateDrawable", object : XC_MethodHook() {
            override fun afterHookedMethod(param: MethodHookParam) {
                super.afterHookedMethod(param)
                Thread {
                    val mDrawable: Any =
                        XposedHelpers.findField(imageViewClass, "mDrawable").get(param.thisObject) ?: return@Thread
                    val bitmapDrawable = mDrawable as? BitmapDrawable ?: return@Thread
                    saveDrawable(bitmapDrawable, File(saveDir, AndroidAppHelper.currentPackageName()).apply { mkdirs() })
                }.start()
            }
        })
    }

    fun saveDrawable(drawable: Drawable, dir: File) {
        if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0)
            return
        val bitmap: Bitmap = drawable.toBitmapOrNull() ?: return
        FileOutputStream(File(dir, "$drawable.png")).use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
    }
}