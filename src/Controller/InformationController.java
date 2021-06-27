package Controller;

import Model.Gender;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.Random;

import static Model.Main.currentUser;

/**
 * <h1>InformationController</h1>
 * <p>this controller page for set information for new user </p>
 *
 * @author A.Raei
 * @version 1.0
 * @since 12/2/2021
 */
public class InformationController {
    //field
    public TextField first_name;
    public TextField last_name;
    public TextField Email_field;
    public TextField age_field;
    public TextField gender_field;
    public TextField part1_phone_field;
    public TextField part2_phone_field;
    public TextField check_field;
    // button
    public Button refresh;
    public Button nextPage;
    //security
    public ImageView image1;
    public ImageView image2;
    public ImageView image3;
    public ImageView image4;
    public ImageView image5;
    public ImageView image6;

    //alert
    public Label fields_empty_alert;
    public Label wrongAnswer_alert;

    //others
    public String[] answer = {"991181", "665648", "163356", "742336", "321404", "651606"};
    public int imageCounter = 0;


    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    @FXML
    public void initialize() {
        imageCounter = getRandomNumberUsingNextInt(0, 5);
        visibleImage();
        //auto fill
        if (currentUser.getFirstname() != null)
            first_name.setText(currentUser.getFirstname());
        if (currentUser.getLastname() != null)
            last_name.setText(currentUser.getLastname());
        if (currentUser.getEmailAddress() != null)
            Email_field.setText(currentUser.getEmailAddress());
        if (!(currentUser.getAge() == 0))
            age_field.setText(Integer.toString(currentUser.getAge()));
        if (!(currentUser.getGender() == Gender.unselected))
            gender_field.setText(currentUser.getGender().toString());

    }

    public void visibleImage() {
        invisibleTest();
        switch (imageCounter) {
            case 0 -> image1.setVisible(true);
            case 1 -> image2.setVisible(true);
            case 2 -> image3.setVisible(true);
            case 3 -> image4.setVisible(true);
            case 4 -> image5.setVisible(true);
            case 5 -> image6.setVisible(true);
        }
    }

    public void invisibleTest() {
        image1.setVisible(false);
        image2.setVisible(false);
        image3.setVisible(false);
        image4.setVisible(false);
        image5.setVisible(false);
        image6.setVisible(false);
    }

    /**
     * it change security picture
     * @param actionEvent by click on a button
     */
    public void change_picture(ActionEvent actionEvent) {
        imageCounter = getRandomNumberUsingNextInt(0, 5);
        visibleImage();
    }

    /**
     * it opens Account setting page
     *
     * @param actionEvent by click on a button
     * @throws IOException because of page loader
     */
    public void goToNextPage(ActionEvent actionEvent) throws IOException {
        if (fields_empty_alert.isVisible() || wrongAnswer_alert.isVisible()) {
            fields_empty_alert.setVisible(false);
            wrongAnswer_alert.setVisible(false);
        }

        String name = first_name.getText();
        String age = age_field.getText();
        String security = check_field.getText();

        if (name.isEmpty() || age.isEmpty() || security.isEmpty())
            fields_empty_alert.setVisible(true);
        else if (!security.equalsIgnoreCase(answer[(imageCounter)])) {
            wrongAnswer_alert.setVisible(true);
            imageCounter = getRandomNumberUsingNextInt(0, 5);
            visibleImage();
        } else {
            updateProfileInformation(); // update profile
            new PageLoader().load("AccountSetting");
        }
    }

    /**
     * it update personal information
     */
    public void updateProfileInformation() {
        currentUser.setFirstname(first_name.getText());
        currentUser.setAge(Integer.parseInt(age_field.getText()));

        String anotherDetails = last_name.getText(); // for last name
        if (!anotherDetails.isEmpty())
            currentUser.setLastname(anotherDetails);

        anotherDetails = Email_field.getText(); // for email address
        if (!anotherDetails.isEmpty())
            currentUser.setEmailAddress(anotherDetails);

        anotherDetails = gender_field.getText();// for gender
        if (!anotherDetails.isEmpty())
            currentUser.setGender(anotherDetails);

        anotherDetails = (part1_phone_field.getText() + part2_phone_field.getText()); // for phone number
        if (!anotherDetails.isEmpty())
            currentUser.setPhone(anotherDetails);
    }
}
