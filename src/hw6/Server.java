package hw6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    private static final int PORT = 8098;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(PORT)) {
            System.out.println("[Server started]");

            try (Socket socket = server.accept()) {
                System.out.println("[Client connected]");

                try (DataInputStream in = new DataInputStream(socket.getInputStream());
                     DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

                    Thread listenerService = new Thread(() -> {
                        String msg;

                        // Пока сервер не завершит работу читаем сообщения от клиента
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                msg = in.readUTF();

                                // Если клиент прислал "команду" завершения сеанса, тогда выходим из цикла
                                if (msg.equals("/end")) {
                                    System.out.println("[The client has completed the connection]");
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

                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
                        String msg;

                        // Пока поток чтения сообщений от клиента не завершен, читаем введенные сообщения с консоли
                        while (listenerService.isAlive()) {

                            // Ждем пока пользователь введет сообщение
                            if (reader.ready()) {
                                msg = reader.readLine();
                                // Если между чтением с консоли и отправкой сообщения клиенту, клиент не завершил
                                // сеанс - отправляем введенное сообщение
                                // Иначе выходим из цикла
                                if (listenerService.isAlive()) {
                                    out.writeUTF(msg);
                                    out.flush();

                                    // Если пользователь ввел "команду" завершения сеанса, тогда останавливаем поток
                                    // чтения сообщений от клиента и выходим из цикла
                                    if (msg.equals("/end")) {
                                        System.out.println("[The connection is closing...]");
                                        listenerService.interrupt();
                                        break;
                                    }
                                } else {
                                    break;
                                }
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
