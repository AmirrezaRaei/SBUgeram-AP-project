package Model;

import java.io.Serializable;
/**
 * <h1>Comment</h1>
 * <p>this class handles Comment</p>
 * @author A.Raei
 * @since 12/2/2021
 * @version 1.0
 */
public class Comment implements Serializable,Comparable {
    private Profile writer = new Profile();
    private String comment;

    public Comment(Profile currentUser, String text) {
        this.writer = currentUser;
        this.comment = text;
    }

    public Comment() {

    }

    public Profile getWriter() {
        return writer;
    }

    public void setWriter(Profile writer) {
        this.writer = writer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
