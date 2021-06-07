package Model;

import javafx.scene.image.ImageView;

public class Profile {
    // user information
    protected String username;
    protected String password;
    protected String firstname;
    protected String lastname;
    protected String emailAddress;
    protected Gender gender;
    protected int age;
    protected ImageView profileImage;

    public Profile(String username, String password) { // use in sign ip and information page
        this.username = username;
        this.password = password;
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

    public void setAge(int age) {
        this.age = age;
    }

    public void setProfileImage(ImageView profileImage) {
        this.profileImage = profileImage;
    }
}
