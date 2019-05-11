package com.wl.dataturbo;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Auther: wl
 * @Date: 2019/5/11 10:28
 * @Description:
 */
public class Utils {

    public static String copyAssetAndWrite(Context context, String fileName) throws IOException {
        File cacheDir = context.getCacheDir();
        if (!cacheDir.exists()) {
            cacheDir.mkdir();
        }

        File outFile = new File(cacheDir, fileName);
        if (!outFile.exists()) {
            boolean newFile = outFile.createNewFile();
            if (newFile) {
                InputStream is = context.getAssets().open(fileName);
                FileOutputStream fos = new FileOutputStream(outFile);
                byte[] bytes = new byte[is.available()];
                int byteCount;
                while ((byteCount = is.read(bytes)) != -1) {
                    fos.write(bytes, 0, byteCount);
                }
                fos.flush();
                is.close();
                fos.close();
                Log.e("copyAssetAndWrite", "文件下载成功");
                return outFile.getAbsolutePath();
            }
        }
        return "";
    }
}
