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
        Game G=new Game();
        root = FXMLLoader.load(getClass().getResource("Background.fxml"));
        primaryStage.setTitle("WILL-HERO");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
