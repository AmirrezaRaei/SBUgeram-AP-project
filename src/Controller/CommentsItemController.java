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

import static Model.Main.currentUser;
import static Model.Main.targetUser;

/**
 * <h1>CommentsItemController</h1>
 * <p>this class shows comments in a special view </p>
 *
 * @author A.Raei
 * @version 1.0
 * @since 12/2/2021
 */
public class CommentsItemController {
    public Label user_username;
    public Label comment;
    public ImageView userProfileImage;
    public AnchorPane root;
    public Comment cComment;
    public byte[] image;

    /**
     * its just a constructor
     *
     * @param comment it initialize global comments with this
     * @throws IOException because of using pageLoader
     */
    public CommentsItemController(Comment comment) throws IOException {
        cComment = comment;
        new PageLoader().load("CommentItem", this);
    }

    /**
     * this method initialize comments features
     *
     * @return the pane that shows the comments
     */
    public AnchorPane init() {
        Profile profile = cComment.getWriter();
        user_username.setText(cComment.getWriter().getUsername());
        image = ClientAPI.getProfile(profile);
        if (image != null)
            userProfileImage.setImage(new Image(new ByteArrayInputStream(image)));
        comment.setText(cComment.getComment());
        return root;
    }

    /**
     * user can view user personal profile
     *
     * @param mouseEvent by click on a button
     * @throws IOException because of using pageLoader
     */
    public void profile_page(MouseEvent mouseEvent) throws IOException {
        targetUser = cComment.getWriter();
        if (targetUser.equals(currentUser))
            new PageLoader().load("Profile_page");
        else new PageLoader().load("PublicUsersPage");
    }
}
