package Controller;

import Model.ClientAPI;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Map;

import static Model.Main.currentUser;

/**
 * <h1>PersonalInformationPageController</h1>
 * <p>this controller page for change  personal information </p>
 *
 * @author A.Raei
 * @version 1.0
 * @since 12/2/2021
 */
public class PersonalInformationPageController {
    public Button arrow_button;
    // field
    public TextField email_field;
    public TextField lastname_field;
    public TextField phone_field;
    public TextField gender_field;

    @FXML
    public void initialize() {
        Map<String, String> information = ClientAPI.getInformation(currentUser);
        assert information != null;
        if (information.get("emailAddress") != null)
            email_field.setText(information.get("emailAddress"));
        if (information.get("lastname") != null)
            lastname_field.setText(information.get("lastname"));
        if (information.get("phone") != null)
            phone_field.setText(information.get("phone"));
        if (!information.get("gender").equals("unselected"))
            gender_field.setText(information.get("gender"));
    }

    /**
     * back to the last page & save the changes
     *
     * @param actionEvent by click on a button
     * @throws IOException because of using pageLoader
     */
    public void last_page(ActionEvent actionEvent) throws IOException {
        currentUser.setEmailAddress(email_field.getText());
        currentUser.setLastname(lastname_field.getText());
        currentUser.setPhone(phone_field.getText());
        currentUser.setGender(gender_field.getText());
        new PageLoader().load("EditProfilePage");
    }
}
