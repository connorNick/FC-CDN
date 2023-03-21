package com.anago.twitchmod.xposed

import android.content.res.XModuleResources
import android.content.res.XmlResourceParser
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.callbacks.XC_InitPackageResources

object ModuleManager {
    private var resources: XModuleResources? = null
    var startupParam: IXposedHookZygoteInit.StartupParam? = null
    var resparam: XC_InitPackageResources.InitPackageResourcesParam? = null

    private fun getXModuleResources(): XModuleResources? {
        if (resources == null)
            resources = XModuleResources.createInstance(startupParam!!.modulePath, resparam!!.res)
        return resources
    }

    fun getString(id: Int): String {
        return getXModuleResources()?.getString(id).toString()
    }

    fun getLayout(id: Int): XmlResourceParser? {
        return getXModuleResources()?.getLayout(id)
    }
}