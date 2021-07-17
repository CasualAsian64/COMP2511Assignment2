package unsw.loopmania;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameOverScreen {
    private Stage stage;
    private String title;
    private MainMenuController controller;

    private Scene scene;

    public GameOverScreen() throws IOException {
        // this.stage = stage;
        title = "Game Over";

        controller = new MainMenuController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOverScreenfxml"));
        loader.setController(controller);

        // load into a Parent node called root
        Parent root = loader.load();
        scene = new Scene(root, 500, 300);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public MainMenuController getController() {
        return controller;
    }
}