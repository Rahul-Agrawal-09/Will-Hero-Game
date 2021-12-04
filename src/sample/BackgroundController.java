package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class BackgroundController {
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

    public BackgroundController(){
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
    }

}
