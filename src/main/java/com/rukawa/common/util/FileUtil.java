package com.rukawa.common.util;

import java.io.*;
import java.util.Optional;

public class FileUtil {

    /**
     * 获取存在的文件
     *
     * @param fileName 文件名
     * @return Optional<File>
     */
    public static Optional<File> getFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (BeanUtil.isEmpty(parentFile)) {
                String serverPath = System.getProperty("user.dir");
                String filePath = serverPath + File.separator + fileName;
                file = new File(filePath);
                parentFile = file.getParentFile();
            }
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
        }
        return Optional.of(file);
    }

    /**
     * 写文件
     *
     * @param content  文件内容
     * @param fileName 文件名
     */
    public static void writeFile(String content, String fileName) {
        Optional<File> fileOptional = getFile(fileName);
        if (fileOptional.isPresent()) {
            try (OutputStream outputStream = new FileOutputStream(fileOptional.get())) {
                outputStream.write(content.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 写对象
     *
     * @param obj      对象
     * @param fileName 文件名
     */
    public static void writeObject(Object obj, String fileName) {
        Optional<File> fileOptional = getFile(fileName);
        if (fileOptional.isPresent()) {
            try (OutputStream outputStream = new FileOutputStream(fileOptional.get());
                 ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
                oos.writeObject(obj);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读对象
     *
     * @param fileName 文件名
     * @param <T>      泛型T
     * @return T
     */
    public static <T> Optional<T> readObject(Class<T> classT, String fileName) {
        Optional<File> fileOptional = getFile(fileName);
        if (fileOptional.isPresent()) {
            try (InputStream inputStream = new FileInputStream(fileOptional.get());
                 ObjectInputStream ois = new ObjectInputStream(inputStream)) {
                T t = (T) ois.readObject();
                return Optional.of(t);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }
}
