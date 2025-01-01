import java.net.InetAddress;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client {

  public void run() throws UnknownHostException,IOException {
    int port = 8010;
    InetAddress address = InetAddress.getByName("localhost");
    Socket socket = new Socket(address,port);
    PrintWriter toServer = new PrintWriter(socket.getOutputStream());
    BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    toServer.println("hello from client");
    String line = fromServer.readLine();
    System.out.println("Response from the server is " + line);
    toServer.close();
    fromServer.close();
    socket.close();
  }

  public static void main(String[] args) {
     try {
        Client client = new Client();
        client.run();
     }catch(Exception ex) {
       ex.printStackTrace();
     }
   }
}
