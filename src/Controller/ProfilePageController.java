package Controller;

import Model.ClientAPI;
import Model.Item.PostItem;
import Model.Main;
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static Model.Main.*;

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
    public ImageView search_button;
    int a = 0, b = 0, c = 0;
    @FXML
    public void initialize() {
        String [] counter = new String[3];
        Main.update();
        List<Post> postList = ClientAPI.getMyPosts(currentUser);
        byte[] image = ClientAPI.getProfile(currentUser);
        if (image != null)
            profile_image.setImage(new Image(new ByteArrayInputStream(image)));

        user_postList.setItems(FXCollections.observableArrayList(currentUser.getPosts()));
        user_postList.setCellFactory(PostList -> new PostItem());

        counter = ClientAPI.getNumbers(currentUser).split("\\|");

        assert counter != null;
        a = Integer.parseInt(counter[0]);
        following_count.setText(String.valueOf(a));
        b = Integer.parseInt(counter[1]);
        followers_count.setText(String.valueOf(b));
        c = Integer.parseInt(counter[2]);
        post_count.setText(String.valueOf(c));

        Map<String, String> information = ClientAPI.getInformation(currentUser);
        username.setText(currentUser.getUsername());
        assert information != null;
        profile_bio.setText(information.get("bio"));
        user_firstname.setText(information.get("firstname"));
        lastPage = "Profile_page";
    }

    public void editProfile(ActionEvent actionEvent) throws IOException {
//        lastPage = "Profile_page";
        new PageLoader().load("EditProfilePage");
    }

    public void homePage(MouseEvent mouseEvent) throws IOException {
//        lastPage = "Profile_page";
        new PageLoader().load("TimeLine");
    }

    public void uploadNewPostPage(MouseEvent mouseEvent) throws IOException {
//        lastPage = "Profile_page";
        new PageLoader().load("UploadNewPost");
    }

    public void activityPage(MouseEvent mouseEvent) throws IOException {
//        lastPage = "Profile_page";
        new PageLoader().load("ActivityPage");
    }

    public void searchPage(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Search_page");
    }
}
