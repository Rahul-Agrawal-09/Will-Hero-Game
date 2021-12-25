package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public final class Hero extends GameObject implements Runnable{
    private final Game game;
    private Island currentIsland;
    private Timeline HopTimeline;
    private Integer Position=0;
    private Integer Coins=0;
    private Label CoinLabel;
    private AnchorPane pane;
    private Label PositionLabel;
    private Double LaunchSpeedY;
    private Double LaunchSpeedX; //not used
    private boolean DoMove;

    public Hero(ImageView IV, Game game){
        super(IV,0.0);
        this.game=game;
        this.LaunchSpeedY=350.0;
        this.LaunchSpeedX=350.0;
        this.DoMove=false;
    }

    public void increaseCoins(Integer I){
        this.Coins+=I;
        this.CoinLabel.setText(this.Coins+"");
    }

    public void increasePosition(){

    }

    public void setPane(AnchorPane AP, Label position){
        this.pane=AP;
        pane.getChildren().add(super.getImageView());
        this.PositionLabel=position;
    }

    public Island getCurrentIsland() {
        return this.currentIsland;
    }

    public void setCurrentIsland(Island CI){
        this.currentIsland=CI;
    }

    public void setCoinLabel(Label l){
        this.CoinLabel=l;
    }

    @Override
    public void run() {
        this.hop();
        this.MoveTimeline();
    }

    //To move if mouse clicked
    public void MoveTimeline(){
        Timeline tl=new Timeline();
        final Double[] total = {0.0};
        tl.setCycleCount(Animation.INDEFINITE);
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(3),event->{
            if(DoMove){
//                this.LaunchSpeedX =300.0;
//                double p=((this.LaunchSpeedX))/80;
//                this.LaunchSpeedX -=1.0;
                HopTimeline.stop();
                double p=2;
                this.IncreseX(p);
                total[0] +=p;
                if(total[0]>=125.0){
                    total[0]=0.0;
                    DoMove=false;
                    this.currentIsland=Game.updateCurrentIsland(this);
                    System.out.println(this.currentIsland);
                    HopTimeline.play();
                    this.PositionLabel.setText(this.Position+"");
                }
            }
        }));
        tl.play();
    }

    public void move(){
        this.DoMove=true;
        this.Position++;
    }

    public void hop(){
        this.currentIsland=Game.updateCurrentIsland(this);
        HopTimeline =new Timeline();
        HopTimeline.setCycleCount(Animation.INDEFINITE);
        HopTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(6), event->{
            if(this.HeroHitIsland()){
                this.LaunchSpeedY =350.0;
            }
            double p=((this.LaunchSpeedY)-200)/100;
            this.LaunchSpeedY -=1.5;
            super.IncreseY(-p);
        } ));
        HopTimeline.play();
    }

    private boolean HeroHitIsland(){
        if(currentIsland==null)
            return false;
        return this.getyPositionBottom() > currentIsland.getyPositionTop();
    }

}
