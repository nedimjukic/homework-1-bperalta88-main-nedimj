package server;

import handler.BaseHandler;
import handler.HandlerFactory;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import request.CustomParser;
import request.ParsedRequest;
import response.CustomHttpResponse;

public class Server {

  public static void main(String[] args) {
    ServerSocket serverSocket;
    Socket socket = null;
    try {
      serverSocket = new ServerSocket(1299);
      System.out.println("Opened socket " + 1299);
      while (true) {
        // keeps listening for new clients, one at a time
        try {
          socket = serverSocket.accept(); // waits for client here
        } catch (IOException e) {
          System.out.println("Error opening socket");
          System.exit(1);
        }

        InputStream stream = socket.getInputStream();
        byte[] b = new byte[1024*20];
        stream.read(b);
        String input = new String(b).trim();
        System.out.println(input);

        BufferedOutputStream out = new BufferedOutputStream(socket.getOutputStream());
        PrintWriter writer = new PrintWriter(out, true);  // char output to the client

        // HTTP Response
        if(!input.isEmpty()){
          writer.println(processRequest(input));
        }else{
          writer.println("HTTP/1.1 200 OK");
          writer.println("Server: TEST");
          writer.println("Connection: close");
          writer.println("Content-type: text/html");
          writer.println("");
        }

        socket.close();
      }
    } catch (IOException e) {
      System.out.println("Error opening socket");
      System.exit(1);
    }
  }

  // Assume the http server feeds the entire raw http request here
  // Response is a raw http response string
  public static String processRequest(String requestString){
    ParsedRequest request = CustomParser.parse(requestString);
    BaseHandler handler = HandlerFactory.getHandler(request);

    /*added this here, since not founding the right handler should be checked earlier than handling the request
    because this should be part of routing, not particular handler and there are better ways to handle this
    situation but this one was the fastest for me */

    if (handler == null){
      return "HTTP/1.1 404 Not Found\n";
    }

    CustomHttpResponse response = handler.handleRequest(request);
    response.headers.put("Content-type", "application/json");
    return response.toString();
  }

}
