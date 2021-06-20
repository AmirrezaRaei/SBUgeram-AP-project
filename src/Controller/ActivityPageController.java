package Controller;

import Model.Item.RequestItem;
import Model.PageLoader;
import Model.Request;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static Model.Main.currentUser;

public class ActivityPageController {

    public ListView<Request> request_list = new ListView<>();
    public ImageView homeButton;
    public ImageView uploadButton;
    public ImageView profileButton;

    @FXML
    public void initialize(){
        request_list.setItems(FXCollections.observableArrayList(currentUser.requested));
        request_list.setCellFactory(RequestList -> new RequestItem());
    }
    public void homePage(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("TimeLine");
    }

    public void uploadPage(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("UploadNewPost");
    }

    public void profilePage(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Profile_page");
    }
}
