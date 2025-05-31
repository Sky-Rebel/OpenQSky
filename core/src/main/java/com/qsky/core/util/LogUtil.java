package com.qsky.core.util;

import android.annotation.SuppressLint;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil
{
	public static final String TAG = "Core_Util_Log";

	public static final int DEBUG = 101;

	public static final int INFO = 102;

	public static final int WARN = 103;

	public static final int ERROR = 104;

	public static final int FATAL = 105;

	public static int logLevel = INFO;

	public static final int LOG_LINE = 2;

	private static final String DIR_NAME = "/log";

	private static final String FILE_NAME = "/qsky_log.log";

	@Deprecated
	private static final String DATA_PATH = "/storage/emulated/0/QSky";

	public static final File logDir = new File(DATA_PATH, DIR_NAME);

	public static final File logFile = new File(logDir, FILE_NAME);

	@SuppressLint({"NewApi", "SimpleDateFormat"})
	public static final String SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());

	public static void d(String message)
	{
		log(DEBUG,null, message);
	}

	public static void d(String tag, String message)
	{
		log(DEBUG,tag, message);
	}

	public static void i(String message)
	{
		log(INFO,null, message);
	}

	public static void i(String tag, String message)
	{
		log(INFO,tag, message);
	}

	public static void w(String message)
	{
		log(WARN,null, message);
	}

	public static void w(String tag, String message)
	{
		log(WARN,tag, message);
	}

	public static void w(String tag, String message, Throwable throwable)
	{
		log(WARN,tag, message, throwable);
	}

	public static void e(String message)
	{
		log(ERROR,null, message);
	}

	public static void e(String tag, String message)
	{
		log(ERROR,tag, message);
	}

	public static void e(String tag, String message, Throwable throwable)
	{
		log(ERROR,tag, message, throwable);
	}

	public static void f(String message)
	{
		log(FATAL,null, message);
	}

	public static void f(String tag, String message)
	{
		log(FATAL,tag, message);
	}

	public static void f(String tag, String message, Throwable throwable)
	{
		log(FATAL,tag, message, throwable);
	}

	private static void log(int level, String tag, String message)
	{
		log(level, tag, message,null);
	}

	private static void log(int level, String tag, String message, Throwable throwable)
	{
		if (level > logLevel) return;
		StringBuilder logEntry = new StringBuilder();
		logEntry.append("[");
		logEntry.append(SIMPLE_DATE_FORMAT);
		logEntry.append("] ");
		logEntry.append(
			level == DEBUG ? "DEBUG -> " :
			level == INFO ? "INFO -> " :
			level == WARN ? "WARN -> " :
			level == ERROR ? "ERROR -> " :
			level == FATAL ? "FATAL -> " : "NULL-> "
		);
		logEntry.append(tag != null ? "[" + tag + "]" + (char)32 : "");
		logEntry.append(message != null ? message : "");
		if (level >= 104 && throwable != null)
		{
			StackTraceElement[] stackTraceElements = throwable.getStackTrace();
			for (StackTraceElement stackTraceElement : stackTraceElements)
			{
				String className = stackTraceElement.getClassName();
				String methodName = stackTraceElement.getMethodName();
				String fileName = stackTraceElement.getFileName();
				int lineNum = stackTraceElement.getLineNumber();
				logEntry.append((char)10);
				logEntry.append("at ");
				logEntry.append(className);
				logEntry.append(".");
				logEntry.append(methodName);
				logEntry.append("(");
				logEntry.append(fileName);
				logEntry.append(":");
				logEntry.append(lineNum);
			}
		}
		printLogToFile(logEntry.toString());
	}

	public static void printLogToFile(String content)
	{
		if (!logDir.exists() & !logDir.mkdirs()) return;
		try (
			FileWriter fileWriter = new FileWriter(logFile, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		)
		{
			bufferedWriter.write(content);
			for (int i = 0 ; i < LOG_LINE ; i++)
			{
				bufferedWriter.newLine();
			}
		}
		catch (IOException ioException) {}
	}
}
