package cn.zing.dynamic.datasource.api.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPOutputStream;

/**
 * @description:
 * @author: dcy
 * @create: 2023-08-22 11:04
 */
public class FileUtil {

    /**
     * 文件转base64
     */
    public static String file2Base64(String path) {
        String base64Str = null;
        FileInputStream inputStream = null;

        try {
            Base64.Encoder encoder = Base64.getEncoder();

            inputStream = new FileInputStream(path);

            int available = inputStream.available();
            byte[] bytes = new byte[available];
            inputStream.read(bytes);

            base64Str = encoder.encodeToString(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return base64Str;
    }

    /**
     * 数据压缩
     */
    public static byte[] compressData(String data) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipStream = new GZIPOutputStream(byteStream);

        gzipStream.write(data.getBytes(StandardCharsets.UTF_8));
        gzipStream.close();

        return byteStream.toByteArray();
    }

}


