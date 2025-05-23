package com.qsky.xposed;

import android.app.Application;
import android.content.Context;

public class HookEnv
{
	public static ClassLoader targetClassLoader;

	public static class TargetInstance
	{
		public static Object mobileQQ;

		public static Context context;

		public static Object appRuntime;

		public static Application application;
	}
}
