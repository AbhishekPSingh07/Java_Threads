import java.net.InetAddress;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client {

  public Runnable getRunnable() {
    return new Runnable() {
      @Override
      public void run() {

        try {
          int port = 8010;
          InetAddress address = InetAddress.getByName("localhost");
          Socket socket = new Socket(address, port);
          PrintWriter toServer = new PrintWriter(socket.getOutputStream());
          BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

          toServer.println("hello from client");
          String line = fromServer.readLine();
          System.out.println("Response from the server is " + line);
          toServer.close();
          fromServer.close();
          socket.close();
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      }
    };
  }

  public static void main(String[] args) {
    Client client = new Client();

    for (int i = 0; i < 100; i++) {
      try {
        Thread thread = new Thread(client.getRunnable());
        thread.start();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}
