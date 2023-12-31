package cn.zing.dubbos.framework.register;


import cn.zing.dubbos.framework.URL;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注册中心
 */
public class RemoteMapRegister {

    /**
     * 注册中心 list模拟集群
     */
    private static Map<String, List<URL>> REGISTER = new HashMap<>();


    public static void regist(String interfaceName, URL url){

        List<URL> list = REGISTER.get(interfaceName);
        if (list == null) {
            list = new ArrayList<>();

        }
        list.add(url);

        REGISTER.put(interfaceName, list);

        saveFile();
    }

    public static List<URL> get(String interfaceName) {
        REGISTER = getFile();

        List<URL> list = REGISTER.get(interfaceName);
        return list;
    }


    private static void saveFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/temp.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(REGISTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<URL>> getFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/temp.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<URL>>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
