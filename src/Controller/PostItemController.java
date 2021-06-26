package Controller;

import Model.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

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

    public Post cPost;// get post details
    public static Post temp; // use for comment
    int a = 0, b = 0, c = 0;


    public PostItemController(Post post) throws IOException {
        this.cPost = post;
        new PageLoader().load("Post", this);
    }

    public AnchorPane init() {
        byte[] image;
        username.setText(cPost.getProfile().getUsername());

        image = ClientAPI.getProfile(cPost.getProfile());

        if (image != null)
            username_image.setImage(new Image(new ByteArrayInputStream(image)));
        if (cPost.getTitle().equals("")) //  to show title or username
            title.setText(cPost.getProfile().getUsername() + " :");
        else
            title.setText(cPost.getTitle() + " :");

        description.setText(cPost.getDescription());

        post_image.setImage(new Image(new ByteArrayInputStream(cPost.getImage())));
        if (cPost.getProfile().profileImage != null)
            username_image.setImage(new Image(new ByteArrayInputStream(cPost.getProfile().profileImage)));

        String [] s = ClientAPI.getPostDetails(cPost).split("\\|");

        assert s != null;
        a = Integer.parseInt(s[0]);
        if (a > 0)
            likes_count.setText(a + " Likes");// show like count
        b = Integer.parseInt(s[1]);
        if (b > 0)
            repost_count.setText(String.valueOf(b));
        c = Integer.parseInt(s[2]);
        if (a != 0) {
            show_comments_count.setText("View all " + a + " comments");
        } else show_comments_count.setText("View all comments");

        List<String> list = ClientAPI.getLikes(cPost);
        assert list != null;
        if (list.contains(currentUser.getUsername())) {
            empty_heart.setVisible(false);
            fill_heart.setVisible(true);
        } else {
            fill_heart.setVisible(false);
            empty_heart.setVisible(true);
        }
        list = ClientAPI.getReposts(cPost);
        assert list != null;
        if (list.contains(currentUser.getUsername())) {
            repost.setVisible(false);
            unRepost.setVisible(true);
        } else {
            unRepost.setVisible(false);
            repost.setVisible(true);
        }
        return post_pane;
    }

    public void profile_page(MouseEvent mouseEvent) throws IOException {
        targetUser = cPost.getProfile();
        if (targetUser.equals(currentUser))
            new PageLoader().load("Profile_page");
        else new PageLoader().load("PublicUsersPage");

    }

    public void repost(MouseEvent mouseEvent) {
        cPost.share.add(currentUser);
        int a = 0;
        a = ClientAPI.repost(currentUser, cPost);
        repost_count.setText(String.valueOf(a));
        currentUser.getPosts().add(cPost);
        Main.update();
        ClientAPI.getAllPosts(currentUser);
        for (Profile profile :
                profiles.values()) {
            ClientAPI.getMyPosts(profile);
        }
    }

    public void like_post(MouseEvent mouseEvent) {
        empty_heart.setVisible(false);
        fill_heart.setVisible(true);
        int like = ClientAPI.like(cPost, currentUser);
        if (like > 0 )
            likes_count.setText(like + " Likes");
    }

    public void unlike_post(MouseEvent mouseEvent) {
        empty_heart.setVisible(true);
        fill_heart.setVisible(false);
        int help = ClientAPI.unlike(cPost, currentUser);
        if (help > 0)
            likes_count.setText(help + " Likes");
    }

    public void seeTheComments(MouseEvent mouseEvent) throws IOException {
        targetPost = cPost;
        new PageLoader().load("CommentsPage");
    }

    public void unRepost(MouseEvent mouseEvent) {
        currentUser.getPosts().remove(targetPost);
        unRepost.setVisible(false);
        repost.setVisible(true);
        cPost.share.remove(currentUser);
        int i = 0;
        i = ClientAPI.unRepost(currentUser, cPost);
        repost_count.setText(String.valueOf(i));
        currentUser.getPosts().remove(cPost);
        Main.update();
        ClientAPI.getAllPosts(currentUser);
        for (Profile profile :
                profiles.values()) {
            ClientAPI.getMyPosts(profile);
        }
    }
}
