package concurrency;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Scene getMainScene() {
        return mainScene;
    }

    private static Scene mainScene;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/main_view.fxml"));
        primaryStage.setTitle("Monte Carlo");
        primaryStage.setScene(new Scene(root, 724, 490));
        mainScene = primaryStage.getScene();
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
