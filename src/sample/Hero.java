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
    private Timeline MoveTimeline;
    private final Helmet MyHelmet;
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
        this.MyHelmet=new Helmet();
    }

    public void increaseCoins(Integer I){
        this.Coins+=I;
        this.CoinLabel.setText(this.Coins+"");
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
        this.moveTimeline();
    }

    //To move if mouse clicked
    public void moveTimeline(){
        MoveTimeline=new Timeline();
        final Double[] total = {0.0};
        MoveTimeline.setCycleCount(Animation.INDEFINITE);
        MoveTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(3),event->{
            if(DoMove){
                HopTimeline.stop();
                double p=2;
                this.IncreseX(p);
                total[0] +=p;
                if(total[0]>=125.0){
                    total[0]=0.0;
                    DoMove=false;
                    if(!Eliminated)
                        this.currentIsland=game.updateCurrentIsland(this);
                    System.out.println(this.currentIsland);
                    HopTimeline.play();
                    this.PositionLabel.setText(this.Position+"");
                }
            }
        }));
        MoveTimeline.play();
    }

    public void move(){
        this.DoMove=true;
        this.Position++;
        this.MyHelmet.useWeapon();
    }

    public void hop(){
        this.currentIsland=game.updateCurrentIsland(this);
        HopTimeline =new Timeline();
        HopTimeline.setCycleCount(Animation.INDEFINITE);
        HopTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(6), event->{
            if(this.HeroHitIsland()){
                this.LaunchSpeedY =350.0;
            }
            double p=((this.LaunchSpeedY)-200)/100;
            this.LaunchSpeedY -=1.5;
            super.IncreseY(-p);
            if(this.getyPositionTop()>450){
                game.ProvideResurgeOptions();
                HopTimeline.stop();
                MoveTimeline.stop();
            }
        } ));
        HopTimeline.play();
    }

    private boolean HeroHitIsland(){
        if(currentIsland==null)
            return false;
        return this.getyPositionBottom() > currentIsland.getyPositionTop();
    }

    public Helmet getMyHelmet(){
        return this.MyHelmet;
    }

    public Integer getCoins(){
        return this.Coins;
    }

    private boolean Eliminated=false;
    public void EleminateHero(){
        Eliminated=true;
        this.currentIsland=null;
        Game.Fade(this.getImageView(),1.0,0.5,20,1);
    }

    public void SaveAttributes(SaveObject SO){
        SO.HeroPosition=this.Position;
        SO.HeroCoins=this.Coins;
        this.MyHelmet.SaveAttributes(SO);
    }

    public void LoadAttributes(SaveObject SO){
        this.currentIsland=game.updateCurrentIsland(this);
        this.Position=SO.HeroPosition;
        this.PositionLabel.setText(this.Position+"");
        this.increaseCoins(SO.HeroCoins);
        this.MyHelmet.LoadAttributes(SO);
    }

    public void ResurgeHero(){
        Game.Fade(this.getImageView(),0.5,1.0,20,1);
        this.getImageView().setLayoutY(200);
        this.IncreseX(30.0);
        this.currentIsland=game.updateCurrentIsland(this);
        this.LaunchSpeedY=350.0;
        this.LaunchSpeedX=350.0;
        Eliminated=false;
        HopTimeline.play();
        MoveTimeline.play();
    }
}
