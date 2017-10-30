package com.example.dm.zhbit.utiltools;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/5/10.
 */

public class ScreenShotUtils {
    public ScreenShotUtils() {
    }

    public static Bitmap takeScreenShot(Activity pActivity) {
        Bitmap bitmap = null;
        View view = pActivity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        bitmap = view.getDrawingCache();
        Rect frame = new Rect();
        view.getWindowVisibleDisplayFrame(frame);
        int stautsHeight = frame.top;
        Log.d("jiangqq", "状态栏的高度为:" + stautsHeight);
        int width = pActivity.getWindowManager().getDefaultDisplay().getWidth();
        int height = pActivity.getWindowManager().getDefaultDisplay().getHeight();
        bitmap = Bitmap.createBitmap(bitmap, 0, stautsHeight, width, height - stautsHeight);
        return bitmap;
    }

    private static boolean savePic(Bitmap pBitmap, String strName) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(strName);
            if(fos != null) {
                pBitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.flush();
                fos.close();
                return true;
            }
        } catch (FileNotFoundException var4) {
            var4.printStackTrace();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return false;
    }

    public static boolean shotBitmap(Activity pActivity) {
        return savePic(takeScreenShot(pActivity), "sdcard/" + System.currentTimeMillis() + ".png");
    }
}
