package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

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
