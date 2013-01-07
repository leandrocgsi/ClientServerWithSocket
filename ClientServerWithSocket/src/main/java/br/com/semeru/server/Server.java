package br.com.semeru.server;

import br.com.semeru.utils.ConnectionUtils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting server");

        ServerSocket serverSocket = new ServerSocket(2525);

        System.out.println("Waiting connection");

        Socket socket = serverSocket.accept();

        System.out.println("Connection established");

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        PrintStream printStream = new PrintStream(outputStream);

        while (true) {
            String mesage = bufferedReader.readLine();

            System.out.println("Message received for the client ["
                    + socket.getInetAddress().getHostName() + "]: " + mesage);
            if ("END".equals(mesage)) {
                break;
            }
            printStream.println(mesage);
        }

        System.out.println("Finishing Connection");

        ConnectionUtils.stopConnection(bufferedReader, printStream, socket);

        System.out.println("Stopping Server");

        serverSocket.close();

    }
}
