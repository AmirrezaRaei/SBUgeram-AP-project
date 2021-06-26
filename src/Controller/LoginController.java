package Controller;

import Model.ClientAPI;
import Model.PageLoader;
import Model.Main;
import Model.Profile;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;

import static Model.Main.currentUser;


public class LoginController {
    @FXML
    // filed
    public TextField username_Field;
    public PasswordField password_Field;
    public TextField password_visible;
    // button
    public Button login_Button;
    public CheckBox password_icon;
    public Hyperlink sign_up_button;
    // alert
    public Label password_alert;
    // security 
    public ImageView hide_password;
    public ImageView showpassword;


    @FXML
    public void initialize() {
        TranslateTransition transition;
        // username filed
        transition = new TranslateTransition(Duration.millis(3000), username_Field);
        transition.setByX(-305);
        transition.playFromStart();
        // password filed
        transition = new TranslateTransition(Duration.millis(3000), password_Field);
        transition.setByX(297);
        transition.playFromStart();
        //login button
        transition = new TranslateTransition(Duration.millis(4000), login_Button);
        transition.setByY(-173);
        transition.playFromStart();

        transition = new TranslateTransition(Duration.millis(3500), showpassword);
        transition.setByX(336);
        transition.playFromStart();
        transition = new TranslateTransition(Duration.millis(3500), password_icon);
        transition.setByX(336);
        transition.playFromStart();
        showpassword.setVisible(true);
    }

    public void Login(ActionEvent actionEvent) throws IOException {
        // invisible all alert
        if (password_alert.isVisible()) {
            password_alert.setVisible(false);
        }
        Profile profile;
        String username = username_Field.getText();
        String password = password_Field.getText();
        if (password_visible.isVisible())
            password = password_visible.getText();
        profile = ClientAPI.login(username, password);
        if (profile == null)
            password_alert.setVisible(true);
        else {
            currentUser = profile;
            Main.update();
            ClientAPI.getAllPosts(currentUser);
            new PageLoader().load("TimeLine");
        }
    }


    public void show_password(ActionEvent actionEvent) {
        if (!password_visible.isVisible()) {
            password_visible.setVisible(true);
            password_visible.setText(password_Field.getText());
            showpassword.setVisible(false);
            hide_password.setVisible(true);
        } else {
            password_visible.setVisible(false);
            password_Field.setText(password_visible.getText());
            showpassword.setVisible(true);
            hide_password.setVisible(false);
        }
    }

    public void sign_up(ActionEvent actionEvent) throws IOException { // go to signup page
        new PageLoader().load("signUp");
    }
}
