package manon.android.code.snippets.utils;

import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class StringZipUtil {
    private static final String DEFAULT_CHARSET = "ISO-8859-1";

    /**
     * @param input 需要压缩的字符串
     * @return 压缩后的字符串
     */
    public static String compress(String input) {
        String rtn = null;
        if (!TextUtils.isEmpty(input)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gzipOutputStream = null;
            try {
                gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                gzipOutputStream.write(input.getBytes());
                rtn = byteArrayOutputStream.toString(DEFAULT_CHARSET);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                CloseableUtils.closeQuietly(gzipOutputStream);
                CloseableUtils.closeQuietly(byteArrayOutputStream);
            }
        }
        return rtn;
    }

    /**
     * @param zippedString 压缩后的字符串
     * @return 解压缩后的。toString()使用平台默认编码，也可以显式的指定如toString("GBK")
     */
    public static String decompress(String zippedString) {
        String rtn = null;
        if (!TextUtils.isEmpty(zippedString)) {
            ByteArrayOutputStream byteArrayOutputStream = null;
            ByteArrayInputStream byteArrayInputStream = null;
            GZIPInputStream gzipInputStream = null;
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                byteArrayInputStream = new ByteArrayInputStream(zippedString.getBytes(DEFAULT_CHARSET));
                gzipInputStream = new GZIPInputStream(byteArrayInputStream);
                byte[] buffer = new byte[256];
                int n;
                while ((n = gzipInputStream.read(buffer)) >= 0) {
                    byteArrayOutputStream.write(buffer, 0, n);
                }
                rtn = byteArrayOutputStream.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                CloseableUtils.closeQuietly(byteArrayOutputStream);
                CloseableUtils.closeQuietly(byteArrayInputStream);
                CloseableUtils.closeQuietly(gzipInputStream);
            }
        }
        return rtn;
    }
}