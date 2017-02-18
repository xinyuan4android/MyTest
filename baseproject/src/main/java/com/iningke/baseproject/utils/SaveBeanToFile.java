package com.iningke.baseproject.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveBeanToFile {

    /**
     * 从文件读取,返回对象
     *
     * @param clazz 对象的字节码
     * @param file  缓存文件
     * @return 文件的反序列化对象
     */
    @SuppressWarnings("unchecked")
    public static <T> T readFileToBean(Class<T> clazz, File file) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            T bean = (T) clazz.newInstance();
            bean = (T) ois.readObject();
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将对象转换成文件
     *
     * @param object 要序列换成文件的对象
     * @param file   缓存文件
     * @return 是否缓存成功
     */
    public static boolean beanToFile(Object object, File file) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(object);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 直接将json序列化成文件
     *
     * @param json  json
     * @param clazz 对象的字节码文件
     * @param file  缓存的文件
     */
    public static void JsonToBeanToFile(String json, Class<?> clazz, File file) {
        Object object = JSONUtils.readValue(json, clazz);
        beanToFile(object, file);
    }

    /**
     * 删除文件
     *
     * @param file 要删除的文件
     * @return
     */
    public static boolean deleteFile(File file) {
        if (file != null) {
            if (file.exists())
                if (file.isFile()) {
                    file.delete();
                    return true;
                }
        }
        return false;
    }
}
