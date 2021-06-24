package Server;

import Common.Action;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

public class ClientHandler implements Runnable {
    private Socket userSocket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    public Boolean clientOnline = true;

    /**
     * only constructor of this class
     * it give socket and create object input output stream and save them
     **/
    public ClientHandler(Socket socket) {
        try {
            userSocket = socket;
            this.objectOutputStream = new ObjectOutputStream(userSocket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(userSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        Map<String, Object> input, output;
        while (true) {
            input = null;

            try {
                input = (Map<String, Object>) objectInputStream.readObject();
                output = null;
                Action action = (Action) input.get("action");
                switch (action) {
                    case login:
                        output = API.login(input);
                        break;
                    case logout:
                        output = API.logout(input);
                    case signup:
                        output = API.signup(input);
                        break;
                    case addPost:
                        output = API.addPost(input);
                        break;
                    case getPosts:
                        output = API.getPosts(input);
                        break;
                    case getMyPost:
                        output = API.getMyPosts(input);
                        break;
                    case getProfiles:
                        output = API.getProfiles(input);
                        break;
                    case follow:
                        output = API.follow(input);
                        break;
                    case unfollow:
                        output = API.unfollow(input);
                        break;
                    case getProfile:
                        output = API.getProfile(input);
                        break;
                    case setProfile:
                        output = API.setProfile(input);
                        break;
                    case getInformation:
                        output = API.getInformation(input);
                        break;
                    case setInformation:
                        output = API.setInformation(input);
                        break;
                    case like:
                        output = API.like(input);
                        break;
                    case dislike:
                        output = API.unlike(input);
                        break;
                    case repost:
                        output = API.repost(input);
                        break;
                    case unReposted:
                        output = API.unRepost(input);
                        break;
                    case getComment:
                        output = API.getComment(input);
                        break;
                    case setComment:
                        output = API.setComment(input);
                        break;
                }
                objectOutputStream.writeObject(output);
                objectOutputStream.flush();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            } catch (IOException e) {
                break;
            }
        }
        try {
            objectInputStream.close();
            objectInputStream.close();
            userSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
