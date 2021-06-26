package Controller;

import Model.PageLoader;
import Model.Request;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static Model.Main.*;

public class RequestItemController {

    public ImageView user_image;
    public Label request_text;
    public Button accept_button;
    public Button reject_button;
    public AnchorPane root;

    public RequestItemController(Request request) throws IOException {
        currentRequest = request;
        new PageLoader().load("RequestItem");
    }

    public AnchorPane init() {
        user_image.setImage(new Image(new ByteArrayInputStream(currentRequest.getName().profileImage)));
        request_text.setText(currentRequest.getName().getUsername() + "requested to follow you");
        return root;
    }

    public void accept(ActionEvent actionEvent) throws IOException {
        currentUser.followers.add(currentRequest.getName());
        currentUser.requested.remove(currentRequest.getName());
        new PageLoader().load("ActivityPage");
    }

    public void reject(ActionEvent actionEvent) throws IOException {
        currentUser.requested.remove(currentRequest.getName());
        new PageLoader().load("ActivityPage");
    }
}
