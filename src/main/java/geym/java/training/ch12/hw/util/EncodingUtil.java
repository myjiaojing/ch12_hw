
package geym.java.training.ch12.hw.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class EncodingUtil {
    public static String getFilecharset(File sourceFile) {
        String charset = "GBK";
        byte[] first3Bytes = new byte[3];
        BufferedInputStream bis = null;
        try {
            // boolean checked = false;
            bis = new BufferedInputStream(new FileInputStream(sourceFile));
            bis.mark(0);

            int read = bis.read(first3Bytes, 0, 3);

            if (read == -1) {
                return charset; // 文件编码为 ANSI
            } else if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                charset = "UTF-16LE"; // 文件编码为 Unicode
                // checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {

                charset = "UTF-16BE"; // 文件编码为 Unicode big endian
                // checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB
                    && first3Bytes[2] == (byte) 0xBF) {

                charset = "UTF-8"; // 文件编码为 UTF-8
                // checked = true;
            }
            bis.reset();

            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return charset;
    }
}
