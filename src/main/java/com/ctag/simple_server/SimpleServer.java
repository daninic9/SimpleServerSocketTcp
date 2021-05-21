package com.ctag.simple_server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SimpleServer {

  private ServerSocket serverSocket;
  private Socket clientSocket;
  private PrintWriter out;
  
  private static int PORT = 9001;
  
  private String userMessageM;
   
  public void start(int port) {
      try (Scanner scanner = new Scanner(System.in)) {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        System.out.println("CLIENT CONNECTED: " + clientSocket.getInetAddress());
        out = new PrintWriter(clientSocket.getOutputStream(), true);
      		
        while (true) {
        	System.out.println("Type info to send: ");
	        userMessageM = scanner.nextLine();
	        out.println(userMessageM);
	        
        	System.out.println("--- Message ---");
        	System.out.println(userMessageM);
        	System.out.println("--- Message sent ---");
        	System.out.println();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
  }

  public void stop() {
      try {
        out.close();
        clientSocket.close();
        serverSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
  }
  
  public static void main(String[] args) {
	  System.out.println("START SERVICE");
	  SimpleServer server=new SimpleServer();
      server.start(PORT);
  }
}
