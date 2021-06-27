package Controller;

import Model.ClientAPI;
import Model.Item.PostItem;
import Model.PageLoader;
import Model.Post;
import Model.Profile;
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
 * <h1>PostItemController</h1>
 * <p>this class shows all information of a user </p>
 *
 * @author A.Raei
 * @version 1.0
 * @since 12/2/2021
 */
public class PublicUsersPageController {
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
    public ListView<Post> user_postList = new ListView<>();

    public ImageView homeButton;
    public ImageView uploadButton;
    public ImageView activityButton;
    public ImageView profileButton;
    public ImageView searchButton;
    byte[] image;
    public Profile profile;
    public static Profile temp;
    int a = 0, b = 0, c = 0;
    int help = 0, i = 0;

    @FXML
    public void initialize() {
        lastPage = "PublicUsersPage";
        profile = targetUser;
        username.setText(profile.getUsername());
        image = ClientAPI.getProfile(profile);
        Image newImage = new Image(new ByteArrayInputStream(image));
        if (newImage != null)
            profile_image.setImage(newImage);
        Map<String, String> data = ClientAPI.getInformation(profile);
        assert data != null;
        user_firstname.setText(data.get("firstname"));
        profile_bio.setText(data.get("bio"));

        String[] details = ClientAPI.getProfilesNumber(currentUser, profile).split("\\|");

        assert details != null;
        a = Integer.parseInt(details[0]);
        following_count.setText(String.valueOf(a));
        b = Integer.parseInt(details[1]);
        followers_count.setText(String.valueOf(b));
        c = Integer.parseInt(details[2]);
        post_count.setText(String.valueOf(c));

        /**
         * check current user follow this page or not
         */
        List<String> list = ClientAPI.getFollowers(targetUser);
        assert targetUser != null;
        assert list != null;
        if (list.contains(currentUser.getUsername())) {
            follow_button.setVisible(false);
            unfollow_button.setVisible(true);
        }

        ClientAPI.getMyPosts(targetUser);
        user_postList.setItems(FXCollections.observableArrayList(targetUser.getPosts()));
        user_postList.setCellFactory(posts -> new PostItem());
    }

    /**
     * the user can follow target user with this method
     *
     * @param actionEvent by click on a button
     */
    public void follow(ActionEvent actionEvent) {
        String[] string = ClientAPI.follow(currentUser, targetUser).split("\\|");
        assert string != null;
        help = Integer.parseInt(string[1]);
        followers_count.setText(String.valueOf(help));
        follow_button.setVisible(false);
        unfollow_button.setVisible(true);
    }

    /**
     * the user can unfollow target user with this method
     *
     * @param actionEvent by click on a button
     */
    public void unfollow(ActionEvent actionEvent) {
        String[] string = ClientAPI.unfollow(currentUser, targetUser).split("\\|");

        assert string != null;
        i = Integer.parseInt(string[1]);
        followers_count.setText(String.valueOf(i));
        unfollow_button.setVisible(false);
        follow_button.setVisible(true);
    }

    /**
     * it opens user's home page
     *
     * @param mouseEvent by click on a button
     * @throws IOException because of using page Loader
     */
    public void homePage(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("TimeLine");
    }

    /**
     * it opens user's home page
     *
     * @param mouseEvent by click on a button
     * @throws IOException because of using page Loader
     */
    public void uploadPage(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("UploadNewPost");
    }

    /**
     * it opens user's activityPage page
     *
     * @param mouseEvent by click on a button
     * @throws IOException because of using page Loader
     */
    public void activityPage(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("ActivityPage");
    }

    /**
     * it opens user's personal page
     *
     * @param mouseEvent by click on a button
     * @throws IOException because of using page Loader
     */
    public void profilePage(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Profile_page");
    }

    /**
     * it opens user's search page
     *
     * @param mouseEvent by click on a tab
     * @throws IOException because of using page Loader
     */
    public void SearchPage(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Search_page");
    }
}
