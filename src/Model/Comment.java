package Model;

public class Comment {
    private Profile writer;
    private String comment;

    public Comment(Profile currentUser, String text) {
        this.writer =currentUser;
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
}
