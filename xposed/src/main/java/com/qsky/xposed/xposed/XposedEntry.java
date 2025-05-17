package com.qsky.xposed.xposed;

import android.util.Log;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
  
public class XposedEntry implements IXposedHookLoadPackage, IXposedHookInitPackageResources
{
    public static final String TAG = "Xposed_Entry";

    private static final String TARGET_PACKAGE_NAME = "com.tencent.mobileqq";

    public static final HashMap<String, String> STRING_RES_HASH_MAP = new HashMap<String, String>();

    static
    {
        STRING_RES_HASH_MAP.put("fsy", "乾坤枢机定");
        STRING_RES_HASH_MAP.put("228", "玉牒锁金钥");
        STRING_RES_HASH_MAP.put("fsa", "青鸟衔云笺");
        STRING_RES_HASH_MAP.put("wjw", "阴阳两仪决");
        STRING_RES_HASH_MAP.put("frw", "四海通衢道");
        STRING_RES_HASH_MAP.put("0yj", "秘阁掩玄机");
        STRING_RES_HASH_MAP.put("2an", "采薇录青简");
        STRING_RES_HASH_MAP.put("2b8", "琼枝寄瑶台");
        STRING_RES_HASH_MAP.put("2ao", "金瓯护灵枢");
        STRING_RES_HASH_MAP.put("fre", "青鸾扶摇鉴");
        STRING_RES_HASH_MAP.put("c6n", "拂袖别云阙");
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable
    {
        if (loadPackageParam.packageName.equals(TARGET_PACKAGE_NAME) || !loadPackageParam.isFirstApplication) return;
        Log.i("Xposed", "Successful to Loaded");
        File file = new File("/storage/emulated/0/OpenQSky");
        file.mkdirs();
    }

    @Override
    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam initPackageResourcesParam) throws Throwable
    {
        if (!initPackageResourcesParam.packageName.equals(TARGET_PACKAGE_NAME)) return;
        for (Map.Entry<String, String> entry : STRING_RES_HASH_MAP.entrySet())
        {
            initPackageResourcesParam.res.setReplacement (
                TARGET_PACKAGE_NAME,
                "string",
                entry.getKey(),
                entry.getValue()
            );
        }
    }
}