package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    public static Profile currentUser = new Profile();
    public static Profile visitCurrentUser = new Profile();// to see other user profile
    public static Post currentPost = new Post();
    public static Comment currentComment = new Comment();
    public static Request currentRequest = new Request();

    public static ArrayList<Post> posts = new ArrayList<>();

    public static String lastPage;
    public static ArrayList<Profile> users = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
       PageLoader.initStage(primaryStage);
        new PageLoader().load("Login");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        System.out.println("program opened");
    }

    public void stop(){
        System.out.printf("Good bye\n Comeback soon :)");
    }
}
