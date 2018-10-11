package com.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by dsk16 on 10/11/2018.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        Socket socket = serverSocket.accept();
        try (BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter socketBufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {

            String message;
            while (!(message = socketBufferedReader.readLine()).isEmpty()) {
                System.out.println(message);
            }

            System.out.println("sending response:");
            socketBufferedWriter.write("HTTP/1.1 200 OK\n");
            socketBufferedWriter.write("\n");
            socketBufferedWriter.write("Hello browser!");
        }
    }
}