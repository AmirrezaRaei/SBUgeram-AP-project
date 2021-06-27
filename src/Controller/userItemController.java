package Controller;

import Model.ClientAPI;
import Model.PageLoader;
import Model.Profile;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

import static Model.Main.targetUser;
/**
 * <h1>userItemController</h1>
 * <p>this class for show the profile page with special view in search page</p>
 * @author A.Raei
 * @since 12/2/2021
 * @version 1.0
 */
public class userItemController {
    public ImageView profile_image;
    public Label username;
    public Label firstname;
    public AnchorPane root;

    public Profile cprofile;
    byte[] image;
    /**
     * its just a constructor
     *
     * @param profile it initialize global user with this
     * @throws IOException because of using pageLoader
     */
    public userItemController(Profile profile) throws IOException {
        cprofile = profile;
        new PageLoader().load("UserItem",this);
    }

    /**
     * user can view user personal profile
     *
     * @param mouseEvent by click on a button
     * @throws IOException because of using pageLoader
     */
    public void profile(MouseEvent mouseEvent) throws IOException {
        targetUser = cprofile;
        new PageLoader().load("PublicUsersPage");
    }

    /**
     * user can view user personal profile
     *
     * @param mouseEvent by click on a button
     * @throws IOException because of using pageLoader
     */
    public void userPage(MouseEvent mouseEvent) throws IOException {
        targetUser = cprofile;
        new PageLoader().load("PublicUsersPage");
    }

    /**
     * this method initialize profile features
     *
     * @return the pane that shows the profile
     * @throws IOException because of using pageLoader
     */
    public AnchorPane init() {
        username.setText(cprofile.getUsername());
        image = ClientAPI.getProfile(cprofile);
        if (image != null){
            profile_image.setImage(new Image(new ByteArrayInputStream(image)));
        }
        Map<String,String> information = ClientAPI.getInformation(cprofile);
        assert information != null;
        firstname.setText(information.get("firstname"));
        return root;
    }
}
