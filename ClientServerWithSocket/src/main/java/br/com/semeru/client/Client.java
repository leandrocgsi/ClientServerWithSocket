package br.com.semeru.client;

import br.com.semeru.utils.ConnectionUtils;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws Exception {
        
        System.out.println("Starting Client!");

        System.out.println("Starting connection with server!");

        Socket socket = new Socket("localhost", 2525);

        System.out.println("Connection established!");

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        PrintStream printStream = new PrintStream(outputStream);


        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Write one message: ");
            String mesage = scanner.nextLine();

            printStream.println(mesage);

            if ("END".equals(mesage)) {
                break;
            }

            mesage = bufferedReader.readLine();
            System.out.println("Message received of the server: "
                    + mesage);

        }

        System.out.println("Finishing Connection");

        ConnectionUtils.stopConnection(bufferedReader, printStream, socket);

    }
}
