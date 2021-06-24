package Model;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class Post {
    protected Profile profile;
    protected String title;
    protected String description = "";
    protected ImageView imageView;
    private int like = 0;
    private int comment = 0;
    public ArrayList<Profile> liked=new ArrayList<>();
    public ArrayList<Profile> reposted=new ArrayList<>();
    public ArrayList<Comment> comments = new ArrayList<>();


    // getter
    public Profile getProfile() {
        return profile;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getLike() {
        return like;
    }

    public int getComment() {
        return comment;
    }

    public ArrayList<Profile> getLiked() {
        return liked;
    }

    public ArrayList<Profile> getReposted() {
        return reposted;
    }
    // setter
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String description) {
        this.description = description;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public void setLiked(ArrayList<Profile> liked) {
        this.liked = liked;
    }

    public void setReposted(ArrayList<Profile> reposted) {
        this.reposted = reposted;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    //equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(title, post.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
