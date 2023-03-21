package com.wrlus.xposed.open;

import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wrlus.xposed.CustomHook;
import com.wrlus.xposed.util.DebugTool;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import java.util.Map;

public class HookWebView extends CustomHook {
    private static final String TAG = "HookWebView";
    @Override
    public void onCustomHook(XC_LoadPackage.LoadPackageParam loadPackageParam) {
        try {
            hookAndroidWebView();
        } catch (Exception e) {
            Log.e(TAG, "Hook error.", e);
            e.printStackTrace();
        }
    }

    public static void hookAndroidWebView() {
        XposedHelpers.findAndHookMethod(WebView.class, "loadUrl",
                String.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        String url = (String) param.args[0];
                        Log.i(TAG, "loadUrl: url = " + url);
                        DebugTool.printStackTrace(TAG);
                    }
                });
        XposedHelpers.findAndHookMethod(WebView.class, "loadUrl",
                String.class, Map.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        String url = (String) param.args[0];
                        Map<String, String> additionalHttpHeaders =
                                (Map<String, String>) param.args[1];
                        Log.i(TAG, "loadUrl: url = " + url +
                                ", additionalHttpHeaders = " + additionalHttpHeaders);
                        DebugTool.printStackTrace(TAG);
                    }
                });
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            XposedHelpers.findAndHookMethod(WebViewClient.class, "shouldOverrideUrlLoading",
                    WebView.class, WebResourceRequest.class,
                    new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            WebView webView = (WebView) param.args[0];
                            WebResourceRequest request =
                                    (WebResourceRequest) param.args[1];
                            Log.i(TAG, "shouldOverrideUrlLoading: webView = " + webView +
                                    ", WebResourceRequest.getUrl = " + request.getUrl());
                            DebugTool.printStackTrace(TAG);
                        }
                    });
        }
        XposedHelpers.findAndHookMethod(WebViewClient.class, "shouldOverrideUrlLoading",
                WebView.class, String.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        WebView webView = (WebView) param.args[0];
                        String url = (String) param.args[1];
                        Log.i(TAG, "shouldOverrideUrlLoading: webView = " + webView +
                                ", url = " + url);
                        DebugTool.printStackTrace(TAG);
                    }
                });
        XposedHelpers.findAndHookMethod(WebView.class, "addJavascriptInterface",
                Object.class, String.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Object object = param.args[0];
                        String name = (String) param.args[1];
                        Log.i(TAG, "addJavascriptInterface: object = " +
                                object.getClass().getName() +
                                ", name = " + name);
                        DebugTool.printStackTrace(TAG);
                    }
                });
        XposedHelpers.findAndHookMethod(WebView.class, "removeJavascriptInterface",
                String.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        String name = (String) param.args[0];
                        Log.i(TAG, "removeJavascriptInterface: name = " + name);
                        DebugTool.printStackTrace(TAG);
                    }
                });
    }


}
