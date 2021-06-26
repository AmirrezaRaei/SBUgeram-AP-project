package Controller;

import Model.*;
import Model.Item.CommentsItem;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Model.Main.currentUser;
import static Model.Main.targetPost;

public class CommentsPageController {

    public Button arrow_button;
    public ImageView currentUserProfileImage;
    public TextField addComment_field;
    public ListView<Comment> comments_list = new ListView<>();
    public ImageView postOwnerProfileImage;
    public Label desc;
    public Label title;
    public ImageView send_logo;

    public Comment currentComment = new Comment();
    List<Comment> commentList = new ArrayList<>();

    byte [] image;
    @FXML
    public void initialize() {
        Profile profile = targetPost.getProfile();
        commentList = ClientAPI.getComments(targetPost);
        image = ClientAPI.getProfile(profile);
        if (image != null)
            postOwnerProfileImage.setImage(new Image(new ByteArrayInputStream(image)));
        image = ClientAPI.getProfile(currentUser);
        if (image != null)
            currentUserProfileImage.setImage(new Image(new ByteArrayInputStream(image)));

        title.setText(targetPost.getTitle());
        desc.setText(targetPost.getDescription());
        comments_list.setItems(FXCollections.observableArrayList(commentList));
        comments_list.setCellFactory(PostList -> new CommentsItem());
    }

    public void last_page(ActionEvent actionEvent) throws IOException {
        new PageLoader().load(Main.lastPage);
    }

    public void add_comment(ActionEvent actionEvent) {
        Comment currentComment = new Comment();
        currentComment.setComment(addComment_field.getText());
        currentComment.setWriter(currentUser);
        commentList = ClientAPI.setComment(PostItemController.temp,currentUser,currentComment);
        commentList = ClientAPI.getComments(PostItemController.temp);
        assert commentList != null;
        commentList.add(currentComment);
        comments_list.setItems(FXCollections.observableArrayList(commentList));
        comments_list.setCellFactory(commentList -> new CommentsItem());
        addComment_field.setText("");
    }

    public void send_comment(MouseEvent mouseEvent) {
        currentComment = new Comment();
        currentComment.setWriter(currentUser);
        currentComment.setComment(addComment_field.getText());
        commentList = ClientAPI.setComment(targetPost,currentUser,currentComment);
        commentList = ClientAPI.getComments(targetPost);
        assert commentList != null;
        commentList.add(currentComment);
        comments_list.setItems(FXCollections.observableArrayList(commentList));
        comments_list.setCellFactory(commentList -> new CommentsItem());
        addComment_field.setText("");
    }
}
