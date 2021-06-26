package Server;

import Common.Action;
import Common.Time;
import Model.Comment;
import Model.Gender;
import Model.Post;
import Model.Profile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.*;
import java.util.stream.Collectors;

public class API {
    public static Map<String, Object> login(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        String user, password;
        Boolean isNull = false;
        user = (String) input.get("username");
        password = (String) input.get("password");
        Profile help = Server.profiles.get(user);
        if (help != null)
            isNull = true;
        output.put("action", Action.login);
        output.put("exist", isNull);
        if (!isNull)
            return output;
        Profile profile = Server.profiles.get(user).authenticate(user, password);
        if (profile != null){
            System.out.println(user + " login");
            System.out.println("time : " + Time.getTime());
        }
        output.put("answer", profile);
        return output;
    }

    public static Map<String, Object> logout(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        Profile profile = (Profile) input.get("profile");
        output.put("action", Action.logout);
        output.put("answer", new Boolean(true));
        System.out.println(profile.getUsername() + " logout");
        System.out.println("Time : " + Time.getTime());
        return output;
    }

    public static Map<String, Object> signup(Map<String, Object> input) {
        // Fields
        Map<String, Object> output = new HashMap<>();
        String username;
        Profile profile;
        //todo
        profile = (Profile) input.get("profile");
        username = profile.getUsername();
        Server.profiles.put(username, profile);
        Database.getInstance().updateDataBase();
        output.put("action", Action.signup);
        output.put("answer", profile);
        return output;
    }

    public static Map<String, Object> addPost(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        List<Post> help = new ArrayList<>(Server.posts);
        Post outputPost = (Post) input.get("post");
        String path = (String) input.get("path");
        byte [] image = (byte[]) input.get("image");
//        Boolean flag = true;
        Server.posts.add(outputPost);
        Server.profiles.get(outputPost.getProfile().getUsername()).getPosts().add(outputPost);
        if (image != null)
            for (Post post :
                    Server.posts) {
                if (post.equals(outputPost))
                    post.setImage(image);
            }
        Database.getInstance().updateDataBase();
        output.put("action", Action.addPost);
        output.put("answer", new Boolean(true));
        System.out.println(outputPost.getProfile().getUsername() + " publish");
        if (image != null)
            System.out.println("message : " + outputPost.getTitle() + " " + path + " " + outputPost.getProfile().getUsername());
        else System.out.println("message : " + outputPost.getTitle() + " " + outputPost.getProfile().getUsername());
        System.out.println("Time : " + Time.getTime());
        return output;
    }

    public static Map<String, Object> getPosts(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        List<Post> help = new ArrayList<>(Server.posts);
        output.put("action", Action.getPosts);
        output.put("posts", help);
        return output;
    }

    public static Map<String, Object> getMyPosts(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        Profile profile = (Profile) input.get("profile");
        String username = profile.getUsername();
        Set<Post> help = Server.profiles.get(username).getPosts();
        output.put("myPosts", help);
        return output;
    }

    public static Map<String, Object> getProfiles(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        Map<String, Profile> map = new HashMap<>();
        Profile profile = (Profile) input.get("profile");
        String username = profile.getUsername();
        List<String> stringList;
        output.put("action", Action.getProfiles);
        stringList = Server.profiles.keySet().stream()
                .filter(user -> !user.equals(username))
                .collect(Collectors.toList());

        List<Profile> profileList = Server.profiles.values()
                .stream().filter(user -> !user.getUsername().equals(username))
                .collect(Collectors.toList());

        for (int i = 0; i < stringList.size(); i++) {
            map.put(stringList.get(i), profileList.get(i));
        }
        output.put("profiles", map);
        return output;
    }

    public static Map<String, Object> follow(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        String ans;
        Profile profile = (Profile) input.get("profile");
        Profile followed = (Profile) input.get("followed");
        Server.profiles.get(profile.getUsername()).following.add(followed);
        Server.profiles.get(followed.getUsername()).followers.add(profile);
        Database.getInstance().updateDataBase();
        ans = Server.profiles.get(profile.getUsername()).following.size() + "|"
                + Server.profiles.get(followed.getUsername()).followers.size();
        output.put("action", Action.follow);
        output.put("answer", ans);
        System.out.println(profile.getUsername() + " follow");
        System.out.println("message : " + followed.getUsername());
        System.out.println("Time : " + Time.getTime());
        return output;
    }

    public static Map<String, Object> unfollow(Map<String, Object> input) {
        // like follow method
        Map<String, Object> output = new HashMap<>();
        String ans;
        Profile profile = (Profile) input.get("profile");
        Profile unfollowed = (Profile) input.get("unfollowed");
        Server.profiles.get(profile.getUsername()).following.
                removeIf(target -> target.getUsername().equals(unfollowed.getUsername()));
        Server.profiles.get(unfollowed.getUsername()).followers.
                removeIf(target -> target.getUsername().equals(profile.getUsername()));
        Database.getInstance().updateDataBase();
        ans = Server.profiles.get(profile.getUsername()).following.size() + "|"
                + Server.profiles.get(unfollowed.getUsername()).followers.size();
        output.put("action", Action.unfollow);
        output.put("answer", ans);
        System.out.println(profile.getUsername() + " unfollow");
        System.out.println("message : " + unfollowed.getUsername());
        System.out.println("Time : " + Time.getTime());
        return output;
    }

    public static Map<String, Object> getProfile(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        Profile profile = (Profile) input.get("profile");
        byte[] image = Server.profiles.get(profile.getUsername()).getProfileImage();
        output.put("action", Action.getProfile);
        output.put("answer", image);
        return output;
    }

    public static Map<String, Object> setProfile(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        Profile profile = (Profile) input.get("profile");
        byte[] image = (byte[]) input.get("image");
        String path = (String) input.get("path");
        Server.profiles.get(profile.getUsername()).setProfileImage(image);
        Server.profiles.get(profile.getUsername()).setPath(path);
        Database.getInstance().updateDataBase(); // update database file
        output.put("action", Action.setProfile);
        output.put("answer", image);
        System.out.println(profile.getUsername() + " register" + path);
        System.out.println("Time : " + Time.getTime());
        return output;
    }

    public static Map<String, Object> getInformation(Map<String, Object> input) {
        Map<String, String> output = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        Profile profile = (Profile) input.get("profile");
        output.put("firstname", Server.profiles.get(profile.getUsername()).getFirstname());
        output.put("age", String.valueOf(Server.profiles.get(profile.getUsername()).getAge()));
        output.put("bio", Server.profiles.get(profile.getUsername()).getBio());
        /**
         * see the other field is exist or not to collect them
         **/
        if (Server.profiles.get(profile.getUsername()).getLastname() != null)
            output.put("lastname", Server.profiles.get(profile.getUsername()).getLastname());
        if (Server.profiles.get(profile.getUsername()).getEmailAddress() != null)
            output.put("emailAddress", Server.profiles.get(profile.getUsername()).getEmailAddress());
        if (Server.profiles.get(profile.getUsername()).getGender() != Gender.unselected)
            output.put("gender", String.valueOf(Server.profiles.get(profile.getUsername()).getGender()));
        if (Server.profiles.get(profile.getUsername()).getPhone() != null)
            output.put("phone", Server.profiles.get(profile.getUsername()).getPhone());
        result.put("action", Action.getInformation);
        result.put("answer", output);
        return result;
    }

    public static Map<String, Object> setInformation(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        Map<String, String> result;
        Profile profile = (Profile) input.get("profile");
        result = (Map<String, String>) input.get("information");

        Server.profiles.get(profile.getUsername()).setFirstname(result.get("firstname"));
        Server.profiles.get(profile.getUsername()).setAge(Integer.parseInt(result.get("age")));
        Server.profiles.get(profile.getUsername()).setBio(result.get("bio"));
        /**
         * see the other field is exist or not to collect them
         **/
        if (result.get("lastname") != null)
            Server.profiles.get(profile.getUsername()).setLastname(result.get("lastname"));
        if (result.get("emailAddress") != null)
            Server.profiles.get(profile.getUsername()).setEmailAddress(result.get("emailAddress"));
        if (result.get("gender") != null)
            Server.profiles.get(profile.getUsername()).setGender(result.get("gender"));
        if (result.get("phone") != null)
            Server.profiles.get(profile.getUsername()).setPhone(result.get("phone"));
        output.put("action", Action.setProfile);
        output.put("answer", new Boolean(true));
        return output;
    }

    public static Map<String, Object> like(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        int counter = 0;
        Profile profile = (Profile) input.get("profile");
        Post post = (Post) input.get("liked");

        for (Post help :
                Server.profiles.get(post.getProfile().getUsername()).getPosts()) {
            if (help.equals(post)) {
                help.getLiked().add(profile);
                counter = help.getLiked().size();
            }
        }
        Database.getInstance().updateDataBase();
        output.put("action", Action.like);
        output.put("answer", counter);
        System.out.println(profile.getUsername() + " Like");
        System.out.println("message : " + post.getProfile().getUsername() + " " +post.getTitle());
        System.out.println("Time : " + Time.getTime());
        return output;
    }

    public static Map<String, Object> unlike(Map<String, Object> input) {
        /**
         *  similar to like method but vice versa
         */
        Map<String, Object> output = new HashMap<>();
        int counter = 0;
        Profile profile = (Profile) input.get("profile");
        Post post = (Post) input.get("disliked");
        for (Post help :
                Server.profiles.get(post.getProfile().getUsername()).getPosts()) {
            if (help.equals(post)) {
                help.getLiked().remove(profile);
                counter = help.getLiked().size();
            }
        }
        Database.getInstance().updateDataBase();
        output.put("action", Action.dislike);
        output.put("answer", counter);
        return output;
    }

    public static Map<String, Object> repost(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        int counter = 0;
        Profile profile = (Profile) input.get("profile");
        Post post = (Post) input.get("repost");
        for (Post help :
                Server.profiles.get(post.getProfile().getUsername()).getPosts()) {
            if (help.equals(post)) {
                help.getReposted().add(profile);
                Server.profiles.get(profile.getUsername()).getPosts().add(help);
                counter = help.getReposted().size();
            }
        }
        Database.getInstance().updateDataBase();
        output.put("action", Action.repost);
        output.put("action", counter);
        System.out.println(profile.getUsername() + " repost");
        System.out.println("message : ");
        return output;
    }

    public static Map<String, Object> unRepost(Map<String, Object> input) {
        /**
         *  similar to repost method but vice versa
         */
        Profile profile = (Profile) input.get("profile");
        Post post = (Post) input.get("unRepost");
        int counter = 0;
        Map<String, Object> output = new HashMap<>();

        for (Post help :
                Server.profiles.get(post.getProfile().getUsername()).getPosts()) {
            if (help.equals(post)) {
                help.getReposted().remove(profile);
                Server.profiles.get(profile.getUsername()).getPosts().remove(help);
                counter = help.getReposted().size();
            }
        }
        Database.getInstance().updateDataBase();
        output.put("action", Action.unReposted);
        output.put("answer", counter);
        return output;
    }

    public static Map<String, Object> setComment(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        List<Comment> commentList = new ArrayList<>();
        Profile profile = (Profile) input.get(input);
        Post post = (Post) input.get("commented");
        Comment comment = (Comment) input.get("comment");
        for (Post help :
                Server.profiles.get(post.getProfile().getUsername()).getPosts()) {
            if (help.equals(post)) {
                help.getComments().add(comment);
                commentList = help.getComments();
            }
        }
        Database.getInstance().updateDataBase();
        output.put("action", Action.setComment);
        output.put("comments", commentList);
//        System.out.println(profile.getUsername() + " comment");
        System.out.println("message : " + comment.getComment());
        System.out.println("Time : " + Time.getTime());
        return output;
    }

    public static Map<String, Object> getComment(Map<String, Object> input) {
        Post post = (Post) input.get("post");
        List<Comment> commentList;
        Map<String, Object> output = new HashMap<>();
        for (Post help :
                Server.posts) {
            if (help.equals(post))
                post = help;
        }
        commentList = post.getComments();
        output.put("action", Action.getComment);
        output.put("answer", commentList);
        return output;
    }

    // add extra method
    public static Map<String, Object> editProfile(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        Profile profile = (Profile) input.get("profile");
        byte[] image = (byte[]) input.get("image");
        String path = (String) input.get("path");
        Server.profiles.get(profile.getUsername()).setProfileImage(image);
        Server.profiles.get(profile.getUsername()).setPath(path);
        Database.getInstance().updateDataBase();
        output.put("action", Action.changeProfile);
        output.put("answer", image);
        System.out.println(profile.getUsername() + " update Information");
        System.out.println("message : " + path);
        System.out.println("Time : " + Time.getTime());
        return output;
    }

    public static Map<String, Object> getFollowers(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        List<String> stringList = new ArrayList<>();
        Profile profile = (Profile) input.get("profile");
        List<Profile> profileList = Server.profiles.get(profile.getUsername()).followers;

        for (Profile temp : profileList)
            stringList.add(temp.getUsername());
        output.put("action", Action.getFollowers);
        output.put("answer", stringList);
        return output;
    }

    public static Map<String, Object> getFollowing(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        List<String> stringList = new ArrayList<>();
        Profile profile = (Profile) input.get("profile");
        List<Profile> profileList = Server.profiles.get(profile.getUsername()).following;

        for (Profile temp : profileList)
            stringList.add(temp.getUsername());
        output.put("action", Action.getFollowings);
        output.put("answer", stringList);
        return output;
    }

    public static Map<String, Object> getNumbers(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        Profile profile = (Profile) input.get("profile");
        String data = Server.profiles.get(profile.getUsername()).following.size() + "|" +
                Server.profiles.get(profile.getUsername()).followers.size() + "|" +
                Server.profiles.get(profile.getUsername()).getPosts().size();
        output.put("action", Action.getNumbers);
        output.put("answer", data);
        return output;
    }

    public static Map<String, Object> getProfilesNumber(Map<String, Object> input) { // for server
        Map<String, Object> output = new HashMap<>();
        Profile profile = (Profile) input.get("profile");
        String data = Server.profiles.get(profile.getUsername()).following.size() + "|" +
                Server.profiles.get(profile.getUsername()).followers.size() + "|" +
                Server.profiles.get(profile.getUsername()).getPosts().size();
        output.put("action", Action.getProfilesNumber);
        output.put("answer", data);
        Profile user = (Profile) input.get("user");
        System.out.println(user.getUsername() + " get information " + profile.getUsername());
        System.out.println("message : " + profile.getUsername() + " " + profile.getPath());
        System.out.println("Time : " + Time.getTime());
        return output;
    }

    public static Map<String, Object> getPostDetails(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        Post post = (Post) input.get("post");
        String data;
        for (Post temp :
                Server.posts) {
            if (temp.equals(post))
                post = temp;
        }
        data = post.getLiked().size() + "|" + post.getReposted().size() + "|" + post.getComments().size();
        output.put("action", Action.getPostDetails);
        output.put("answer", data);
        return output;
    }

    public static Map<String,Object> timelineUpdate(Map<String, Object> input){
        Map<String, Object> output = new HashMap<>();
        List<Post> postList = new ArrayList<>();
        Profile profile = (Profile) input.get("profile");
        postList.addAll(Server.profiles.get(profile.getUsername()).getPosts());
        for (Profile temp :
                Server.profiles.get(profile.getUsername()).following) {
            postList.addAll(Server.profiles.get(temp.getUsername()).getPosts());
        }
        output.put("action",Action.timelineUpdate);
        output.put("answer",postList);
        return output;
    }

    public static Map<String,Object> getLikes(Map<String,Object> input){
        Map<String, Object> output = new HashMap<>();
        Post post = (Post) input.get("post");
        List<Profile> profileList;
        List<String> userString = new ArrayList<>();
        for (Post temp :
                Server.posts) {
            if (temp.equals(post))
                post = temp;
        }
        profileList = post.getLiked();
        for (Profile profile :
                profileList) {
            userString.add(profile.getUsername());
        }
        output.put("action",Action.getLikes);
        output.put("answer",userString);
        return output;
    }

    public static Map<String,Object> getReposts(Map<String,Object> input){
        Map<String, Object> output = new HashMap<>();
        Post post = (Post) input.get("post");
        List<Profile> profileList;
        List<String> userString = new ArrayList<>();
        for (Post temp :
                Server.posts) {
            if (temp.equals(post))
                post = temp;
        }
        profileList = post.getReposted();
        for (Profile profile :
                profileList) {
            userString.add(profile.getUsername());
        }
        output.put("action",Action.getReposts);
        output.put("answer",userString);
        return output;
    }
}