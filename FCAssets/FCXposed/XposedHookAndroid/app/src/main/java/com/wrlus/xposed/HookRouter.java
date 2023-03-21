package com.wrlus.xposed;

import android.util.Log;

import com.wrlus.xposed.open.HookDarkTech;
import com.wrlus.xposed.open.HookWebView;
import com.wrlus.xposed.priv.bytedance.jsb.HookBridgeSdk;

import java.util.ArrayList;
import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by wrlu on 2023/2/2.
 */
public class HookRouter implements IXposedHookLoadPackage {
    private static final String TAG = "HookRouter";
    private static final List<Class<? extends CustomHook>> customHookClasses = new ArrayList<>();

    static {
//        customHookClasses.add(HookDarkTech.class);
        customHookClasses.add(HookWebView.class);
        customHookClasses.add(HookBridgeSdk.class);
    }

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) {
        for (Class<? extends CustomHook> customHookClass : customHookClasses) {
            try {
                CustomHook customHook = customHookClass.newInstance();
                Log.d(TAG, "Load custom hook class " + customHook.getClass().getName());
                customHook.onCustomHook(loadPackageParam);
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }
    }
}
