package manon.android.code.snippets.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.Locale;

import manon.android.code.snippets.BuildConfig;

/**
 * Created by Manson on 2015/7/22.
 */
public class WTFLog {
    public final static String TAG;
    private final static String TAG_CLASS = "Class: ";
    private final static char SPACE = ' ';
    private final static boolean DEBUG_ABLE;

    static {
        TAG = BuildConfig.APPLICATION_ID.substring(BuildConfig.APPLICATION_ID.lastIndexOf(".") + 1).toUpperCase(Locale.US);
        boolean fileExist;
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "DEBUG");
            fileExist = file.exists();
        } catch (Exception e) {
            fileExist = false;
        }
        DEBUG_ABLE = BuildConfig.DEBUG | fileExist;
        _log(Log.DEBUG, WTFLog.class, "PACKAGE_NAME:" + BuildConfig.APPLICATION_ID + " VERSION_NAME:" + BuildConfig.VERSION_NAME + " VERSION_CODE:" + BuildConfig.VERSION_CODE);
    }

    public static void d(Class<?> clazz, Object... objects) {
        if (DEBUG_ABLE) {
            _log(Log.DEBUG, clazz, objects);
        }
    }

    public static void i(Class<?> clazz, Object... objects) {
        if (DEBUG_ABLE) {
            _log(Log.INFO, clazz, objects);
        }
    }

    public static void e(Class<?> clazz, Object... objects) {
        if (DEBUG_ABLE) {
            _log(Log.ERROR, clazz, objects);
        }
    }

    public static void e(Throwable throwable) {
        throwable.printStackTrace();
    }

    private static void _log(int flag, Class<?> clazz, Object... objects) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TAG_CLASS);
        stringBuilder.append(clazz.getSimpleName());
        stringBuilder.append(SPACE);
        if (objects != null) {
            for (Object object : objects) {
                stringBuilder.append(object);
                stringBuilder.append(SPACE);
            }
        }
        String string = stringBuilder.toString();
        switch (flag) {
            case Log.VERBOSE:
                Log.v(TAG, string);
                break;
            case Log.DEBUG:
                Log.d(TAG, string);
                break;
            case Log.INFO:
                Log.i(TAG, string);
                break;
            case Log.WARN:
                Log.w(TAG, string);
                break;
            case Log.ERROR:
                Log.e(TAG, string);
                break;
        }
    }
}
