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
/**
 * <h1>CommentsPageController</h1>
 * <p>this controller page for see the all comments </p>
 *
 * @author A.Raei
 * @version 1.0
 * @since 12/2/2021
 */
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
    /**
     * this method load all comments that post has
     */
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

    /**
     * back to the last page & save the changes
     *
     * @param actionEvent by click on a button
     * @throws IOException because of using pageLoader
     */
    public void last_page(ActionEvent actionEvent) throws IOException {
        new PageLoader().load(Main.lastPage);
    }
    /**
     * this method initialize and add new comment
     * @param actionEvent by click on a button
     */
    public void add_comment(ActionEvent actionEvent) {
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
    /**
     * this method initialize and add new comment
     * @param mouseEvent by click on a button
     */
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
