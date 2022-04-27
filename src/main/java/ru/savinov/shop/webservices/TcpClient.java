package ru.savinov.shop.webservices;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Подключается к серверу и передает ему сообщения вводимые пользователем в
 * консоли. Хост сервера указвается первым аргументом, порт вторым. При
 * отсутствии аргументов в качестве адреса порта принимается localhost:9999
 */
public class TcpClient {
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 9999;

    public static void main(String[] args) {
        /* Определяем хост сервера и порт */
        String host = DEFAULT_HOST;
        int port = DEFAULT_PORT;
        if (args.length > 0) {
            host = args[0];
        }
        if (args.length > 1) {
            port = Integer.parseInt(args[1]);
        }
        /*
         * Создаем сокет для полученной пары хост/порт
         */
        Socket socket = null;
        try {
            socket = new Socket(host, port);
        } catch (UnknownHostException e) {
            System.out.println("Неизвестный хост: " + host);
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода при создании сокета " + host
                    + ":" + port);
            System.exit(-1);
        }
        /*
         * Для удобства обернем стандартный поток ввода в BufferedReader
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        /*
         * Получаем поток вывода, через который будут передаваться сообщения
         * серверу
         */
        OutputStream out = null;
        try {
            out = socket.getOutputStream();
        } catch (IOException e) {
            System.out.println("Не удалось получить поток вывода.");
            System.exit(-1);
        }
        /*
         * Все вводимые пользователем сообщения будем транслировать в поток вывода
         * созданного сокета
         */
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        String ln = null;
        try {
            while ((ln = reader.readLine()) != null) {
                writer.write(ln + "\n");
                writer.flush();
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи сообщения.");
            System.exit(-1);
        }
    }
}
