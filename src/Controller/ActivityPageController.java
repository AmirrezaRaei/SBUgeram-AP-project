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
/**
 * <h1>ActivityPageController</h1>
 * <p>this controller page for see the all activities</p>
 *
 * @author A.Raei
 * @version 1.0
 * @since 12/2/2021
 */
public class ActivityPageController {

    public ListView<Request> request_list = new ListView<>();
    public ImageView homeButton;
    public ImageView uploadButton;
    public ImageView profileButton;
    public ImageView searchButton;

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


    public void searchPage(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Search_page");
    }
}
