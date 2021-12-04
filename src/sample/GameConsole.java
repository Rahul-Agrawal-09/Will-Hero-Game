package sample;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class GameConsole {

    @FXML
    private ImageView Hero;

    @FXML
    private AnchorPane HomePage;

    @FXML
    void move(MouseEvent event) {
        Hero.setLayoutX(Hero.getLayoutX()+50);
    }

}
