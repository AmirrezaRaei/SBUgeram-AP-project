package Server;

import Common.Time;
import Model.Post;
import Model.Profile;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <h1>Server</h1>
 * <p>this class is the server's main and run the server side</p>
 * @author A.Raei
 * @version 1.0
 * @since 12/2/2021
 */
public class Server implements Runnable {
    public static final int port = 2222;
    private static boolean isServerUp = true;

    public static Map<String, Profile> profiles = new HashMap<>();
    public static Set<Post> posts = null;
    public static ServerSocket serverSocket = null;

    public static boolean isServerUp() {
        return isServerUp;
    }

    /**
     * this method initialize all information in database and runs the server
     * it make a new socket and start listening
     */
    public static void main(String[] args) {
        Database.getInstance().initializeServer();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread thread = new Thread(new Server());
        thread.start();
    }

    /**
     * this method is a socket that always listening and after connecting a new client, it makes a new client handler and new thread for it
     */
    @Override
    public void run() {

        while (isServerUp()) {
            Socket currentUserSocket = null;
            try {
                currentUserSocket = serverSocket.accept();
                System.out.println("connect");
                System.out.println("Time : " + Time.getTime());
                ClientHandler handler = new ClientHandler(currentUserSocket);
                new Thread(handler).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
