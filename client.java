import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345)) {
            System.out.println("Connected to the server");

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            String message;

            while (true) {
                System.out.print("You: ");
                message = scanner.nextLine();
                output.println(message);

                if ("bye".equalsIgnoreCase(message)) {
                    System.out.println("Disconnected from the server");
                    break;
                }

                System.out.println(input.readLine());
            }

            scanner.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
