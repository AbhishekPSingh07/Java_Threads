import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.function.Consumer;

public class Server {

  public Consumer<Socket> getConsumer() {

    // return new Consumer<Socket>(){
    // @Override
    // public void accept(){
    // return (clientSocket) -> {
    // try {
    // PrintWriter toClient = new PrintWriter(clientSocket.getOutoutStream);
    // toClienet.println("hello from the server");
    // toClient.close();
    // toClient.close();
    // }catch(IOException e) {
    // e.printStackTrace();
    // }
    // }
    // }s
    // }

    return (clientSocket) -> {
      try {
        PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
        toClient.println("hello from the server");
        toClient.close();
        toClient.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    };
  }

  public static void main(String[] args) {
    int port = 8010;
    Server server = new Server();
    try {
      ServerSocket serverSocket = new ServerSocket(port);
      serverSocket.setSoTimeout(10000);
      System.out.println("server is listening on the port " + port);

      while (true) {
        Socket acceptedSocket = serverSocket.accept();
        Thread thread = new Thread(() -> server.getConsumer().accept(acceptedSocket));
        thread.start();
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }

  }
}
