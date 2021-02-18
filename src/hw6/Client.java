package hw6;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Client {
    private static final String IP_ADDRESS = "localhost";
    private static final int PORT = 8098;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) {
        try (Socket socket = new Socket(IP_ADDRESS, PORT)) {
            System.out.println("[Client started]");

            try (DataInputStream in = new DataInputStream(socket.getInputStream());
                 DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                Thread listenerService = new Thread(() -> {
                   String msg;

                    // Пока клиент не завершит работу читаем сообщения от сервера
                    while (!Thread.currentThread().isInterrupted()) {
                       try {
                           msg = in.readUTF();

                           // Если сервер прислал "команду" завершения сеанса, тогда выходим из цикла
                           if (msg.equals("/end")) {
                               System.out.println("[The server closed the connection]");
                               break;
                           }

                           System.out.printf("[%s] %s\n", dateFormat.format(new Date()), msg);
                       } catch (SocketException e) {
                           System.out.println("[The input stream stopped]");
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
                });
                listenerService.start();

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
                    String msg;

                    // Пока поток чтения сообщений от сервера не завершен, читаем введенные сообщения с консоли
                    while (listenerService.isAlive()) {

                        // Ждем пока пользователь введет сообщение
                        if (reader.ready()) {
                            msg = reader.readLine();

                            // Если между чтением с консоли и отправкой сообщения серверу, сервер не завершил
                            // сеанс - отправляем введенное сообщение
                            // Иначе выходим из цикла
                            if (listenerService.isAlive()) {
                                out.writeUTF(msg);
                                out.flush();

                                // Если пользователь ввел "команду" завершения сеанса, тогда останавливаем поток
                                // чтения сообщений от сервера и выходим из цикла
                                if (msg.equals("/end")) {
                                    listenerService.interrupt();
                                    System.out.println("[The connection is closing...]");
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
