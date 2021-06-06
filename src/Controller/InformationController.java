package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.Random;

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
    }

    public void visibleImage() {
        invisibleTest();
        switch (imageCounter) {
            case 0:
                image1.setVisible(true);
                break;
            case 1:
                image2.setVisible(true);
                break;
            case 2:
                image3.setVisible(true);
                break;
            case 3:
                image4.setVisible(true);
                break;
            case 4:
                image5.setVisible(true);
                break;
            case 5:
                image6.setVisible(true);
                break;
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

    public void change_picture(ActionEvent actionEvent) {
        imageCounter = getRandomNumberUsingNextInt(0,5);
        visibleImage();
    }

    public void goToNextPage(ActionEvent actionEvent) {
        if (fields_empty_alert.isVisible() || wrongAnswer_alert.isVisible()){
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
            imageCounter = getRandomNumberUsingNextInt(0,5);
            visibleImage();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Hi Ali!\n welcome back");
            alert.showAndWait();
        }
    }
}
