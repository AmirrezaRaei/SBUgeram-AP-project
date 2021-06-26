package Model;

import Common.Action;
import javafx.geometry.Pos;
import javafx.scene.image.Image;

import javax.swing.text.html.ImageView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static Model.Main.*;

public class ClientAPI {

    public static Profile login(String username, String pass) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.login);
        message.put("username", username);
        message.put("password", pass);
        map = ClientNetwork.serve(message);
        if (map.get("answer") == null)
            return null;
        return (Profile) map.get("answer");
    }

    public static void logout(Profile profile) {
        Map<String, Object> message = new HashMap<>();
        message.put("action", Action.logout);
        message.put("profile", profile);
        ClientNetwork.serve(message);
    }

    public static Profile signup(Profile profile) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.signup);
        message.put("profile", profile);
        map = ClientNetwork.serve(message);
        if (map.get("answer") == null)
            return null;
        return (Profile) map.get("answer");
    }

    public static void addPost(Post post, byte[] image, String path) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.addPost);
        message.put("post", post);
        message.put("image", image);
        message.put("path", path);
        map = ClientNetwork.serve(message);
    }

    public static Map<String, Object> getPosts(Profile profile) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.getPosts);
        message.put("posts", posts);
        message.put("profile", profile);
        map = ClientNetwork.serve(message);
        return map;
    }

    public static List<Post> getAllPosts(Profile profile) {
        Map<String, Object> output = getPosts(profile);
        return (List<Post>) output.get("posts");
    }

    public static List<Post> getMyPosts(Profile profile) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.getMyPost);
        message.put("profile", profile);
        map = ClientNetwork.serve(message);
        return (List<Post>) map.get("myPost");
    }

    public static List<Post> getAllOfMyPosts(Profile profile) {
        List<Post> output = getMyPosts(profile);
        return (List<Post>) output;
    }

    public static Map<String, Object> getProfiles(Profile profile) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.getProfiles);
        message.put("profile", profile);
        message.put("profiles", profiles);
        map = ClientNetwork.serve(message);
        return map;
    }

    public static Map<String, Profile> getAllProfiles(Profile profile) {
        Map<String, Object> output = getProfiles(profile);
        return (Map<String, Profile>) output.get("profiles");
    }

    public static String follow(Profile profile, Profile user) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.follow);
        message.put("profile", profile);
        message.put("followed", user);
        map = ClientNetwork.serve(message);

        if (map.get("answer") == null)
            return null;
        return (String) map.get("answer");
    }

    public static String unfollow(Profile profile, Profile user) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.unfollow);
        message.put("profile", profile);
        message.put("unfollowed", user);
        map = ClientNetwork.serve(message);

        if (map.get("answer") != null)
            return (String) map.get("answer");
        return null;
    }

    public static byte[] getProfile(Profile profile) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.getProfile);
        message.put("profile", profile);
        map = ClientNetwork.serve(message);

        if (map.get("answer") == null)
            return null;
        return (byte[]) map.get("answer");
    }

    public static byte[] setProfile(Profile profile, byte[] imageView, String path) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.setProfile);
        message.put("profile", profile);
        message.put("image", imageView);
        message.put("path", path);
        map = ClientNetwork.serve(message);

        if (map.get("answer") == null)
            return null;
        return (byte[]) map.get("answer");
    }

    public static Map<String, String> getInformation(Profile profile) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.getInformation);
        message.put("profile", profile);
        map = ClientNetwork.serve(message);

        if (map.get("answer") == null)
            return null;
        return (Map<String, String>) map.get("answer");
    }

    public static void setInformation(Profile profile, Map<String, String> information) {
        Map<String, Object> message = new HashMap<>();
        message.put("action", Action.setInformation);
        message.put("profile", profile);
        message.put("information", information);
        ClientNetwork.serve(message);
    }

    public static Integer like(Post post, Profile profile) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.like);
        message.put("profile", profile);
        message.put("liked", post);
        map = ClientNetwork.serve(message);
        if (map.get("answer") == null)
            return null;
        return (Integer) map.get("answer");
    }

    public static Integer unlike(Post post, Profile profile) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.dislike);
        message.put("profile", profile);
        message.put("disliked", post);
        map = ClientNetwork.serve(message);
        if (map.get("answer") == null)
            return null;
        return (Integer) map.get("answer");
    }

    public static Integer repost(Profile profile, Post post) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.repost);
        message.put("profile", profile);
        message.put("repost", post);
        map = ClientNetwork.serve(message);
        if (map.get("answer") == null)
            return null;
        return (Integer) map.get("answer");
    }

    public static Integer unRepost(Profile profile, Post post) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.unReposted);
        message.put("profile", profile);
        message.put("unRepost", post);
        map = ClientNetwork.serve(message);
        if (map.get("answer") == null)
            return null;
        return (Integer) map.get("answer");
    }

    public static List<Comment> getComments(Post post) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.getComment);
        message.put("post", post);
        map = ClientNetwork.serve(message);
        if (map.get("answer") == null)
            return null;
        return (List<Comment>) map.get("answer");
    }

    public static List<Comment> setComment(Post post, Profile profile, Comment comment) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.setComment);
        message.put("profile", profile);
        message.put("commented", post);
        message.put("comment", comment);
        map = ClientNetwork.serve(message);
        if (map.get("answer") == null)
            return null;
        return (List<Comment>) map.get("answer");
    }

    // extra method
    public static byte[] changeProfile(Profile profile, byte[] image, String path) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.changeProfile);
        message.put("profile", profile);
        message.put("image", image);
        message.put("path", path);
        map = ClientNetwork.serve(message);
        if (map.get("answer") == null)
            return null;
        return (byte[]) map.get("answer");
    }

    public static String getNumbers(Profile profile) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.getNumbers);
        message.put("profile", profile);
        map = ClientNetwork.serve(message);
        if (map.get("answer") == null)
            return null;
        return (String) map.get("answer");
    }

    public static String getProfilesNumber(Profile user, Profile profile) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.getProfilesNumber);
        message.put("profile", profile);
        message.put("user", user);
        map = ClientNetwork.serve(message);
        if (map.get("answer") == null)
            return null;
        return (String) map.get("answer");
    }

    public static String getPostDetails(Post post) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.getPostDetails);
        message.put("post", post);
        map = ClientNetwork.serve(message);
        if (map.get("answer") == null)
            return null;
        return (String) map.get("answer");
    }

    public static List<Post> timelineUpdate(Profile profile) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.timelineUpdate);
        message.put("posts", posts);
        message.put("profile", profile);
        map = ClientNetwork.serve(message);
        if (map.get("answer") == null)
            return null;
        return (List<Post>) map.get("answer");
    }

    public static void addPost(Post post) {
        Map<String, Object> message = new HashMap<>();
        message.put("action", Action.addPost);
        message.put("post", post);
        ClientNetwork.serve(message);
    }

    public static List<String> getLikes(Post post) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.getLikes);
        message.put("post", post);
        map = ClientNetwork.serve(message);

        if (map.get("answer") == null)
            return null;
        return (List<String>) map.get("answer");
    }

    public static List<String> getReposts(Post post){
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.getReposts);
        message.put("post", post);
        map = ClientNetwork.serve(message);

        if (map.get("answer") == null)
            return null;
        return (List<String>) map.get("answer");
    }

    public static List<String> getFollowers(Profile profile){
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.getFollowers);
        message.put("profile", profile);
        map = ClientNetwork.serve(message);

        if (map.get("answer") == null)
            return null;
        return (List<String>) map.get("answer");
    }

    public static List<String> getFollowings(Profile profile){
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.getFollowings);
        message.put("profile", profile);
        map = ClientNetwork.serve(message);

        if (map.get("answer") == null)
            return null;
        return (List<String>) map.get("answer");
    }
}
