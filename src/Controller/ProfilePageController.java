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

/**
 * <h1>ProfilePageController</h1>
 * <p>this controller page profile page </p>
 *
 * @author A.Raei
 * @version 1.0
 * @since 12/2/2021
 */
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
        String[] counter = new String[3];
        Main.update();
        ClientAPI.getMyPosts(currentUser);
        byte[] image = ClientAPI.getProfile(currentUser);
        if (image != null)
            profile_image.setImage(new Image(new ByteArrayInputStream(image)));

        user_postList.setItems(FXCollections.observableArrayList(currentUser.myPosts));
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

    /**
     * its in user's personal page and user can edit its information here
     *
     * @param actionEvent by click on a button
     * @throws IOException because of using pageLoader
     */
    public void editProfile(ActionEvent actionEvent) throws IOException {
//        lastPage = "Profile_page";
        new PageLoader().load("EditProfilePage");
    }

    /**
     * it opens user's personal page
     *
     * @param mouseEvent by click on a button
     * @throws IOException because of using page Loader
     */
    public void homePage(MouseEvent mouseEvent) throws IOException {
//        lastPage = "Profile_page";
        new PageLoader().load("TimeLine");
    }

    /**
     * it opens upload page
     *
     * @param mouseEvent by click on a button
     * @throws IOException because of using page Loader
     */
    public void uploadNewPostPage(MouseEvent mouseEvent) throws IOException {
//        lastPage = "Profile_page";
        new PageLoader().load("UploadNewPost");
    }

    /**
     * it opens user's activityPage page
     *
     * @param mouseEvent by click on a button
     * @throws IOException because of using page Loader
     */
    public void activityPage(MouseEvent mouseEvent) throws IOException {
//        lastPage = "Profile_page";
        new PageLoader().load("ActivityPage");
    }

    /**
     * user can go search page  this method
     *
     * @param mouseEvent by click on a button
     * @throws Exception because of using page Loader
     */
    public void searchPage(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Search_page");
    }
}
