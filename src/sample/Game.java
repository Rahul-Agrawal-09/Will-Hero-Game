package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class Game {

    @FXML
    private ImageView player;

    public Game(){
        //instantiating timeline for a specific game
        Timeline tl=new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new TimeHandler()));
//        new Controller().move();
        tl.play();
    }

    private class TimeHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event){
            TranslateTransition translate=new TranslateTransition();
            translate.setNode(player);
            translate.setDuration(Duration.millis(500));
            translate.setByX(100);
            translate.play();
    }
    }
}
