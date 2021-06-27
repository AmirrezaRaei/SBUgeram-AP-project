package Controller;

import Model.*;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.util.Duration;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static Model.Main.currentUser;
import static Model.Main.profiles;
/**
 * <h1>AccountSetting</h1>
 * <p>this controller page for set user information </p>
 * @author A.Raei
 * @since 12/2/2021
 * @version 1.0
 */
public class AccountSetting {
    //button
    public Button changePicture_button;
    public Button Done_button;
    public Button Private_button;
    public Button Public_button;
    public Button back_button;
    // image
    public ImageView profile_image;
    public ImageView private_Unselected;
    public ImageView private_select;
    public ImageView public_unselected;
    public ImageView public_selected;
    // label
    public Label Account_Privacy;
    public Label private_text;
    public Label public_text;
    public Label bio_text;


    public Line bio_Line;
    public TextField bio_field;

    public static String path;
    public static byte[] image;

    @FXML
    public void initialize() {
        TranslateTransition transition;
        //profile image
        transition = new TranslateTransition(Duration.millis(2000), profile_image);
        transition.setByY(262);
        transition.playFromStart();
        // private selected
        transition = new TranslateTransition(Duration.millis(2750), private_select);
        transition.setByY(261);
        transition.playFromStart();
        // public unselected
        transition = new TranslateTransition(Duration.millis(2750), public_unselected);
        transition.setByY(261);
        transition.playFromStart();
        //account privacy label
        transition = new TranslateTransition(Duration.millis(2750), Account_Privacy);
        transition.setByY(261);
        transition.playFromStart();
        // private text
        transition = new TranslateTransition(Duration.millis(2750), private_text);
        transition.setByY(261);
        transition.playFromStart();
        //private button
        transition = new TranslateTransition(Duration.millis(2800), Private_button);
        transition.setByY(261);
        transition.playFromStart();
        // public text
        transition = new TranslateTransition(Duration.millis(2750), public_text);
        transition.setByY(261);
        transition.playFromStart();
        // public button
        transition = new TranslateTransition(Duration.millis(2800), Public_button);
        transition.setByY(261);
        transition.playFromStart();
        // bio label
        transition = new TranslateTransition(Duration.millis(2000), bio_text);
        transition.setByY(499);
        transition.playFromStart();
        // bio field
        transition = new TranslateTransition(Duration.millis(2000), bio_field);
        transition.setByY(499);
        transition.playFromStart();
        // bio line
        transition = new TranslateTransition(Duration.millis(2000), bio_Line);
        transition.setByY(499);
        transition.playFromStart();

        // Done button
        transition = new TranslateTransition(Duration.millis(2300), Done_button);
        transition.setByX(-224);
        transition.playFromStart();
        // Back button
        transition = new TranslateTransition(Duration.millis(2300), back_button);
        transition.setByX(224);
        transition.playFromStart();
    }
    /**
     * user can add Image to its new profile Image by this method
     * @param actionEvent by click on a button
     * @throws IOException because of using FileInputStream
     */
    public void changeProfileImage(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Popup());
        if (file != null) {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bytes = fileInputStream.readAllBytes();
            Image newImage = new Image(new ByteArrayInputStream(bytes));
            image = bytes;
            path = file.getAbsolutePath();
            profile_image.setImage(newImage);
        }
    }

    /**
     * user save the the information in database and it opens user's home page
     * @param actionEvent by clicking this button
     * @throws IOException because of using page Loader
     */
    public void Done(ActionEvent actionEvent) throws IOException {
        Map<String, String> help = new HashMap<>();
        if (image != null )
            ClientAPI.setProfile(currentUser, image, path);
        else ClientAPI.setProfile(currentUser,null,null);
        help.put("firstname", currentUser.getFirstname());
        help.put("age", String.valueOf(currentUser.getAge()));
        help.put("bio", bio_field.getText());
        if (currentUser.getLastname() != null)
            help.put("lastname", currentUser.getLastname());
        if (currentUser.getEmailAddress() != null)
            help.put("emailAddress", currentUser.getEmailAddress());
        if (currentUser.getGender() != Gender.unselected)
            help.put("gender", String.valueOf(currentUser.getGender()));
        if (currentUser.getPhone() != null)
            help.put("phone", currentUser.getPhone());
        ClientAPI.setInformation(currentUser, help);
        Main.update();
        ClientAPI.getAllPosts(currentUser);
        for (Profile profile :
                profiles.values()) {
            ClientAPI.getMyPosts(profile);
        }
        ClientAPI.getAllProfiles(currentUser);
        new PageLoader().load("TimeLine");
    }

    public void privet_account(ActionEvent actionEvent) {
        if (public_selected.isVisible()) {
            public_selected.setVisible(false);
            public_unselected.setVisible(true);
            private_Unselected.setVisible(false);
            private_select.setVisible(true);
        }

    }

    public void public_account(ActionEvent actionEvent) {
        if (private_select.isVisible()) {
            private_select.setVisible(false);
            private_Unselected.setVisible(true);
            public_selected.setVisible(true);
            public_unselected.setVisible(false);
        }
    }

    /**
     * user can go to the last page with this method
     * @param actionEvent by clicking this button
     * @throws IOException because of using page Loader
     */
    public void lastPage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("usernameInformation");
    }
}
