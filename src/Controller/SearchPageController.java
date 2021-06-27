package Controller;

import Model.ClientAPI;
import Model.Item.ProfileItem;
import Model.PageLoader;
import Model.Profile;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import static Model.Main.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <h1>SearchPageController</h1>
 * <p>this controller page for search profile </p>
 *
 * @author A.Raei
 * @version 1.0
 * @since 12/2/2021
 */
public class SearchPageController {

    public TextField search_field;
    public ImageView homeButton;
    public ImageView uploadButton;
    public ImageView activity_button;
    public ImageView profileButton;
    public AnchorPane root;
    public ListView<Profile> find = new ListView<>();

    /**
     * user can search in accounts by this method
     *
     * @param actionEvent by click on a button
     */
    public void search(ActionEvent actionEvent) {
        ClientAPI.getAllProfiles(currentUser);
        List<Profile> profileList = new ArrayList<>();
        for (Profile profile :
                profiles.values()) {
            profileList.add(profile);
        }
        List<Profile> result = profileList.stream().
                filter(temp -> temp.getUsername().contains(search_field.getText()))
                .collect(Collectors.toList());
        find.setItems(FXCollections.observableArrayList(result));
        find.setCellFactory(temp -> new ProfileItem());
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
     * it opens upload page
     *
     * @param mouseEvent by click on a button
     * @throws IOException because of using page Loader
     */
    public void uploadPage(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("UploadNewPost");
    }

    public void activityPage(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("TimeLine");
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
}
