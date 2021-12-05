package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class Game {


    public Game(){
        //instantiating timeline for a specific game
        Timeline tl=new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(1000), new TimeHandler()));
        tl.play();
    }

    private class TimeHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event){
            TranslateTransition translate=new TranslateTransition();
//            translate.setNode();
            translate.setDuration(Duration.millis(500));
            translate.setByX(100);
            translate.play();
    }
    }
}
