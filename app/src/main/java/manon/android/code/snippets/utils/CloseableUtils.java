package manon.android.code.snippets.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * create by Manson on 2019/9/30.
 */
public class CloseableUtils {
    private CloseableUtils() {
    }

    /**
     * eg: try{
     * } catch(Exception e) {
     * } finally {
     *     CloseableUtils.closeQuietly();
     * }
     * @param closeable
     */

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
