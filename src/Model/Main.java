package Model;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main extends Application {
    public static Profile currentUser = new Profile();
    public static Profile visitCurrentUser = new Profile();// to see other user profile
    public static Post currentPost = new Post();
    public static Comment currentComment = new Comment();
    public static Request currentRequest = new Request();

    public static List<Post> posts = new CopyOnWriteArrayList<>();

    public static String lastPage;
    public static Map<String, Profile> users = new ConcurrentHashMap<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        PageLoader.initStage(primaryStage);
        new PageLoader().load("Login");
    }

    public static void main(String[] args) {
        ClientNetwork.connectToServer();
        launch(args);
    }

    @Override
    public void init() {
        System.out.println("program opened");
    }

    public void stop() {
        System.out.print("Good bye\n Comeback soon :)");
    }
}
