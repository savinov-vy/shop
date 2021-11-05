package ru.savinov.shop.webservices;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

@Service
public class TimeSocketService {
    private final String URL = "india.colorado.edu";
    private final Integer PORT = 13;
    private final Integer TIME_OUT = 2000;


    public void exactlyTime() {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(URL, PORT), TIME_OUT);
            Scanner scanner = new Scanner(socket.getInputStream());
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
