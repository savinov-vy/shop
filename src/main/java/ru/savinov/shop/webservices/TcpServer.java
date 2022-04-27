package ru.savinov.shop.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Слушает указанный в первом параметре порт и выводит все приходящие на него
 * сообщения на консоль.
 */
public class TcpServer {
    private static final int DEFAULT_PORT = 9999;

    public static void main(String[] args) {
        /* Если аргументы отсутствуют, порт принимае значение поумолчанию */
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        /* Создаем серверный сокет на полученном порту */
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Порт занят: " + port);
            System.exit(-1);
        }
        /*
         * Если порт был свободен и сокет был успешно создан, можно переходить к
         * следующему шагу - ожиданию клинта
         */
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Ошибка при подключении к порту: " + port);
            System.exit(-1);
        }
        /*
         * Теперь можно получить поток ввода, в который помещаются сообщения от
         * клиента
         */
        InputStream in = null;
        try {
            in = clientSocket.getInputStream();
        } catch (IOException e) {
            System.out.println("Не удалось получить поток ввода.");
            System.exit(-1);
        }
        /*
         * В этой реализации сервер будет без конца читать поток и выводить его
         * содержимое на консоль
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String ln = null;
        try {
            while ((ln = reader.readLine()) != null) {
                System.out.println(ln);
                System.out.flush();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении сообщения.");
            System.exit(-1);
        }
    }
}
