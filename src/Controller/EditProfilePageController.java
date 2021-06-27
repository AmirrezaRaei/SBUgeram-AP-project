package Controller;

import Model.ClientAPI;
import Model.Gender;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Popup;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static Model.Main.currentUser;

/**
 * <h1>EditProfilePageController</h1>
 * <p>this controller page for set new information , change profile image , logout</p>
 *
 * @author A.Raei
 * @version 1.0
 * @since 12/2/2021
 */
public class EditProfilePageController {
    //button
    public Button done_button;
    public Button cancel_button;
    public Button logout_button;
    public Button personalPage_button;
    public Button changePhotoButton;
    //text field
    public TextField firstname_field;
    public TextField username_field;
    public TextField bio_field;
    //alert
    public Label name_alert;
    public Label username_alert;

    public ImageView profile_image;
    String path;
    byte[] image;

    @FXML
    public void initialize() {
        Map<String, String> information = ClientAPI.getInformation(currentUser);
        assert information != null;
        firstname_field.setText(information.get("firstname"));
        username_field.setText(currentUser.getUsername());
        bio_field.setText(information.get("bio"));
        byte[] nImage = ClientAPI.getProfile(currentUser);
        if (nImage != null)
            profile_image.setImage(new Image(new ByteArrayInputStream(nImage)));
    }

    public void change_photo(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Popup());
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes = fileInputStream.readAllBytes();
        Image newImage = new Image(new ByteArrayInputStream(bytes));
        path = file.getAbsolutePath();
        image = bytes;
        profile_image.setImage(newImage);
    }

    /**
     * user can log out of its account
     *
     * @param actionEvent by click on a button
     * @throws IOException because of using pageLoader
     */
    public void logout(ActionEvent actionEvent) throws IOException {
        ClientAPI.logout(currentUser);
        new PageLoader().load("Login");
    }

    /**
     * it open Personal Information Page
     *
     * @param actionEvent by click on a button
     * @throws IOException because of using pageLoader
     */
    public void personal_page(ActionEvent actionEvent) throws IOException {
        currentUser.setFirstname(firstname_field.getText());
        currentUser.setUsername(username_field.getText());
        currentUser.setBio(bio_field.getText());
        if (image != null)
            ClientAPI.changeProfile(currentUser, image, path);
        new PageLoader().load("PersonalInformationPage");
    }
    /**
     * back to the last page & discard the changes
     *
     * @param actionEvent by click on a button
     * @throws IOException because of using pageLoader
     */
    public void cancel_action(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("Profile_page");
    }

    /**
     * back to the last page & save the changes
     *
     * @param actionEvent by click on a button
     * @throws IOException because of using pageLoader
     */
    public void done(ActionEvent actionEvent) throws IOException {
        name_alert.setVisible(false);
        username_alert.setVisible(false);
        if (firstname_field.getText().equalsIgnoreCase(""))
            name_alert.setVisible(true);
        else if (username_field.getText().equalsIgnoreCase(""))
            username_alert.setVisible(true);
        else {
            if (image != null)
                ClientAPI.changeProfile(currentUser, image, path);
            Map<String, String> information = new HashMap<>();
//            information.put("username",username_field.getText());
            information.put("firstname", firstname_field.getText());
            information.put("bio", bio_field.getText());
            information.put("age", String.valueOf(currentUser.getAge()));
            if (currentUser.getLastname() != null)
                information.put("lastname", currentUser.getLastname());
            if (currentUser.getEmailAddress() != null)
                information.put("emailAddress", currentUser.getEmailAddress());
            if (currentUser.getGender() != Gender.unselected)
                information.put("gender", String.valueOf(currentUser.getGender()));
            if (currentUser.getPhone() != null)
                information.put("phone", currentUser.getPhone());
            ClientAPI.setInformation(currentUser, information);
            new PageLoader().load("Profile_page");
        }
    }
}
