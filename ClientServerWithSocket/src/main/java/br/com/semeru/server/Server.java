package br.com.semeru.server;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception{
        System.out.println("Starting server");
        
        ServerSocket server = new ServerSocket(2525);
    
        System.out.println("Waiting connection");
        
        Socket socket = server.accept();
        
        System.out.println("Connection OK");
        
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(input));
        PrintStream out = new PrintStream(output);
        
        while (true) {            
            String mensagem = in.readLine();
            
            System.out.println("Message received for the client [" +
                    socket.getInetAddress().getHostName() + "]: " + mensagem);
            if ("FIM".equals(mensagem)) {
                break;
            }
            out.println(mensagem);            
        }
        
        System.out.println("Finishing Connection");
        
        in.close();
        
        out.close();
        
        socket.close();
        
        System.out.println("Stopping Server");
        
        server.close();
        
    }
}
