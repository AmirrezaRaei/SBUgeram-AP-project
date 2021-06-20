package Controller;

import Model.Comment;
import Model.Item.CommentsItem;
import Model.Item.PostItem;
import Model.Main;
import Model.PageLoader;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

import static Model.Main.*;

public class CommentsPageController {

    public Button arrow_button;
    public ImageView currentUserProfileImage;
    public TextField addComment_field;
    public ListView<Comment> comments_list = new ListView<>();
    public ImageView postOwnerProfileImage;
    public Label desc;
    public Label title;
    public ImageView send_logo;

    @FXML
    public void initialize() {
        currentUserProfileImage.setImage(currentUser.profileImage.getImage());
        postOwnerProfileImage.setImage(currentPost.getProfile().profileImage.getImage());
        if (!currentPost.getTitle().equalsIgnoreCase(""))
            title.setText(currentPost.getTitle());
        else title.setText(String.valueOf(currentPost.getProfile().getUsername()));
        desc.setText(currentPost.getDescription());
        comments_list.setItems(FXCollections.observableArrayList(currentPost.comments));
        comments_list.setCellFactory(PostList -> new CommentsItem());
    }

    public void last_page(ActionEvent actionEvent) throws IOException {
        new PageLoader().load(Main.lastPage);
    }

    public void add_comment(ActionEvent actionEvent) {
        currentComment = new Comment(currentUser,addComment_field.getText());
        currentPost.comments.add(currentComment);
        comments_list.setItems(FXCollections.observableArrayList(currentPost.comments));
        comments_list.setCellFactory(PostList -> new CommentsItem());
        currentComment = new Comment();
        addComment_field.setText("");
    }

    public void send_comment(MouseEvent mouseEvent) {
        currentComment = new Comment(currentUser,addComment_field.getText());
        currentPost.comments.add(currentComment);
        comments_list.setItems(FXCollections.observableArrayList(currentPost.comments));
        comments_list.setCellFactory(PostList -> new CommentsItem());
        currentComment = new Comment();
        addComment_field.setText("");
    }
}
