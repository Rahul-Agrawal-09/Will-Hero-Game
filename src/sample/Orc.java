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
import java.util.ArrayList;

public abstract class Orc extends GameObject implements Cloneable{
    //static variables-> define Orcs
    private static final String path=System.getProperty("user.dir")+"\\src\\sample\\assets\\";
    public static final ArrayList<ArrayList<Orc>> OrcOnIsland=new ArrayList<>();
    public static final ArrayList<ArrayList<Double>> OrcPositionOffset=new ArrayList<>();
    private static final ArrayList<GreenOrc> GreenOrcs= new ArrayList<>();
    private static final ArrayList<RedOrc> RedOrcs= new ArrayList<>();
    private static BossOrc Boss;
    protected static AnchorPane pane;
    protected Timeline pushOrcTimeline;
    protected Timeline hopOrcTimeline;
    private static Game game;
    //Instance variable->Specific to each object
    private Island myIsland;

    public abstract Integer getCoinsOnElimination();
    public abstract void ReducePower();
    public abstract void StartHop();

    public Orc(ImageView image) {
        super(image, 0.0);
    }

    protected void AddOrcToIsland(Island island, Double offset) {
        try {
            Orc orc= this.clone();
            orc.myIsland = island;
            orc.getImageView().setLayoutX(island.getxPositionLeft()+offset);
            orc.IncreseY(island.getyPositionTop()-orc.getImageHeight());
            Orc.pane.getChildren().add(orc.getImageView());
            orc.StartHop();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Orc clone() throws CloneNotSupportedException {
        return (Orc) super.clone();
    }

    public static void initialiseOrcs(AnchorPane AP) throws FileNotFoundException {
        Orc.pane=AP;
        for(int i = 1; i<=4; i++){
            FileInputStream input = new FileInputStream(Orc.path+"orcs"+i+".png");
            ImageView IV=new ImageView();
            IV.setImage(new Image(input));
            IV.setPreserveRatio(true);
            IV.setFitWidth(IV.getBoundsInLocal().getWidth()*0.414556962);
            if(i==4)
                Boss=new BossOrc(IV);
            else
                Orc.GreenOrcs.add(new GreenOrc(IV));
        }
        for(int i = 5; i<=6; i++){
            FileInputStream input = new FileInputStream(Orc.path+"orcs"+i+".png");
            ImageView IV=new ImageView();
            IV.setImage(new Image(input));
            IV.setPreserveRatio(true);
            IV.setFitWidth(IV.getBoundsInLocal().getWidth()*0.414556962);
            Orc.RedOrcs.add(new RedOrc(IV));
        }
    }

    public static void setGame(Game G){
        Orc.game=G;
    }

    private Double LaunchSpeedY;
    protected void HopOrc(Double speedX,Double speedY){
        LaunchSpeedY=speedY;
        hopOrcTimeline =new Timeline();
        hopOrcTimeline.setCycleCount(Animation.INDEFINITE);
        hopOrcTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(8), event->{
            if(this.OcsHitIsland()){
                this.LaunchSpeedY =speedY;
            }
            if(this.OrcPushedByHero())
                pushed=true;
            double p=((this.LaunchSpeedY)-150)/100;
            this.LaunchSpeedY -=1.5;
            super.IncreseY(-p);
            if(OrcEliminatesHero()) {
                Game.hero.EleminateHero();
                pushOrcTimeline.stop();
            }
        } ));
        hopOrcTimeline.play();
        PushOrcTimeline(speedX);
    }

    protected boolean pushed=false;
    private Double LaunchSpeedX;
    protected void PushOrcTimeline(Double speedX){
        LaunchSpeedX=speedX;
        pushOrcTimeline =new Timeline();
        pushOrcTimeline.setCycleCount(Animation.INDEFINITE);
        pushOrcTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(4), event->{
            if( pushed){
                double p = ((this.LaunchSpeedX)) / 120;
                this.LaunchSpeedX -= 3.0;
                this.IncreseX(p);
            }
            if(this.LaunchSpeedX<0){
                this.myIsland=game.updateCurrentIsland(this);
                System.out.println(this.myIsland);
                pushed=false;
                this.LaunchSpeedX=speedX;
            }
            if(this.getyPositionTop()>450)
                this.eliminateOrc();
            if(this.OrcHitByWeapon())
                this.ReducePower();
        } ));
        pushOrcTimeline.play();
    }

    protected boolean OcsHitIsland(){
        if(myIsland==null)
            return false;
        return this.getyPositionBottom() > myIsland.getyPositionTop();
    }

    protected boolean OrcPushedByHero(){
        return Game.hero.getyPositionBottom() > this.getyPositionTop() &&
                Game.hero.getyPositionTop() < this.getyPositionBottom() &&
                Game.hero.getxPositionRight() > this.getxPositionLeft() &&
                Game.hero.getxPositionLeft() < this.getxPositionRight();
    }

    public void eliminateOrc(){
        Game.hero.increaseCoins(this.getCoinsOnElimination());
        Orc.pane.getChildren().remove(this.getImageView());
        hopOrcTimeline.stop();
        pushOrcTimeline.stop();
        myIsland=null;
    }

    protected boolean OrcEliminatesHero(){
        return Game.hero.getxPositionRight()-this.getxPositionLeft()>8 &&
                this.getxPositionRight()-Game.hero.getxPositionLeft()>8 &&
                this.getyPositionBottom()-Game.hero.getyPositionTop()<2 &&
                this.getyPositionBottom()-Game.hero.getyPositionTop()>0;
    }

    private boolean OrcHitByWeapon(){
        Weapon weapon=Game.hero.getMyHelmet().getCurrentWeapon();
        if(weapon==null)
            return false;
        return weapon.getxPositionLeft() < this.getxPositionRight() &&
                weapon.getxPositionRight() > this.getxPositionLeft() &&
                weapon.getyPositionBottom() > this.getyPositionTop() &&
                weapon.getyPositionTop() < this.getyPositionBottom();
    }

    //Define all orcs of the Game
    public static void addAllOrc(){
        ArrayList<Orc> orcs;
        ArrayList<Double> offset;

        //for Island 0
        orcs=new ArrayList<>();
        offset=new ArrayList<>();
        orcs.add(Orc.GreenOrcs.get(0));
        offset.add(100.0);
        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 1
        orcs=new ArrayList<>();
        offset=new ArrayList<>();
        orcs.add(Orc.GreenOrcs.get(1));
        offset.add(60.0);
        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 2
        orcs=new ArrayList<>();
        offset=new ArrayList<>();
        orcs.add(Orc.RedOrcs.get(1));
        offset.add(20.0);
        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 3
        orcs=new ArrayList<>();
        offset=new ArrayList<>();
        orcs.add(Orc.GreenOrcs.get(0));
        offset.add(150.0);
        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 4
        orcs=new ArrayList<>();
        offset=new ArrayList<>();

        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 5
        orcs=new ArrayList<>();
        offset=new ArrayList<>();

        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 6
        orcs=new ArrayList<>();
        offset=new ArrayList<>();
//        orcs.add(Orc.GreenOrcs.get(0));
//        offset.add(150.0);
//        orcs.add(Orc.GreenOrcs.get(1));
//        offset.add(200.0);
        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 7
        orcs=new ArrayList<>();
        offset=new ArrayList<>();

        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 8
        orcs=new ArrayList<>();
        offset=new ArrayList<>();
        orcs.add(Orc.GreenOrcs.get(2));
        offset.add(20.0);
        orcs.add(Orc.RedOrcs.get(0));
        offset.add(180.0);
        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 9
        orcs=new ArrayList<>();
        offset=new ArrayList<>();
        orcs.add(Orc.GreenOrcs.get(1));
        offset.add(200.0);
        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 10 Boss Island
        orcs=new ArrayList<>();
        offset=new ArrayList<>();
        orcs.add(Boss);
        offset.add(100.0);
        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

    }

}
