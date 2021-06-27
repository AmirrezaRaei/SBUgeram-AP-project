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

/**
 * <h1>TimeLineController</h1>
 * <p>this controller page is a connector between TimeLinePage FXML(ths stage) and Client Api </p>
 *
 * @author A.Raei
 * @version 1.0
 * @since 12/2/2021
 */
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
        for (Post post : post_List) {
            postSet.add(post);

//            List<String> pu = post.share.stream().map(temp -> temp.getUsername())
//                    .collect(Collectors.toList());
        }
        post_List = postSet.stream().sorted().collect(Collectors.toList());
        postList.setItems(FXCollections.observableArrayList(post_List));
        postList.setCellFactory(PostList -> new PostItem());
    }

    /**
     * it opens user's personal page
     *
     * @param mouseEvent by click on a button
     * @throws IOException because of using page Loader
     */
    public void ProfilePage(MouseEvent mouseEvent) throws IOException {
//        lastPage = "TimeLine";
        new PageLoader().load("Profile_page");
    }

    /**
     * it opens user's activityPage page
     *
     * @param mouseEvent by click on a button
     * @throws IOException because of using page Loader
     */
    public void activityPage(MouseEvent mouseEvent) throws IOException {
//        lastPage = "TimeLine";
        new PageLoader().load("ActivityPage");
    }

    /**
     * it opens upload page
     *
     * @param mouseEvent by click on a button
     * @throws IOException because of using page Loader
     */
    public void uploadNewPost(MouseEvent mouseEvent) throws IOException {
//        lastPage = "TimeLine";
        new PageLoader().load("UploadNewPost");
    }

    /**
     * this method refresh user's timeline and shows it the new information
     *
     * @param mouseEvent by click on a button
     * @throws IOException because of using pageLoader
     */
    public void refresh(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("TimeLine");
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
