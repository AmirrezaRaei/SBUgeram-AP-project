package Controller;

import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

import static Model.Main.currentUser;

public class PersonalInformationPageController {
    public Button arrow_button;
    // field
    public TextField email_field;
    public TextField lastname_field;
    public TextField phone_field;
    public TextField gender_field;
    @FXML
    public void initialize(){
        email_field.setText(currentUser.getEmailAddress());
        lastname_field.setText(currentUser.getLastname());
        phone_field.setText(currentUser.getPhone());
        gender_field.setText(String.valueOf(currentUser.getGender()));
    }
    public void last_page(ActionEvent actionEvent) throws IOException {
        currentUser.setEmailAddress(email_field.getText());
        currentUser.setLastname(lastname_field.getText());
        currentUser.setPhone(phone_field.getText());
        currentUser.setGender(gender_field.getText());
        new PageLoader().load("EditProfilePage");
    }
}
