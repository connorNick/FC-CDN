```kotlin
XposedBridge.hookAllMethods(Application::class.java, "dispatchActivityResumed", object : XC_MethodHook() {
    override fun afterHookedMethod(param: MethodHookParam) {
        super.afterHookedMethod(param)

        mCurrentActivity = param.args[0] as Activity
    }
})
```
```kotlin
XposedBridge.hookAllMethods(Instrumentation::class.java, "callApplicationOnCreate", object : XC_MethodHook() {
    override fun afterHookedMethod(param: MethodHookParam) {
        super.afterHookedMethod(param)
        
        mApplication = param.args[0] as Application
    }
})
```
```gradle
maven { url "https://api.xposed.info/" }
```
```gradle
compileOnly "de.robv.android.xposed:api:82"
```
```xml
<meta-data
    android:name="xposedmodule"
    android:value="true" />
<meta-data
    android:name="xposeddescription"
    android:value="" />
<meta-data
    android:name="xposedminversion"
    android:value="93" />
```
```kotlin
IXposedHookLoadPackage
```
