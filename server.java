import java.io.*;
import java.net.*;

public class server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is listening on port 12345");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            String message;

            while ((message = input.readLine()) != null) {
                System.out.println("Client: " + message);
                if ("bye".equalsIgnoreCase(message)) {
                    System.out.println("Client disconnected");
                    break;
                }
                output.println("Server: " + message);
            }

            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}