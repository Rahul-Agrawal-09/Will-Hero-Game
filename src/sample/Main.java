package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Parent root;
    @Override
    public void start(Stage primaryStage) throws Exception{
        root = FXMLLoader.load(getClass().getResource("Parent_Background.fxml"));
        primaryStage.setTitle("WILL-HERO");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
        GameConsole.setStage(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
