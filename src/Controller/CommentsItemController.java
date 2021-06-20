package Controller;

import Model.Main;
import Model.Comment;
import Model.PageLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;
import static Model.Main.*;

public class CommentsItemController {
    public Label user_username;
    public Label comment;
    public ImageView userProfileImage;
    public AnchorPane root;
    
    public CommentsItemController(Comment comment) throws IOException{
        currentComment = comment;
        new PageLoader().load("CommentItem",this);
    }

    public AnchorPane init() {
        user_username.setText(String.valueOf(currentComment.getWriter().getUsername()));
        userProfileImage.setImage(currentComment.getWriter().profileImage.getImage());
        comment.setText(currentComment.getComment());
        return root;
    }
}
