package Controller;

import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static Model.Main.*;

public class PrivateUsersPage {
    // label : counter
    public Label post_count;
    public Label followers_count;
    public Label following_count;
    // label : text
    public Label username;
    public Label profile_bio;
    public Label user_firstname;
    // button
    public Button follow_button;
    public Button unfollow_button;
    
    public ImageView profile_image;
    public ImageView homeButton;
    public ImageView uploadButton;
    public ImageView activityButton;
    public ImageView profileButton;

    @FXML
    public void initialize(){
        post_count.setText(String.valueOf(visitCurrentUser.myPosts.size()));
        followers_count.setText(String.valueOf(visitCurrentUser.followers.size()));
        following_count.setText(String.valueOf(visitCurrentUser.following.size()));
        username.setText(visitCurrentUser.getUsername());
        profile_bio.setText(visitCurrentUser.getBio());
        user_firstname.setText(visitCurrentUser.getFirstname());
        profile_image.setImage(visitCurrentUser.profileImage.getImage());
        if (visitCurrentUser.requested.contains(currentUser)) {
            follow_button.setVisible(false);
            unfollow_button.setVisible(true);
        }

    }

    public void follow(ActionEvent actionEvent) {
        visitCurrentUser.requested.add(currentUser);
        follow_button.setVisible(false);
        unfollow_button.setVisible(true);
    }

    public void unfollow(ActionEvent actionEvent) {
        visitCurrentUser.requested.remove(currentUser);
        unfollow_button.setVisible(false);
        follow_button.setVisible(true);
    }

    public void homePage(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("TimeLine");
    }

    public void uploadPage(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("UploadNewPost");
    }

    public void activityPage(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("ActivityPage");
    }

    public void profilePage(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Profile_page");
    }
}