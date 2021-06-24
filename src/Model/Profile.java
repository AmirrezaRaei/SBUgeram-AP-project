package Model;

import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Profile extends Request {
    // user information
    protected String username;
    protected String password;
    protected String firstname ;
    protected String lastname;
    protected String emailAddress;
    protected Gender gender = Gender.unselected;
    protected int age = 0;
    protected String phone;
    public ImageView profileImage;
    protected boolean private_page;
    protected String bio;
    public ArrayList<Post> myPosts = new ArrayList<>();
    public ArrayList<Profile> followers = new ArrayList<>();
    public ArrayList<Profile> following = new ArrayList<>();
    public ArrayList<Request> requested = new ArrayList<>();


    public Profile(String username, String password) { // use in sign ip and information page
        this.username = username;
        this.password = password ;
        this.private_page = true;
        this.bio = "";
    }


    public Profile() {
    }

    public Profile(String username) {
        this.username = username;
    }

    // getter
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public ImageView getProfileImage() {
        return profileImage;
    }

    public String getPhone() {
        return phone;
    }

    public boolean getPrivate_page() {
        return private_page;
    }

    public boolean isPrivate_page() {
        return private_page;
    }

    public String getBio() {
        return bio;
    }

    public ArrayList<Post> getPosts() {
        return myPosts;
    }

    // setter
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setGender(String gender){
        if (gender.equals("male"))
            this.gender = Gender.male;
        else if (gender.equals("female"))
            this.gender = Gender.female;
        else
            this.gender = Gender.other;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setProfileImage(ImageView profileImage) {
        this.profileImage = profileImage;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPrivate_page(boolean private_page) {
        this.private_page = private_page;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.myPosts = posts;
    }

    public Profile authenticate(String username,String password){
        if(this.username.equals(username) && this.password.equals(password)) return this;
        return null;
    }
}
