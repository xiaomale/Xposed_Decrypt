package com.example.xposed;

import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class Main implements IXposedHookLoadPackage {

	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		// TODO Auto-generated method stub
		if (!lpparam.packageName.equals("com.dtl.rockplayer")) {//注意是被hook应用的包名
			return;
		}

		XposedBridge.log("Loaded app: " + lpparam.packageName);
		Log.e("------>handleLoadPackage", lpparam.packageName);
		XposedHelpers.findAndHookMethod("com.Hfck.f", lpparam.classLoader, "a",//注意是被hook方法所在类的包名+类名（即完整类名）
				String.class, new XC_MethodHook() {
					@Override
					protected void afterHookedMethod(MethodHookParam param)
							throws Throwable {
						XposedBridge.log((String) param.args[0]
								+ "---------------->>>>>>> "
								+ (String) param.getResult());
						Log.e((String) param.args[0]
								+ "---------------->>>>>>> ",
								(String) param.getResult());
					}

				});

	}

}
