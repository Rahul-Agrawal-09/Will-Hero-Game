package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public final class Hero extends GameObject implements Runnable{
    private final Game game;
    private Island currentIsland;
    private AnchorPane pane;
    private Double LaunchSpeedY;
    private Double LaunchSpeedX;
    private boolean DoMove;

    public Hero(ImageView IV, Game game){
        super(IV,0.0);
        this.game=game;
        this.LaunchSpeedY=350.0;
        this.LaunchSpeedX=350.0;
        this.DoMove=false;
    }

    public void setPane(AnchorPane AP){
        this.pane=AP;
        pane.getChildren().add(super.getImageView());
    }

    public Island getCurrentIsland() {
        return this.currentIsland;
    }

    public void setCurrentIsland(Island CI){
        this.currentIsland=CI;
    }

    public void MoveTimeline(){
        Timeline tl=new Timeline();
        final Double[] total = {0.0};
        tl.setCycleCount(Animation.INDEFINITE);
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(4),event->{
            if(DoMove){
                this.LaunchSpeedX =300.0;
                double p=((this.LaunchSpeedX))/80;
                this.LaunchSpeedX -=1.0;
                this.IncreseX(p);
                total[0] +=p;
                if(total[0]>=125.0){
                    total[0]=0.0;
                    DoMove=false;
                    this.currentIsland=game.updateCurrentIsland();
                    System.out.println(this.currentIsland);
                }
            }
        }));
        tl.play();
    }

    public void move(){
        this.DoMove=true;
    }

    @Override
    public void run() {
        this.hop();
        this.MoveTimeline();
    }

    public void hop(){
        this.currentIsland=game.updateCurrentIsland();
        Timeline tl =new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(5), event->{
            if(this.IsHeroAboveIsland()){
                this.LaunchSpeedY =350.0;
            }
            double p=((this.LaunchSpeedY)-205)/100;
            this.LaunchSpeedY -=1.5;
            super.IncreseY(-p);
//            System.out.println(this.currentIsland.getyPositionTop()-this.getyPositionBottom());
        } ));
        tl.play();
    }

    private boolean IsHeroAboveIsland(){
        if(currentIsland==null)
            return false;
        return this.getyPositionBottom() > currentIsland.getyPositionTop();
    }

}
