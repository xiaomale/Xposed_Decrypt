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
		if (!lpparam.packageName.equals("com.dtl.rockplayer")) {//ע���Ǳ�hookӦ�õİ���
			return;
		}

		XposedBridge.log("Loaded app: " + lpparam.packageName);
		Log.e("------>handleLoadPackage", lpparam.packageName);
		XposedHelpers.findAndHookMethod("com.Hfck.f", lpparam.classLoader, "a",//ע���Ǳ�hook����������İ���+������������������
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
