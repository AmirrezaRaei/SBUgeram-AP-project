package Controller;

import Model.ClientAPI;
import Model.Comment;
import Model.PageLoader;
import Model.Profile;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static Model.Main.targetUser;

public class CommentsItemController {
    public Label user_username;
    public Label comment;
    public ImageView userProfileImage;
    public AnchorPane root;
    public Comment cComment;
    public byte[] image;

    public CommentsItemController(Comment comment) throws IOException{
        cComment = comment;
        new PageLoader().load("CommentItem",this);
    }

    public AnchorPane init() {
        Profile profile = cComment.getWriter();
        user_username.setText(cComment.getWriter().getUsername());
        image = ClientAPI.getProfile(profile);
        if (image != null)
        userProfileImage.setImage(new Image(new ByteArrayInputStream(image)));
        comment.setText(cComment.getComment());
        return root;
    }

    public void profile_page(MouseEvent mouseEvent) throws IOException {
        targetUser = cComment.getWriter();
        new PageLoader().load("PublicUsersPage");
    }
}
