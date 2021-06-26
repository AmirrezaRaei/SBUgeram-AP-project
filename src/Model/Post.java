package Model;

import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class Post implements Serializable,Comparable {
    protected Profile profile = new Profile();
    protected String title;
    protected String description = "";
    protected byte[] image;
    private int like = 0;
    private int comment = 0;
    public List<Profile> liked=new CopyOnWriteArrayList<>();
    public List<Profile> reposted=new CopyOnWriteArrayList<>();
    public List<Comment> comments = new CopyOnWriteArrayList<>();
    public List<Profile> share = new CopyOnWriteArrayList<>();

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

    public byte[] getImage() {
        return image;
    }

    public int getLike() {
        return like;
    }

    public int getComment() {
        return comment;
    }

    public List<Profile> getLiked() {
        return liked;
    }

    public List<Profile> getReposted() {
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

    public void setImage(byte[] imageView) {
        this.image = imageView;
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

    public List<Comment> getComments() {
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

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
