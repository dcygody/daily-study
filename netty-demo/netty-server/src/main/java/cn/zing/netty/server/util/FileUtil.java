package cn.zing.netty.server.util;

import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class FileUtil {

    public static String read(File file) throws Exception {
        StringBuilder content = new StringBuilder();
        int num = 0;
        char[] buf = new char[1024];
        FileReader fr = new FileReader(file);
        while ((num = fr.read(buf)) != -1) {
            content.append(new String(buf, 0, num));
        }
        fr.close();
        return content.toString();
    }

    public static void readBin(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[2048];
            int bytesRead;
            // 读取文件内容到缓冲区，直到文件末尾
            while ((bytesRead = fis.read(buffer)) != -1) {
                // 处理读取到的数据，可以根据需要进行操作
                // 这里仅示例打印每个字节的十六进制值
                System.out.println();
                for (int i = 0; i < bytesRead; i++) {
                    System.out.printf("%02X ", buffer[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = Objects.requireNonNull(Objects.requireNonNull(ClassUtils.getDefaultClassLoader()).getResource("")).getPath();

//        readBin("E:\\workspace\\JAVA\\note\\netty-demo\\src\\main\\resources\\310dtu_v1.bin");

        byte[] bytes = new byte[10];
        bytes[0] = 1;

        System.out.println(Arrays.toString(bytes));
    }
}
