package com.xushibo.studyjdkframework.ioframework;

import java.io.*;

/**
 * <p> 同步阻塞IO</p>
 *
 * @author ken1993
 * @date 20210901
 *
 * */
public class BIO {


    public static void copyFileBybyteArray(File source, File dest) throws
            IOException {

        try (InputStream is = new FileInputStream(source);
             OutputStream os = new FileOutputStream(dest);) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        }
    }

    public static void copyFileByStream(File source, File dest) throws
            IOException {

        try (InputStream is = new FileInputStream(source);
             OutputStream os = new FileOutputStream(dest);) {
            int length;
            while ((length=is.read()) != -1) {
                os.write(length);
            }
        }
    }


    public static void copyFileByCharacter(File source, File dest) throws
            IOException {

        try (FileReader is = new FileReader(source);
             FileWriter os = new FileWriter(dest);) {
            int length;
            while ((length=is.read()) != -1) {
                os.write(length);
            }
        }
    }


}
