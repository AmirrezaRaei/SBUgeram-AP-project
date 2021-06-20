package Controller;

import Model.Main;
import Model.PageLoader;
import Model.Post;
import Model.Profile;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static Model.Main.*;

public class PostItemController {
    // label
    public Label username;
    public Label title;
    public Label description;
    public Label likes_count;
    public Label comments;
    public Label repost_count;
    public Label show_comments_count;
    // image
    public ImageView empty_heart;
    public ImageView fill_heart;
    public ImageView comment;
    public ImageView repost;
    public ImageView post_image;
    // pane
    public AnchorPane post_pane;
    public ImageView username_image;
    public ImageView unRepost;


    public PostItemController(Post post) throws IOException {
        currentPost = post;
        new PageLoader().load("Post", this);
    }

    public AnchorPane init() {
        username.setText(currentPost.getProfile().getUsername()); // to show username
        if (currentPost.getTitle().equals("")) //  to show title or username
            title.setText(currentPost.getProfile().getUsername() + " :");
        else
            title.setText(currentPost.getTitle() + " :");

        if (currentPost.getRepost() != 0) // show number of repost
            repost_count.setText(String.valueOf(currentPost.getRepost()));

        likes_count.setText(currentPost.getLike() + "Likes");// show like count

        if (currentPost.getComment() != 0) // show number of comments
            show_comments_count.setText("View all " + currentPost.getComment() + " comments");
        description.setText(currentPost.getDescription());

        post_image.setImage(currentPost.getImageView().getImage());

        username_image.setImage(currentPost.getProfile().profileImage.getImage());

        if (currentPost.liked.contains(currentUser)) {
            empty_heart.setVisible(false);
            fill_heart.setVisible(true);
        } else {
            fill_heart.setVisible(false);
            empty_heart.setVisible(true);
        }
        if (currentPost.reposted.contains(currentUser)){
            repost.setVisible(false);
            unRepost.setVisible(true);
        } else {
            unRepost.setVisible(false);
            repost.setVisible(true);
        }
        return post_pane;
    }

    public void profile_page(MouseEvent mouseEvent) throws IOException {
        visitCurrentUser = new Profile(username.getText());
        if (visitCurrentUser.equals(currentUser))
            new PageLoader().load("Profile_page");
        if (visitCurrentUser.getPrivate_page() && visitCurrentUser.requested.contains(currentUser))
            new PageLoader().load("PrivateUsersPage");
        else new PageLoader().load("PublicUsersPage");
    }

    public void repost(MouseEvent mouseEvent) {
        currentUser.myPosts.add(currentPost);
        currentPost.reposted.add(currentUser);
        currentPost.setRepost((currentPost.getRepost() + 1));
        repost_count.setText(String.valueOf(currentPost.getRepost()));
        repost.setVisible(false);
        unRepost.setVisible(true);
    }

    public void like_post(MouseEvent mouseEvent) {
        empty_heart.setVisible(false);
        fill_heart.setVisible(true);
        currentPost.setLike((currentPost.getLike() + 1));
        likes_count.setText(currentPost.getLike() + "Likes");
        currentPost.liked.add(currentUser);
    }

    public void unlike_post(MouseEvent mouseEvent) {
        empty_heart.setVisible(true);
        fill_heart.setVisible(false);
        currentPost.setLike((currentPost.getLike() - 1));
        likes_count.setText(currentPost.getLike() + "Likes");
        currentPost.liked.remove(currentUser);
    }

    public void seeTheComments(MouseEvent mouseEvent) throws IOException {
        new PageLoader().load("CommentsPage");
    }

    public void unRepost(MouseEvent mouseEvent) {
        currentUser.getPosts().remove(currentPost);
        currentPost.setRepost((currentPost.getRepost() - 1));
        repost_count.setText(String.valueOf(currentPost.getRepost()));
        unRepost.setVisible(false);
        repost.setVisible(true);
    }
}
