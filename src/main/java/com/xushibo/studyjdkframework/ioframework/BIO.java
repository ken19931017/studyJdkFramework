package com.xushibo.studyjdkframework.ioframework;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p> 同步阻塞IO</p>
 *
 * @author ken1993
 * @date 20210901
 */
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
            while ((length = is.read()) != -1) {
                os.write(length);
            }
        }
    }


    public static void copyFileByCharacter(File source, File dest) throws
            IOException {

        try (FileReader is = new FileReader(source);
             FileWriter os = new FileWriter(dest);) {
            int length;
            while ((length = is.read()) != -1) {
                os.write(length);
            }
        }
    }

    /**
     * 服务器端启动 ServerSocket，端口 0 表示自动绑定一个空闲端口。
     * 调用 accept 方法，阻塞等待客户端连接。
     * 利用 Socket 模拟了一个简单的客户端，只进行连接、读取、打印。
     * 当连接建立后，启动一个单独线程负责回复客户端请求。
     */
    public static class DemoServer extends Thread {
        private ServerSocket serverSocket;

        public int getPort() {
            return serverSocket.getLocalPort();
        }

        public void run() {
            try {
                serverSocket = new ServerSocket(0);
                while (true) {
                    Socket socket = serverSocket.accept();
                    RequestHandler requestHandler = new RequestHandler(socket);
                    requestHandler.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (serverSocket != null) {
                    try {
                        serverSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ;
                }
            }
        }

        // 简化实现，不做读取，直接发送字符串
        static class RequestHandler extends Thread {
            private Socket socket;

            RequestHandler(Socket socket) {
                this.socket = socket;
            }

            @Override
            public void run() {
                try (PrintWriter out = new PrintWriter(socket.getOutputStream());) {
                    out.println("Hello world!");
                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public static void main(String[] args) throws IOException {
            DemoServer server = new DemoServer();
            server.start();
            try (Socket client = new Socket(InetAddress.getLocalHost(), server.getPort())) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                bufferedReader.lines().forEach(s -> System.out.println(s));
            }
        }
    }

}
