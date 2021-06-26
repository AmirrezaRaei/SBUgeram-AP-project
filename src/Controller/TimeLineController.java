package Controller;

import Model.*;
import Model.Item.PostItem;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        Main.update();
        ClientAPI.getAllProfiles(currentUser);
        for (Profile profile :
                profiles.values()) {
            ClientAPI.getAllOfMyPosts(profile);
        }
        List<Post> post_List = ClientAPI.getAllPosts(currentUser);
        Set<Post> postSet = new HashSet<>();
        for (Post post : post_List){
            postSet.add(post);

//            List<String> pu = post.share.stream().map(temp -> temp.getUsername())
//                    .collect(Collectors.toList());
        }
        postList.setItems(FXCollections.observableArrayList(postSet));
        postList.setCellFactory(PostList -> new PostItem());
    }

    public void ProfilePage(MouseEvent mouseEvent) throws IOException {
//        lastPage = "TimeLine";
        new PageLoader().load("Profile_page");
    }

    public void activityPage(MouseEvent mouseEvent) throws IOException {
//        lastPage = "TimeLine";
        new PageLoader().load("ActivityPage");
    }

    public void uploadNewPost(MouseEvent mouseEvent) throws IOException {
//        lastPage = "TimeLine";
        new PageLoader().load("UploadNewPost");
    }

    public void refresh(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("TimeLine");
    }

    public void searchPage(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Search_page");
    }
}
