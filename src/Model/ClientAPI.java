package Model;

import Common.Action;

import javax.swing.text.html.ImageView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Model.Main.*;

public class ClientAPI {

    public static Profile login(String username, String pass) {
        Map<String, Object> massage = new HashMap<>(), map;
        massage.put("action", Action.login);
        massage.put("username", username);
        massage.put("password", pass);
        map = ClientNetwork.serve(massage);
        if (map.get("answer") == null)
            return null;
        return (Profile) map.get("answer");
    }

    public static void logout (Profile profile){
        Map<String, Object> massage = new HashMap<>();
        massage.put("action",Action.logout);
        massage.put("profile",profile);
        ClientNetwork.serve(massage);
    }

    public static Profile signup(Profile profile) {
        Map<String, Object> massage = new HashMap<>(), map;
        massage.put("action", Action.signup);
        massage.put("profile", profile);
        map = ClientNetwork.serve(massage);
        if (map.get("answer") == null)
            return null;
        return (Profile) map.get("answer");
    }

    public static void addPost(Post post, ImageView image) {
        Map<String, Object> massage = new HashMap<>(), map;

        massage.put("action", Action.addPost);
        massage.put("post", post);
        massage.put("image", image);
        map = ClientNetwork.serve(massage);
    }

    public static Map<String, Object> getPosts() {
        Map<String, Object> massage = new HashMap<>(), map;
        massage.put("action", Action.getPosts);
        massage.put("posts", posts);
        map = ClientNetwork.serve(massage);
        return map;
    }

    public static Map<String, Object> getMyPosts(Profile profile) {
        Map<String, Object> massage = new HashMap<>(), map;
        massage.put("action", Action.getMyPost);
        massage.put("profile", profile);
        map = ClientNetwork.serve(massage);
        return map;
    }

    public static Map<String, Object> getProfiles() {
        Map<String, Object> massage = new HashMap<>(), map;
        massage.put("action", Action.getProfiles);
        massage.put("profile", currentUser);
        massage.put("profiles", users);
        map = ClientNetwork.serve(massage);
        return map;
    }

    public static String follow(Profile profile) {
        Map<String, Object> massage = new HashMap<>(), map;
        massage.put("action", Action.follow);
        massage.put("profile", currentUser);
        map = ClientNetwork.serve(massage);

        if (map.get("answer") != null)
            return (String) map.get("answer");
        return null;
    }

    public static String unfollow(Profile profile) {
        Map<String, Object> massage = new HashMap<>(), map;
        massage.put("action", Action.unfollow);
        massage.put("profile", currentUser);
        massage.put("unfollowed", profile);
        map = ClientNetwork.serve(massage);

        if (map.get("answer") != null)
            return (String) map.get("answer");
        return null;
    }

    public static ImageView getProfile(Profile profile) {
        Map<String, Object> massage = new HashMap<>(), map;
        massage.put("action", Action.getProfile);
        massage.put("profile", profile);
        map = ClientNetwork.serve(massage);

        if (map.get("answer") != null)
            return (ImageView) map.get("answer");
        return null;
    }

    public static ImageView setProfile(Profile profile, javafx.scene.image.ImageView imageView) {
        Map<String, Object> massage = new HashMap<>(), map;
        massage.put("action", Action.setProfile);
        massage.put("profile", profile);
        massage.put("image", imageView);
        map = ClientNetwork.serve(massage);

        if (map.get("answer") != null)
            return (ImageView) map.get("answer");
        return null;
    }

    public static Map<String, String> getInformation(Profile profile) {
        Map<String, Object> massage = new HashMap<>(), map;
        massage.put("action", Action.getInformation);
        massage.put("profile", profile);
        map = ClientNetwork.serve(massage);

        if (map.get("answer") != null)
            return (Map<String, String>) map.get("answer");
        return null;
    }

    public static void setInformation(Profile profile, Map<String, String> information) {
        Map<String, Object> massage = new HashMap<>();
        massage.put("action", Action.setInformation);
        massage.put("profile", profile);
        massage.put("information", information);
        ClientNetwork.serve(massage);
    }

    public static Integer like(Post post) {
        Map<String, Object> massage = new HashMap<>(), map;
        massage.put("action", Action.like);
        massage.put("profile", currentUser);
        massage.put("liked", post);
        massage.put("liked", post);
        map = ClientNetwork.serve(massage);
        if (map.get("answer") != null)
            return (Integer) map.get("answer");
        return null;
    }

    public static Integer unlike(Post post) {
        Map<String, Object> massage = new HashMap<>() , map;
        massage.put("action",Action.dislike);
        massage.put("profile",currentUser);
        massage.put("disliked",post);
        map = ClientNetwork.serve(massage);
        if (map.get("answer") != null)
            return (Integer) map.get("answer");
        return null;
    }

    public static Integer repost(Post post){
        Map<String , Object> massage = new HashMap<>(),map;
        massage.put("action",Action.repost);
        massage.put("profile",currentUser);
        massage.put("repost",post);
        map = ClientNetwork.serve(massage);
        if (map.get("answer") != null)
            return (Integer) map.get("answer");
        return null;
    }
    public static Integer unRepost(Post post){
        Map<String , Object> massage = new HashMap<>(),map;
        massage.put("action",Action.repost);
        massage.put("profile",currentUser);
        massage.put("unRepost",post);
        map = ClientNetwork.serve(massage);
        if (map.get("answer") != null)
            return (Integer) map.get("answer");
        return null;
    }

    public static List<Comment> getComments(Post post){
        Map<String , Object> massage = new HashMap<>(),map;
        massage.put("action",Action.getComment);
        massage.put("post",post);
        map = ClientNetwork.serve(massage);
        if (map.get("answer") != null)
            return (List<Comment>) map.get("answer");
        return null;
    }

    public static List<Comment> setComment(Post post, Comment comment){
        Map<String , Object> massage = new HashMap<>(),map;
        massage.put("action",Action.setComment);
        massage.put("profile",currentUser);
        massage.put("commented",post);
        massage.put("comment",comment);
        map = ClientNetwork.serve(massage);
        if (map.get("answer") != null)
            return (List<Comment>) map.get("answer");
        return null;
    }


}
