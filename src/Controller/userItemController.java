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

public class userItemController {
    public ImageView profile_image;
    public Label username;
    public Label firstname;
    public AnchorPane root;

    public Profile cprofile;
    byte[] image;

    public userItemController(Profile profile) throws IOException {
        cprofile = profile;
        new PageLoader().load("UserItem",this);
    }

    public void profile(MouseEvent mouseEvent) throws IOException {
        targetUser = cprofile;
        new PageLoader().load("PublicUsersPage");
    }

    public void userPage(MouseEvent mouseEvent) throws IOException {
        targetUser = cprofile;
        new PageLoader().load("PublicUsersPage");
    }

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
