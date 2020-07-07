package pkg01.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerSocketTest {

    public static void main(String[] args) throws Exception {

        System.out.println("聊天室服务端正在运行......");

        ServerSocket serverSocket = new ServerSocket(9000);

        ArrayList<Socket> sockets = new ArrayList<>();

        while (true) {

            Socket accept = serverSocket.accept();

            sockets.add(accept);

            ChatThread chatThread = new ChatThread(accept, sockets);

            chatThread.start();
        }

    }
}

class ChatThread extends Thread {

    public ArrayList<Socket> sockets;

    public Socket socket;
    public ChatThread (Socket socket, ArrayList<Socket> sockets) {
        this.sockets = sockets;
        this.socket = socket;
    }

    public void run () {

        try {

            InputStream inputStream = socket.getInputStream();

            while (true) {

                byte[] bytes = new byte[1024];

                int read = inputStream.read(bytes);

                if (read != -1) {

                    String receiveMessage = new String(bytes, 0, read);

                    System.out.println("服务器端接受到了消息 -> " + receiveMessage);

                    for(Socket socket: sockets) {

                        if (socket.equals(this.socket)) {
                            continue;
                        }

                        OutputStream outputStream1 = socket.getOutputStream();
                        outputStream1.write(receiveMessage.getBytes());
                        outputStream1.flush();
                    }

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}
