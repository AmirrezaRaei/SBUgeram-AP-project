package Model;

import Common.Action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Model.Main.posts;
import static Model.Main.profiles;

/**
 * <h1>ClientAPI</h1>
 * <p>this class sends massage to server and receive its responds</p>
 * @author A.Raei
 * @since 12/2/2021
 * @version 1.0
 */
public class ClientAPI {

    /**
     * @param username its check for existing
     * @param pass its check for belong to the same user
     * @return the current user
     */
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

    /**
     * this method helps server to reports someone's logs out
     * @param profile its the user that want to log out of its account
     */
    public static void logout(Profile profile) {
        Map<String, Object> message = new HashMap<>();
        message.put("action", Action.logout);
        message.put("profile", profile);
        ClientNetwork.serve(message);
    }

    /**
     * @param profile it sends to server to add to users
     * @return the user that made
     */
    public static Profile signup(Profile profile) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.signup);
        message.put("profile", profile);
        map = ClientNetwork.serve(message);
        if (map.get("answer") == null)
            return null;
        return (Profile) map.get("answer");
    }

    /**
     * it sends server a post to add to all posts
     * @param post the post that wants to add
     *@param image its the post's attached photo
     *@param path the absolute path of the attached photo
     */
    public static void addPost(Post post, byte[] image, String path) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.addPost);
        message.put("post", post);
        message.put("image", image);
        message.put("path", path);
        ClientNetwork.serve(message);
    }

    /**
     * @param profile it show what user wants the posts
     * @return the map that one of it objects is the posts list
     */
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

    /**
     * @param profile its the user that we want its posts
     * @return the map that one of its objects is user's posts
     */
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

    /**
     * @param profile its the current user that shouldn't be in user's list that we can follow
     * @return a map that one of its objects is all users except the current user
     */
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

    /**
     * it sends a message to server to add someone to another one's followers and followings
     * @param profile its the user that wants to follow someone
     * @param user the user that wants to be followed
     * @return a string that has number of followers and followings and posts of user
     */
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

    /**
     * it sends a message to server to add someone to another one's followers and followings
     * @param profile its the user that wants to unfollow someone
     * @param user the user that wants to be unfollowed
     * @return a string that has number of followers and followings and posts of user
     */
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

    /**
     * @param profile its the user that client wants its info
     * @return the user's profile photo
     */
    public static byte[] getProfile(Profile profile) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.getProfile);
        message.put("profile", profile);
        map = ClientNetwork.serve(message);

        if (map.get("answer") == null)
            return null;
        return (byte[]) map.get("answer");
    }

    /**
     * it adds a photo for user's profile and just for first sign up function
     * @param profile its the user that wants to set profile photo for itself
     * @param imageView its the profile photo
     * @param path its the absolute path of profile photo
     * @return the profile photo
     */
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

    /**
     * @param profile its the user that client wants its information
     * @return a map that contain user's personal information
     */
    public static Map<String, String> getInformation(Profile profile) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.getInformation);
        message.put("profile", profile);
        map = ClientNetwork.serve(message);

        if (map.get("answer") == null)
            return null;
        return (Map<String, String>) map.get("answer");
    }

    /**
     * this method add some information for user
     * @param profile its the user that wants to set some personal information for its account
     * @param information its the information that users add
     */
    public static void setInformation(Profile profile, Map<String, String> information) {
        Map<String, Object> message = new HashMap<>();
        message.put("action", Action.setInformation);
        message.put("profile", profile);
        message.put("information", information);
        ClientNetwork.serve(message);
    }

    /**
     * it sends a message to server to add user to a post's likes
     * @param post the post that wants to be liked
     * @param profile its the user that wants to like a post
     * @return a int that shows posts likes number
     */
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
    /**
     * it sends a message to server to remove user to a post's likes
     * @param post the post that wants to be unlike
     * @param profile its the user that wants wants to take its like's back
     * @return a int that shows posts likes number
     */
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

    /**
     * it sends a message to server to add user to a post's reposts
     * @param profile its the user that wants to repost a post
     * @param post the post that wants to be reposted
     * @return a int that shows posts reposts number
     */
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

    /**
     * it sends a message to server to remove user to a post's reposts
     * @param profile its the user that wants to unRepost
     * @param post the post that wants to be unReposted
     * @return a int that shows posts reposts number
     */
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

    /**
     * @param post its the post that client wants its info
     * @return the list of post's comments
     */
    public static List<Comment> getComments(Post post) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.getComment);
        message.put("post", post);
        map = ClientNetwork.serve(message);
        if (map.get("answer") == null)
            return null;
        return (List<Comment>) map.get("answer");
    }

    /**
     * it sends a message to server to set a comment to a post's comments
     * @param post its the user that wants to adda comment for a post
     * @param profile the post that wants add a comment's to
     * @param comment the comment that wants to add
     * @return the list of post's comments
     */
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

    /**
     * it changes the user's profile photo
     * @param profile its the user that wants to change its profile photo
     * @param image its the profile photo
     * @param path its the absolute path of new profile photo
     * @return the profile photo
     */
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

    /**
     * a method that gives shown information of users
     * @param profile its the user that client wants its information
     * @return a string that has number of followers and followings and posts of user
     */
    public static String getNumbers(Profile profile) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.getNumbers);
        message.put("profile", profile);
        map = ClientNetwork.serve(message);
        if (map.get("answer") == null)
            return null;
        return (String) map.get("answer");
    }

    /**
     * this method helps server to repost in console too
     * @param user its the user that client wants its information
     * @param profile its the user that wants the another user's info
     * @return a string that has number of followers and followings and posts of user
     */
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
    /**
     * a method that gives shown details of posts
     * @param post its the post that client wants its details
     * @return a string that shows post's likes and reposts and comments numbers
     */
    public static String getPostDetails(Post post) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.getPostDetails);
        message.put("post", post);
        map = ClientNetwork.serve(message);
        if (map.get("answer") == null)
            return null;
        return (String) map.get("answer");
    }

    public static void addPost(Post post) {
        Map<String, Object> message = new HashMap<>();
        message.put("action", Action.addPost);
        message.put("post", post);
        ClientNetwork.serve(message);
    }

    /**
     * @param post its the post that client wants its information
     * @return the list of post's likes
     */
    public static List<String> getLikes(Post post) {
        Map<String, Object> message = new HashMap<>(), map;
        message.put("action", Action.getLikes);
        message.put("post", post);
        map = ClientNetwork.serve(message);

        if (map.get("answer") == null)
            return null;
        return (List<String>) map.get("answer");
    }

    /**
     * @param post its the post that client wants its info
     * @return the list of post's likes
     */
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
