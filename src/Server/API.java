package Server;

import Common.Action;
import Model.Comment;
import Model.Gender;
import Model.Post;
import Model.Profile;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        output.put("answer", profile);
        return output;
    }

    public static Map<String,Object> logout(Map<String,Object> input){
        Map<String, Object> output = new HashMap<>();
        Profile profile = (Profile) input.get("profile");
        output.put("action",Action.logout);
        output.put("answer",new Boolean(true));
        System.out.println(profile.getUsername() +" bye");
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
        ImageView image = (ImageView) input.get("image");
//        Boolean flag = true;
        Server.posts.add(outputPost);
        /*shayad niaz beshe chizi ezafe beshe
        /*todo
         */
        Database.getInstance().updateDataBase();
        output.put("action", Action.addPost);
        output.put("answer", new Boolean(true));
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
        List<Post> help = Server.profiles.get(username).getPosts();
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
        ans = Server.profiles.get(profile.getUsername()).followers.size() + "|"
                + Server.profiles.get(profile.getUsername()).following.size();
        output.put("action", Action.follow);
        output.put("answer", ans);
        return output;
        //todo
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
        ans = Server.profiles.get(profile.getUsername()).followers.size() + "|"
                + Server.profiles.get(profile.getUsername()).following.size();
        output.put("action", Action.unfollow);
        output.put("answer", ans);
        return output;
    }

    public static Map<String, Object> getProfile(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        Profile profile = (Profile) input.get("profile");
        ImageView imageView = Server.profiles.get(profile.getUsername()).getProfileImage();
        output.put("action", Action.getProfile);
        output.put("answer", imageView);
        return output;
    }

    public static Map<String, Object> setProfile(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        Profile profile = (Profile) input.get("profile");
        ImageView imageView = (ImageView) input.get("image");
        Server.profiles.get(profile.getUsername()).setProfileImage(imageView);
        Database.getInstance().updateDataBase(); // update database file
        output.put("action", Action.setProfile);
        output.put("answer", imageView);
        return output;
    }

    public static Map<String, Object> getInformation(Map<String, Object> input) {
        Map<String, String> output = new HashMap<>();
        Map<String, Object> result = new HashMap<>();
        Profile profile = (Profile) input.get("profile");
        output.put("firstName", Server.profiles.get(profile).getFirstname());
        output.put("age", String.valueOf(Server.profiles.get(profile).getAge()));
        output.put("bio", Server.profiles.get(profile).getBio());
        /**
         * see the other field is exist or not to collect them
         **/
        if (Server.profiles.get(profile).getLastname() != null)
            output.put("lastname", Server.profiles.get(profile).getLastname());
        if (Server.profiles.get(profile.getEmailAddress()) != null)
            output.put("emailAddress", Server.profiles.get(profile).getEmailAddress());
        if (Server.profiles.get(profile).getGender() != Gender.unselected)
            output.put("gender", String.valueOf(Server.profiles.get(profile).getGender()));
        if (Server.profiles.get(profile).getPhone() != null)
            output.put("phone", Server.profiles.get(profile).getPhone());
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
            Server.profiles.get(profile.getUsername()).setLastname(result.get("firstname"));
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

        for (Post help : Server.posts) {
            if (help.equals(post)) {
                help.getLiked().add(profile);
                counter = help.getLiked().size();
            }
        }
        for (Post help :
                Server.profiles.get(post.getProfile().getUsername()).getPosts()) {
            if (help.equals(post))
                help.getLiked().add(profile);
        }
        Database.getInstance().updateDataBase();
        output.put("action", Action.like);
        output.put("answer", counter);
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

        for (Post help : Server.posts) {
            if (help.equals(post)) {
                help.getLiked().remove(profile);
                counter = help.getLiked().size();
            }
        }
        for (Post help :
                Server.profiles.get(post.getProfile().getUsername()).getPosts()) {
            if (help.equals(post))
                help.getLiked().remove(profile);
        }
        Database.getInstance().updateDataBase();
        output.put("action", Action.like);
        output.put("answer", counter);
        return output;
    }

    public static Map<String, Object> repost(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        int counter = 0;
        Profile profile = (Profile) input.get("profile");
        Post post = (Post) input.get("repost");
        for (Post help :
                Server.posts) {
            if (help.equals(post)) {
                help.getReposted().add(profile);
                counter = help.getReposted().size();
            }
        }
        for (Post help :
                Server.profiles.get(post.getProfile().getUsername()).getPosts()) {
            help.getReposted().add(profile);
        }
        Server.profiles.get(profile.getUsername()).getPosts().add(post);
        Database.getInstance().updateDataBase();
        output.put("action", Action.repost);
        output.put("action", counter);
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
                Server.posts) {
            if (help.equals(post)) {
                help.getReposted().remove(profile);
                counter = help.getReposted().size();
            }
        }
        for (Post help :
                Server.profiles.get(post.getProfile().getUsername()).getPosts()) {
            help.getReposted().remove(profile);
        }
        Server.profiles.get(profile.getUsername()).getPosts().remove(post);
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
                Server.posts) {
            if (help.equals(post)) {
                help.getComments().add(comment);
                commentList = help.getComments();
            }
        }
        for (Post help :
                Server.profiles.get(post.getProfile().getUsername()).getPosts()) {
            if (help.equals(post))
                help.getComments().add(comment);
        }
        Database.getInstance().updateDataBase();
        output.put("action", Action.setComment);
        output.put("comments", commentList);
        return output;
    }

    public static Map<String, Object> getComment(Map<String, Object> input) {
        Post post = (Post) input.get("profile");
        List<Comment> commentList;
        Map<String, Object> output = new HashMap<>();
        for (Post help :
                Server.posts) {
            post = help;
        }
        commentList = post.getComments();
        output.put("action", Action.getComment);
        output.put("answer", commentList);
        return output;
    }
}