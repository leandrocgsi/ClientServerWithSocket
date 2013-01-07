package br.com.semeru.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class ConnectionUtils {

    public static void stopConnection(BufferedReader bufferedReader, PrintStream printStream, Socket socket) throws IOException {
        bufferedReader.close();
        printStream.close();
        socket.close();
    }
}
