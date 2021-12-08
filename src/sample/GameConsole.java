package sample;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GameConsole implements Initializable {

    @FXML
    private ImageView ExitButton;
    @FXML
    private ImageView Hero;
    @FXML
    private AnchorPane HomePage;
    @FXML
    private ImageView IslandHomePage;
    @FXML
    private ImageView IslandMid;
    @FXML
    private ImageView IslandRight;
    @FXML
    private ImageView LoadSavedButton;
    @FXML
    private ImageView StartButton;
    @FXML
    private ImageView WillHeroName;
    @FXML
    private ImageView orcs;

    @FXML
    void ExitGame(MouseEvent event) {

    }

    @FXML
    void LoadGame(MouseEvent event) {

    }

    @FXML
    void StartNewGame(MouseEvent event) {
        TranslateTransition translate=new TranslateTransition();
        translate.setNode(HomePage);
        translate.setDuration(Duration.millis(1000));
        translate.setByY(-500);
        translate.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Island I=new Island(IslandMid, 100.0);
        I.setMotion(40.0);
    }

//    void Translate(ImageView IV, ){
//
//    }

}
