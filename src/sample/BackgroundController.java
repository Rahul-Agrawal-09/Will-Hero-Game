package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public final class BackgroundController implements Initializable {
    private GameConsole gameConsole;

    @FXML
    private ImageView BackgroundImage;
    @FXML
    private ImageView Cloud1;
    @FXML
    private ImageView Cloud2;
    @FXML
    private ImageView Cloud3;
    @FXML
    private ImageView Cloud4;
    @FXML
    private AnchorPane CloudPane;
    @FXML
    private AnchorPane ParentOfAll;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timeline tl=new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(11),event->{
            for(Node I:CloudPane.getChildren()){
                I.setLayoutX(I.getLayoutX()-0.1);
                if(I.getLayoutX()<-150)
                    I.setLayoutX(800);
            }
        } ));
        tl.play();
        GameConsole.setPrimaryStage(ParentOfAll);
    }

    public void setGameConsole(GameConsole GC){
        this.gameConsole=GC;
    }
}
