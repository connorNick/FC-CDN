package com.anago.twitchmod.xposed

import com.anago.twitchmod.xposed.hooks.*
import com.anago.twitchmod.xposed.utils.Logger
import de.robv.android.xposed.IXposedHookInitPackageResources
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.IXposedHookZygoteInit
import de.robv.android.xposed.callbacks.XC_InitPackageResources
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam


class MainHook : IXposedHookLoadPackage, IXposedHookZygoteInit, IXposedHookInitPackageResources {

    override fun handleLoadPackage(loadPackageParam: LoadPackageParam) {
        if (!loadPackageParam.packageName.equals("tv.twitch.android.app"))
            return
        Logger.d("start ${loadPackageParam.packageName}")
        XposedUtil.setPackage(loadPackageParam)
        HookApplication.init().hook()
        HookSettingsMenuViewDelegate.init().hook()
        HookGlideHelper.init()
        HookMessageRecyclerItem.init().hook()
        HookPlayerViewDelegate.init().hook()
        HookLiveAdsCoordinatorPresenter.init().hook()
        HookCommunityPointsButtonViewDelegate.init().hook()
        Logger.d("ussu")
    }

    override fun initZygote(startupParam: IXposedHookZygoteInit.StartupParam) {
        ModuleManager.startupParam = startupParam
    }

    override fun handleInitPackageResources(resparam: XC_InitPackageResources.InitPackageResourcesParam) {
        ModuleManager.resparam = resparam
    }
}