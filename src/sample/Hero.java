package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class Hero extends GameObject implements Runnable{
    private final Game game;
    private AnchorPane pane;
    private Double LaunchSpeed;

    public Hero(ImageView IV, Game game){
        super(IV,0.0);
        this.game=game;
        this.LaunchSpeed =350.0;
    }

    public void setPane(AnchorPane AP){
        this.pane=AP;
        pane.getChildren().add(super.getImageView());
    }

    @Override
    public void run() {
        Timeline tl=new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(5), event->{
            if(game.IsHeroAboveIsland()){
                this.LaunchSpeed =350.0;
            }
            double p=((this.LaunchSpeed)-205)/100;
            this.LaunchSpeed -=1.5;
            super.getImageView().setLayoutY(super.getImageView().getLayoutY()-p);
//            System.out.println(game.currentIsland.getyPositionTop()-this.getyPositionBottom());
        } ));
        tl.play();
    }
}
