package Controller;

import Model.Item.PostItem;
import Model.PageLoader;
import Model.Post;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import static Model.Main.*;

public class TimeLineController {

    public ListView<Post> postList = new ListView<>();
    //    public ListView<Post> myPost = new ListView<>();

    public ImageView homeButton;
    public ImageView sbuButton;
    public ImageView activityButton;
    public ImageView profileButton;
    public ImageView searchButton;

    @FXML
    public void initialize() {
        lastPage = "TimeLine";
        postList.setItems(FXCollections.observableArrayList(posts));
        postList.setCellFactory(PostList -> new PostItem());
    }

    public void ProfilePage(MouseEvent mouseEvent) throws IOException {
        lastPage = "TimeLine";
        new PageLoader().load("Profile_page");
    }

    public void activityPage(MouseEvent mouseEvent) throws IOException {
        lastPage = "TimeLine";
        new PageLoader().load("ActivityPage");
    }

    public void uploadNewPost(MouseEvent mouseEvent) throws IOException {
        lastPage = "TimeLine";
        new PageLoader().load("UploadNewPost");
    }

    public void refresh(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("TimeLine");
    }
}
