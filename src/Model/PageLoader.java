package Model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.nio.file.Paths;


public class PageLoader {
    private static Stage stage;
    private static Scene scene;

    public static final int WIDTH = 420;
    public static final int HEIGHT = 620;

    public static void initStage(Stage primaryStage) {

        stage = primaryStage;
        stage.setTitle("SBU GRAM");
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHT);
        stage.initStyle(StageStyle.DECORATED); //the three buttons on top of stage will be shown
        stage.setResizable(false); //the resizable button is enabled. if your design is responsive this must be true
        stage.getIcons().add(new Image(Paths.get("logos/87390.png").toUri().toString()));
    }

    public void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Stage getPrimaryStage() {
        return stage;
    }

    public Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    //load fxmls with already set controllers
    public void load(String url) throws IOException {
        scene = new Scene(new PageLoader().loadFXML(url));
        stage.setScene(scene);
        stage.show();
    }

    //load fxmls with a special controller set in the runtime
    public void load(String fxml, Object controller) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/" + fxml + ".fxml"));
        fxmlLoader.setController(controller);
        fxmlLoader.load();
    }
}
