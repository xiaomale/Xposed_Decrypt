package com.example.xposed;

import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class OtherImp implements IXposedHookLoadPackage {
	public String TAG = "AqCxBoM";
	private final String mStrPackageName = "aqcxbom.xposedhooktarget"; // HOOK
																		// APP目标的包名
	private final String mStrClassPath = "aqcxbom.xposedhooktarget.MyClass"; // HOOK
																				// 目标类全路径
	private final String mStrMethodName = "fun1"; // HOOK 目标函数名

	private void LOGI(String ct) {
		Log.d(TAG, ct);
	}

	@Override
	public void handleLoadPackage(
			XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
		// 判断包名是否一致
		if (loadPackageParam.packageName.equals(mStrPackageName)) {
			LOGI("found target: " + loadPackageParam.packageName);
			final Class<?> ArgClass = XposedHelpers.findClass(
					"aqcxbom.xposedhooktarget.ArgClass",
					loadPackageParam.classLoader);
			final Class<?> ArrayList = XposedHelpers.findClass(
					"java.util.ArrayList", loadPackageParam.classLoader);
			final Class<?> Map = XposedHelpers.findClass("java.util.Map",
					loadPackageParam.classLoader);
			// 包名一致时查找是否有匹配参数的类及函数
			XposedHelpers.findAndHookMethod(mStrClassPath, // 类路径
					loadPackageParam.classLoader, // ClassLoader
					mStrMethodName, // 目标函数名
					"[[Ljava.lang.String;", // 参数1
					Map, // 参数2
					Map, // 参数3
					Map, // 参数4
					ArrayList, // 参数5
					ArrayList, // 参数6
					ArgClass, // 参数7
					new XC_MethodHook() {
						@Override
						protected void beforeHookedMethod(MethodHookParam param)
								throws Throwable {
							super.beforeHookedMethod(param); // 这个函数会在被hook的函数执行前执行
							LOGI("beforeHook");
						}

						@Override
						protected void afterHookedMethod(MethodHookParam param)
								throws Throwable {
							super.afterHookedMethod(param);// 这个函数会在被hook的函数执行后执行
							LOGI("afterHooke param: ");
						}
					});
		}
	}
}
