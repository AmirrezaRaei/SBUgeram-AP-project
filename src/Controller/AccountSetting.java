package Controller;

import Model.PageLoader;
import Model.Profile;
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

import static Model.Main.currentUser;

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

    @FXML
    public void initialize(){
        TranslateTransition transition;
        //profile image
        transition = new TranslateTransition(Duration.millis(2000),profile_image);
        transition.setByY(262);
        transition.playFromStart();
        // private selected
        transition = new TranslateTransition(Duration.millis(2750),private_select);
        transition.setByY(261);
        transition.playFromStart();
        // public unselected
        transition = new TranslateTransition(Duration.millis(2750),public_unselected);
        transition.setByY(261);
        transition.playFromStart();
        //account privacy label
        transition = new TranslateTransition(Duration.millis(2750),Account_Privacy);
        transition.setByY(261);
        transition.playFromStart();
        // private text
        transition = new TranslateTransition(Duration.millis(2750),private_text);
        transition.setByY(261);
        transition.playFromStart();
        //private button
        transition = new TranslateTransition(Duration.millis(2800),Private_button);
        transition.setByY(261);
        transition.playFromStart();
        // public text
        transition = new TranslateTransition(Duration.millis(2750),public_text);
        transition.setByY(261);
        transition.playFromStart();
        // public button
        transition = new TranslateTransition(Duration.millis(2800),Public_button);
        transition.setByY(261);
        transition.playFromStart();
        // bio label
        transition = new TranslateTransition(Duration.millis(2000),bio_text);
        transition.setByY(499);
        transition.playFromStart();
        // bio field
        transition = new TranslateTransition(Duration.millis(2000),bio_field);
        transition.setByY(499);
        transition.playFromStart();
        // bio line
        transition = new TranslateTransition(Duration.millis(2000),bio_Line);
        transition.setByY(499);
        transition.playFromStart();

        // Done button
        transition = new TranslateTransition(Duration.millis(2300),Done_button);
        transition.setByX(-224);
        transition.playFromStart();
        // Back button
        transition = new TranslateTransition(Duration.millis(2300),back_button);
        transition.setByX(224);
        transition.playFromStart();
    }

    public void changeProfileImage(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser=new FileChooser();
        File file=fileChooser.showOpenDialog(new Popup());
        FileInputStream fileInputStream=new FileInputStream(file);
        byte[] bytes=fileInputStream.readAllBytes();
        Image newImage=new Image(new ByteArrayInputStream(bytes));
        profile_image.setImage(newImage);
    }

    public void Done(ActionEvent actionEvent) throws IOException {
        currentUser.setBio(bio_field.getText());
        currentUser.setProfileImage(profile_image);
        if (public_selected.isVisible())
            currentUser.setPrivate_page(false);
        new PageLoader().load("Profile_page");
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
        if (private_select.isVisible()){
            private_select.setVisible(false);
            private_Unselected.setVisible(true);
            public_selected.setVisible(true);
            public_unselected.setVisible(false);
        }
    }

    public void lastPage(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("usernameInformation");
    }
}
