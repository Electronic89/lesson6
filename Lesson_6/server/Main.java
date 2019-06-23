package Lesson_6.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Main {
    private Vector<ClientHandler> clients;

    public Main() {
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;

        try {
            server = new ServerSocket(8189);
            System.out.println("Сервер запущен");

            while (true) {
                socket = server.accept();
                new ClientHandler(socket, this);
                System.out.println("Клиент подключился");


            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
               socket.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public  synchronized void broadCastMsg(String msg)  {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }
}
