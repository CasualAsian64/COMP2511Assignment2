package unsw.loopmania;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShopScreen {
    private Stage stage;
    private String title;
    private ShopController controller;

    private Scene scene;

    public ShopScreen(Stage stage) throws IOException {
        this.stage = stage;
        title = "Shop Screen";

        controller = new ShopController();
        FXMLLoader shopLoader = new FXMLLoader(getClass().getResource("shop.fxml"));
        shopLoader.setController(controller);

        // load into a Parent node called root
        Parent root = shopLoader.load();
        scene = new Scene(root, 500, 300);
    }

    public void start() {
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public ShopController getController() {
        return controller;
    }
}
