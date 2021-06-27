package Model;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
/**
 * <h1>Main</h1>
 * <p>this class runs the client side</p>
 * @author A.Raei
 * @since 12/2/2021
 * @version 1.0
 */
public class Main extends Application {
    public static Profile currentUser = new Profile();
    public static Profile targetUser = new Profile();// to see other user profile
    public static Post targetPost = new Post();
//    public static Post currentPost = new Post();
//    public static Comment currentComment = new Comment();
    public static Request currentRequest = new Request();

    public static List<Post> posts = new CopyOnWriteArrayList<>();

    public static String lastPage;
    public static Map<String, Profile> profiles = new ConcurrentHashMap<>();

    /**
     * this method opens the program
     * @param primaryStage it uses for initialize basic stage
     * @throws Exception because of using pageLoader
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        PageLoader.initStage(primaryStage);
        new PageLoader().load("Login");
    }
    /**
     * it connect the client to server and runs the client side
     * @param args it uses for opening program
     */
    public static void main(String[] args) {
        ClientNetwork.connectToServer();
        launch(args);
    }

    /**
     * this method update information in client side
     */
    public static void update() {
        Map<String, Profile> profileMap;
        if (ClientAPI.getPosts(currentUser) != null)
            posts = new CopyOnWriteArrayList<>(ClientAPI.getAllPosts(currentUser));
        profileMap = ClientAPI.getAllProfiles(currentUser);
        if (profileMap != null)
            profiles = profileMap;
        for (Profile profile :
                profiles.values()) {
            if (ClientAPI.getAllOfMyPosts(profile) != null) {
                profile.getPosts().clear();
                profile.getPosts().addAll(ClientAPI.getAllOfMyPosts(profile));
            }
        }
    }

        @Override
        public void init () {
            System.out.println("program opened");
        }

        public void stop () {
            System.out.print("Good bye\n Comeback soon :)");
        }
    }
