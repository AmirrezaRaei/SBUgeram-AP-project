package Controller;

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

import static Model.Main.currentUser;
import static Model.Main.lastPage;

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

    @FXML
    public void initialize(){
        firstname_field.setText(currentUser.getFirstname());
        username_field.setText(currentUser.getUsername());
        bio_field.setText(currentUser.getBio());
        profile_image.setImage(currentUser.profileImage.getImage());
    }

    public void change_photo(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser=new FileChooser();
        File file=fileChooser.showOpenDialog(new Popup());
        FileInputStream fileInputStream=new FileInputStream(file);
        byte[] bytes=fileInputStream.readAllBytes();
        Image newImage=new Image(new ByteArrayInputStream(bytes));
        profile_image.setImage(newImage);
    }

    public void logout(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("Login");
    }

    public void personal_page(ActionEvent actionEvent) throws IOException {
        currentUser.setFirstname(firstname_field.getText());
        currentUser.setUsername(username_field.getText());
        currentUser.setBio(bio_field.getText());
        currentUser.setProfileImage(profile_image);
        new PageLoader().load("PersonalInformationPage");
    }

    public void cancel_action(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("Profile_page");
    }

    public void done(ActionEvent actionEvent) throws IOException {
        name_alert.setVisible(false);
        username_alert.setVisible(false);
        if (firstname_field.getText().equalsIgnoreCase(""))
            name_alert.setVisible(true);
        else if (username_field.getText().equalsIgnoreCase(""))
            username_alert.setVisible(true);
        else {
            currentUser.setFirstname(firstname_field.getText());
            currentUser.setUsername(username_field.getText());
            currentUser.setBio(bio_field.getText());
            currentUser.setProfileImage(profile_image);
            new PageLoader().load("Profile_page");
        }
    }
}
