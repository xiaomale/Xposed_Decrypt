package com.example.xposed;

import android.util.Log;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class OtherImp implements IXposedHookLoadPackage {
	public String TAG = "AqCxBoM";
	private final String mStrPackageName = "aqcxbom.xposedhooktarget"; // HOOK
																		// APPĿ��İ���
	private final String mStrClassPath = "aqcxbom.xposedhooktarget.MyClass"; // HOOK
																				// Ŀ����ȫ·��
	private final String mStrMethodName = "fun1"; // HOOK Ŀ�꺯����

	private void LOGI(String ct) {
		Log.d(TAG, ct);
	}

	@Override
	public void handleLoadPackage(
			XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
		// �жϰ����Ƿ�һ��
		if (loadPackageParam.packageName.equals(mStrPackageName)) {
			LOGI("found target: " + loadPackageParam.packageName);
			final Class<?> ArgClass = XposedHelpers.findClass(
					"aqcxbom.xposedhooktarget.ArgClass",
					loadPackageParam.classLoader);
			final Class<?> ArrayList = XposedHelpers.findClass(
					"java.util.ArrayList", loadPackageParam.classLoader);
			final Class<?> Map = XposedHelpers.findClass("java.util.Map",
					loadPackageParam.classLoader);
			// ����һ��ʱ�����Ƿ���ƥ��������༰����
			XposedHelpers.findAndHookMethod(mStrClassPath, // ��·��
					loadPackageParam.classLoader, // ClassLoader
					mStrMethodName, // Ŀ�꺯����
					"[[Ljava.lang.String;", // ����1
					Map, // ����2
					Map, // ����3
					Map, // ����4
					ArrayList, // ����5
					ArrayList, // ����6
					ArgClass, // ����7
					new XC_MethodHook() {
						@Override
						protected void beforeHookedMethod(MethodHookParam param)
								throws Throwable {
							super.beforeHookedMethod(param); // ����������ڱ�hook�ĺ���ִ��ǰִ��
							LOGI("beforeHook");
						}

						@Override
						protected void afterHookedMethod(MethodHookParam param)
								throws Throwable {
							super.afterHookedMethod(param);// ����������ڱ�hook�ĺ���ִ�к�ִ��
							LOGI("afterHooke param: ");
						}
					});
		}
	}
}
