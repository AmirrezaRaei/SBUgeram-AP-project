package Controller;

import Model.PageLoader;
import Model.Profile;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static Model.Main.currentUser;

public class signUpController {
    // field
    public TextField username_register;
    public TextField password_registers;
    public TextField confirm_password;
    // button
    public Button next_button;
    public Hyperlink login;
    public CheckBox human_Test;
    // alert
    public Label confirm_password_alert;
    public Label username_alert;
    public Label password_alert;
    public Label Robot_alert;
    // security
    public ImageView test_done;
    public ImageView not_test;
    public Label username_alert1; // For the username that exists

    public void signUp(ActionEvent actionEvent) throws IOException { // go to next page
        // invisible all alert
        confirm_password_alert.setVisible(false);
        username_alert.setVisible(false);
        password_alert.setVisible(false);
        Robot_alert.setVisible(false);

        String username = username_register.getText();
        String password = password_registers.getText();
        String confirm = confirm_password.getText();

        if (username.length() < 3)
            username_alert.setVisible(true);
        else if (password.length() < 5)
            password_alert.setVisible(true);
        else if (!password.equalsIgnoreCase(confirm))
            confirm_password_alert.setVisible(true);
        else if (not_test.isVisible())
            Robot_alert.setVisible(true);
        else if ((password.equalsIgnoreCase(confirm))) {
            currentUser = new Profile(username, password);
            new PageLoader().load("usernameInformation");
        }
    }

    public void check(ActionEvent actionEvent) {// not robot test
        if (!test_done.isVisible()) {
            not_test.setVisible(false);
            test_done.setVisible(true);
        } else {
            not_test.setVisible(true);
            test_done.setVisible(false);
        }
    }
    public void login(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("Login");
    }
}
