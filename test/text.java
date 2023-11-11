import java.io.*;
import java.net.*;
import java.util.Scanner;

public class text {

  public static void main(String[] args) {
    try (Socket socket = new Socket("103.17.88.39", 12345)) {
      System.out.println("Connected to the server.");
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(
        new InputStreamReader(socket.getInputStream())
      );

      // Get the user's name
      System.out.println("Enter your name: ");
      Scanner scanner = new Scanner(System.in);
      String userName = scanner.nextLine();
      out.println(userName); // Send the user's name to the server

      Thread receiveThread = new Thread(() -> {
        String message;
        try {
          while ((message = in.readLine()) != null) {
            System.out.println(message);
          }
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
      receiveThread.start();

      while (true) {
        String userInput = scanner.nextLine();
        out.println(userInput); // Send the user's name along with the message
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
