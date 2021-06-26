package Server;

import Model.Post;
import Model.Profile;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Server implements Runnable {
    public static  final int port = 2222;
    private static  boolean isServerUp = true;

    public static Map<String, Profile> profiles = new HashMap<>();
    public static Set<Post> posts = null;
    public static ServerSocket serverSocket=null;

    public static boolean isServerUp(){return isServerUp;
    }

    public static void main(String[] args){
        Database.getInstance().initializeServer();
        try {
            serverSocket =new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(new Server());
        thread.start();
    }


    @Override
    public void run() {

      while (isServerUp()){
          Socket currentUserSocket = null;
          try {
              currentUserSocket = serverSocket.accept();
              ClientHandler handler = new ClientHandler(currentUserSocket);
              new Thread(handler).start();
          } catch (IOException e) {
              e.printStackTrace();
          }
      }

    }
}
