package com.et.et.customcontrolsdemo.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.io.File;
import java.io.IOException;


/**
 * @author wangxiongfeng
 * @date 2017/7/14 0014 15:02
 */

public class LruCacheUtils {
    private static LruCache<String, Bitmap> stringBitmapLruCache;
    private static int DISK_CACHE_SIZE = 1024 * 1024 * 50;

    public static void lrcchatchBitmap() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int maxCatch = maxMemory / 8;
        stringBitmapLruCache = new LruCache<String, Bitmap>(maxCatch) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    public static Bitmap getCatch(String key) {
        return stringBitmapLruCache.get(key);
    }

    public static void setCatch(String key, Bitmap bitmap) {
        stringBitmapLruCache.put(key, bitmap);
    }

    public static void removeCatch(String key) {
        stringBitmapLruCache.remove(key);
    }

    public static DiskLruCache getDiskLruCatchInstance(File file) throws IOException {
        if (!file.exists()) {
            file.mkdirs();
        }

        return DiskLruCache.open(file, 1, 1, DISK_CACHE_SIZE);
    }
}
