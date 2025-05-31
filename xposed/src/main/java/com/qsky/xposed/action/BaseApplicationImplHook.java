package com.qsky.xposed.action;

import android.app.Application;
import android.content.Context;

import com.qsky.core.util.LogUtil;
import com.qsky.xposed.HookEnv;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class BaseApplicationImplHook
{
	private static final String TAG = "Hook_BaseApplicationImpl";

	private static final String TARGET_METHOD = "onCreate";

	private static final String TARGET_CLASS = ".BaseApplication";

	private static final String TARGET_PACKAGE = "com.tencent.mobileqq.mqsafeedit";

	private static final String TARGET_FULLY_QUALIFIED_NAME = TARGET_PACKAGE + TARGET_CLASS;

	public static void hook()
	{
		XC_MethodHook xc_methodHook = new XC_MethodHook()
		{
			@Override
			protected void afterHookedMethod(MethodHookParam methodHookParam) throws Throwable
			{
				LogUtil.d(TAG, "afterHookedMethod");
				HookEnv.TargetInstance.context = (Context) methodHookParam.thisObject;
				HookEnv.TargetInstance.application = (Application) methodHookParam.thisObject;
				HookEnv.TargetInstance.appRuntime = XposedHelpers.callMethod(HookEnv.TargetInstance.application, "getRuntime");
				HookEnv.TargetInstance.mobileQQ = XposedHelpers.callMethod(HookEnv.TargetInstance.appRuntime, "getApplication");
			}
		};
		XposedHelpers.findAndHookMethod(TARGET_FULLY_QUALIFIED_NAME, HookEnv.targetClassLoader, TARGET_METHOD, xc_methodHook);
	}
}
