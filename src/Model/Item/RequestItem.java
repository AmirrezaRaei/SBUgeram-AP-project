package Model.Item;

import Controller.RequestItemController;
import Model.Request;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class RequestItem extends ListCell<Request> {
    @Override
    public void updateItem(Request request, boolean empty) {
        super.updateItem(request, empty);
        if (request != null) {
            try {
                setGraphic(new RequestItemController(request).init());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

