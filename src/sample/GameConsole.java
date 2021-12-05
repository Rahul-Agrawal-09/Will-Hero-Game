package sample;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class GameConsole {

    @FXML
    private AnchorPane HomePage;
    @FXML
    private ImageView StartButton;
    @FXML
    private ImageView WillHeroName;
    @FXML
    void StartNewGame(MouseEvent event) {
        TranslateTransition translate=new TranslateTransition();
        translate.setNode(HomePage);
        translate.setDuration(Duration.millis(1000));
        translate.setByY(-500);
        translate.play();
    }

}
