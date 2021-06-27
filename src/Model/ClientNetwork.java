package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
/**
 * <h1>ClientNetwork</h1>
 * <p>this class is connection between client and server in client side</p>
 * @author A.Raei
 * @since 12/2/2021
 * @version 1.0
 */
public class ClientNetwork {
    public static String ServerAddress = "localhost";
    public static final int PORT = 2222;
    private static boolean isConnected = false;
    public static Socket socket;
    public static ObjectInputStream socketIn;
    public static ObjectOutputStream socketOut;

    public static boolean isConnected() {
        return isConnected;
    }

    /**
     * this method connects client to server
     * @return a boolean that shows if client connected or not
     */
    public static Boolean connectToServer() {
        if (socket != null)
            return false;
        try {
            socket = new Socket(ServerAddress, PORT);
            socketOut = new ObjectOutputStream(socket.getOutputStream());
            socketIn = new ObjectInputStream(socket.getInputStream());
            isConnected = true;
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * this method disconnects client from server
     * @return a boolean that shows if client connected or not
     */
    public static Boolean disconnectFromServer(){
        try {
            socketIn.close();
            socketOut.close();
            socket.close();
            isConnected = false;
            socket = null;
            socketIn = null;
            socketOut = null;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        socket = null;
        socketIn = null;
        socketOut = null;
        return false;
    }

    /**
     * this method send massage to server and receive massage from
     * @param toSend its the map that this method send to server
     * @return it returns server answer in map way
     */
    public static Map<String,Object> serve(Map<String,Object> toSend){
        Map<String,Object> get = null;
        try {
            socketOut.writeObject(toSend);
            socketOut.flush();
            socketOut.reset();
            get = (Map<String, Object>) socketIn.readObject();
            return get;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return get;
    }
}
