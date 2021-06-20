package Controller;

import Model.Item.PostItem;
import Model.PageLoader;
import Model.Post;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static Model.Main.currentUser;
import static Model.Main.lastPage;

public class ProfilePageController {
    // label : counter
    public Label post_count;
    public Label followers_count;
    public Label following_count;
    public Label user_firstname;
    // label : text
    public Label username;
    public Label profile_bio;

    public ImageView profile_image;
    public ListView<Post> user_postList = new ListView<>();

    public Button editProfile_button;
    public ImageView homeButton;
    public ImageView uploadNewPostButton;
    public ImageView activityButton;

    @FXML
    public void initialize(){
        post_count.setText(String.valueOf(currentUser.myPosts.size()));
        followers_count.setText(String.valueOf(currentUser.followers.size()));
        following_count.setText(String.valueOf(currentUser.following.size()));
        username.setText(currentUser.getUsername());
        profile_bio.setText(currentUser.getBio());
        user_firstname.setText(currentUser.getFirstname());
        profile_image.setImage(currentUser.profileImage.getImage());
        lastPage = "Profile_page";

        user_postList.setItems(FXCollections.observableArrayList(currentUser.myPosts));
        user_postList.setCellFactory(PostList -> new PostItem());
    }
    public void editProfile(ActionEvent actionEvent) throws IOException {
        lastPage = "Profile_page";
        new PageLoader().load("EditProfilePage");
    }

    public void homePage(MouseEvent mouseEvent) throws IOException {
        lastPage = "Profile_page";
        new PageLoader().load("TimeLine");
    }

    public void uploadNewPostPage(MouseEvent mouseEvent) throws IOException {
        lastPage = "Profile_page";
        new PageLoader().load("UploadNewPost");
    }

    public void activityPage(MouseEvent mouseEvent) throws IOException {
        lastPage = "Profile_page";
        new PageLoader().load("ActivityPage");
    }
}
