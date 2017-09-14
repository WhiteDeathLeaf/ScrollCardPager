package com.galaxylight.scrollcardpager.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片工具类
 * Created by gzh on 2017-9-14.
 */

public class ImageUtil {
    private ImageUtil() {
    }

    /**
     * 将图片文件转换成bitmap
     *
     * @param context context
     * @return Bitmap集合
     */
    public static List<Bitmap> getBitmap(Context context) {
        List<Bitmap> bitmaps = new ArrayList<>();
        for (String fileName : getImageFile(context)) {
            bitmaps.add(BitmapFactory.decodeFile(fileName));
        }
        return bitmaps;
    }

    /**
     * 获取图片文件
     *
     * @param context context
     * @return 图片文件集合
     */
    public static List<File> getFile(Context context) {
        List<File> files = new ArrayList<>();
        for (String file : getImageFile(context)) {
            files.add(new File(file));
        }
        return files;
    }

    /**
     * 获取手机内所有图片
     *
     * @param context context
     * @return 图片文件名集合
     */
    private static List<String> getImageFile(Context context) {
        List<String> fileNames = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                byte[] data = cursor.getBlob(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                fileNames.add(new String(data, 0, data.length - 1));
            }
            cursor.close();
        }
        return fileNames;
    }

}
