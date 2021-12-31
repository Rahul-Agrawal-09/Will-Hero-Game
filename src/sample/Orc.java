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
    private static AnchorPane pane;

    //Instance variable->Specific to each object
    private Island myIsland;

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
            orc.HopOrc();
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
        for(int i = 1; i<=3; i++){
            FileInputStream input = new FileInputStream(Orc.path+"orcs"+i+".png");
            ImageView IV=new ImageView();
            IV.setImage(new Image(input));
            IV.setPreserveRatio(true);
            IV.setFitWidth(IV.getBoundsInLocal().getWidth()*0.414556962);
            Orc.GreenOrcs.add(new GreenOrc(IV));
        }
            //set red orcs here once added
    }

    private Double LaunchSpeedY=350.0;
    private void HopOrc(){
        Timeline tl =new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(6), event->{
            if(this.OcsHitIsland()){
                this.LaunchSpeedY =350.0;
            }
            if(this.OrcPushedByHero())
                pushed=true;
            double p=((this.LaunchSpeedY)-150)/100;
            this.LaunchSpeedY -=1.5;
            super.IncreseY(-p);
        } ));
        tl.play();
        PushOrcTimeline();
    }

    private boolean pushed=false;
    private Double LaunchSpeedX=350.0;
    private void PushOrcTimeline(){
        Timeline tl2 =new Timeline();
        tl2.setCycleCount(Animation.INDEFINITE);
        tl2.getKeyFrames().add(new KeyFrame(Duration.millis(6), event->{
            if(pushed){
                double p=((this.LaunchSpeedX))/120;
                this.LaunchSpeedX -=3.0;
                this.IncreseX(p);
            }
            if(this.LaunchSpeedX<0){
                this.myIsland=Game.updateCurrentIsland(this);
//                System.out.println(this.myIsland);
                pushed=false;
                this.LaunchSpeedX=350.0;
            }
        } ));
        tl2.play();
    }

    private boolean OcsHitIsland(){
        if(myIsland==null)
            return false;
        return this.getyPositionBottom() > myIsland.getyPositionTop();
    }

    private boolean OrcPushedByHero(){
        return Game.hero.getyPositionBottom() > this.getyPositionTop() &&
                Game.hero.getyPositionTop() < this.getyPositionBottom() &&
                Game.hero.getxPositionRight() > this.getxPositionLeft() &&
                Game.hero.getxPositionLeft() < this.getxPositionRight();
    }

    private boolean OrcAboveHero(){
        return false;
    }


    //Define all orcs of the Game
    public static void addAllOrc(){
        ArrayList<Orc> orcs;
        ArrayList<Double> offset;

        //for Island 0
        orcs=new ArrayList<>();
        offset=new ArrayList<>();

        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 1
        orcs=new ArrayList<>();
        offset=new ArrayList<>();

        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 2
        orcs=new ArrayList<>();
        offset=new ArrayList<>();

        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 3
        orcs=new ArrayList<>();
        offset=new ArrayList<>();
        orcs.add(Orc.GreenOrcs.get(0));
        offset.add(20.0);
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
        orcs.add(Orc.GreenOrcs.get(1));
        offset.add(200.0);
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

        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

        //for Island 9
        orcs=new ArrayList<>();
        offset=new ArrayList<>();

        OrcOnIsland.add(orcs);
        OrcPositionOffset.add(offset);

    }

}
