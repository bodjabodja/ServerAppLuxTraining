package com.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by dsk16 on 10/11/2018.
 */
public class Server {
    private int port;
    private StringBuilder content;

    public Server(){

    }

    public void setPort(int port){
            this.port=port;
    }

    public void setWebAppPath(String pathTo) throws IOException {
        File file = new File(pathTo);
        BufferedReader bf = new BufferedReader(new FileReader(file));
        content = new StringBuilder();
        String value;
        while((value = bf.readLine())!= null){
            content.append(value);
        }
    }

    //todo: implement func to send and view style.css
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        try (BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter socketBufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));) {

            //while (true) {
                String message;
                while (!(message = socketBufferedReader.readLine()).isEmpty()) {
                    System.out.println(message);
                }

                //File file = new File("P:\\Users\\dsk16\\IdeaProjects\\ServerAppLuxTraining\\src\\com\\net\\webapp\\" + message);//идем по пути к файлу
                //вычитываем и отдаем на вне(например через стринг билдер
                System.out.println("sending response:");
                socketBufferedWriter.write("HTTP/1.1 200 OK\n");
                socketBufferedWriter.write("\n");
                socketBufferedWriter.write(content.toString());
            //}
        }
    }


    public static void main(String[] args) throws IOException {

            Server server = new Server();
            server.setPort(3000);
            server.setWebAppPath("P:\\Users\\dsk16\\IdeaProjects\\ServerAppLuxTraining\\src\\com\\net\\webapp\\index.html");
            server.start();
    }
}
