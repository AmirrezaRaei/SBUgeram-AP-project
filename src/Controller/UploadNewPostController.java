package Controller;

import Model.ClientAPI;
import Model.PageLoader;
import Model.Post;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Popup;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static Model.Main.*;

public class UploadNewPostController {
    public ImageView upload_image;
    public Label title;
    // Text field
    public TextField title_field;
    public TextField desc_field;
    // button
    public Button upload_button;
    public Button share_button;
    // option button
    public ImageView homeButton;
    public ImageView activityButton;
    public ImageView profileButton;
    public ImageView searchButton;

    String path;
    byte [] image;
    public void uploadNewImage(ActionEvent actionEvent)throws IOException {
        FileChooser fileChooser=new FileChooser();
        File file=fileChooser.showOpenDialog(new Popup());
        FileInputStream fileInputStream=new FileInputStream(file);
        byte[] bytes=fileInputStream.readAllBytes();
        path = file.getAbsolutePath();
        Image newImage=new Image(new ByteArrayInputStream(bytes));
        image = bytes;
        upload_image.setImage(newImage);
    }

    public void publish(ActionEvent actionEvent) throws IOException {
        Post currentPost = new Post();
        currentPost.setProfile(currentUser);
        currentPost.share.add(currentUser);
        if (!title_field.getText().equalsIgnoreCase(""))
            currentPost.setTitle(title_field.getText());
        else currentPost.setTitle(currentUser.getUsername());
        currentPost.setText(desc_field.getText());
        if (image != null)
            currentPost.setImage(image);
        posts.add(currentPost);
        currentUser.myPosts.add(currentPost);
        if (image != null)
            ClientAPI.addPost(currentPost,image,path);
        else ClientAPI.addPost(currentPost);
        new PageLoader().load("TimeLine");
    }

    public void profilePage(MouseEvent mouseEvent) throws IOException {
//        lastPage = "UploadNewPost";
        new PageLoader().load("Profile_page");
    }

    public void activityPage(MouseEvent mouseEvent) throws IOException {
//        lastPage = "UploadNewPost";
        new PageLoader().load("ActivityPage");
    }

    public void homePage(MouseEvent mouseEvent) throws IOException {
//        lastPage = "UploadNewPost";
        new PageLoader().load("TimeLine");
    }

    public void searchPage(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("Search_page");
    }
}
