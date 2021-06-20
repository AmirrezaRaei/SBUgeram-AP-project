package Model.Item;

import Controller.CommentsItemController;
import Model.Comment;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class CommentsItem extends ListCell<Comment> {
    @Override
    public void updateItem(Comment comment, boolean empty) {
        super.updateItem(comment, empty);
        if (comment != null) {
            try {
                setGraphic(new CommentsItemController(comment).init());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
