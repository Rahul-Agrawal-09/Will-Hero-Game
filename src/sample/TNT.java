package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class TNT extends GameObject implements Obstacle,Cloneable{
    private static TNT tnt;
    private static AnchorPane pane;
    private Timeline BlastTimeline;
    private Integer TimetoBlast;
    public static HashMap<Integer,Double> Placement=new HashMap<>();

    public TNT(ImageView image) {
        super(image, 0.0);
        this.isActivated=false;
        this.TimetoBlast=3000;//i.e. 3 seconds
    }

    @Override
    public TNT clone() throws CloneNotSupportedException {
        TNT clone=(TNT) super.clone();
        clone.isActivated=false;
        clone.TimetoBlast=3000;
        clone.setupTimeline();
        return clone;
    }

    private static void setupTNT(){
        tnt=new TNT(setupTNTImage());
    }

    private boolean isActivated;
    private void setupTimeline(){
        BlastTimeline=new Timeline();
        final Double[] total = {0.0};
        BlastTimeline.setCycleCount(Animation.INDEFINITE);
        BlastTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(10), event->{
            if(HasColided())
                ActivateObstacle();
            if(isActivated){
                TimetoBlast=TimetoBlast-10;
                if(TimetoBlast<=0)
                    this.Blast();
            }
        }));
        BlastTimeline.play();
    }

    private static ImageView setupTNTImage(){
        FileInputStream input;
        try {
            input = new FileInputStream(System.getProperty("user.dir")+"\\src\\sample\\assets\\TNT.png");
            javafx.scene.image.ImageView IV=new javafx.scene.image.ImageView();
            IV.setImage(new Image(input));
            IV.setPreserveRatio(true);
            IV.setFitHeight(37.5);
            return IV;
        } catch (FileNotFoundException e) {
            System.out.println("Error: getting Hero Image");
            return null;
        }
    }

    private void Blast(){
        this.getImageView().preserveRatioProperty();
        ScaleTransition ST=new ScaleTransition();
        ST.setNode(this.getImageView());
        ST.setDuration(Duration.millis(1000));
        ST.setByX(200);
        ST.setByY(200);
        ST.play();
        pane.getChildren().remove(this.getImageView());
        if(inRange())
            Game.hero.EleminateHero();
    }

    public static void PlaceTNT(Island I) throws CloneNotSupportedException {
        TNT tnt=TNT.tnt.clone();
        pane.getChildren().add(tnt.getImageView());
        tnt.IncreseY(I.getyPositionTop()-tnt.getImageWidth());
        tnt.IncreseX(I.getxPositionLeft()+50);
    }


    public static void setpane(AnchorPane AP){
        TNT.pane=AP;
        setupTNT();
    }

    @Override
    public void ActivateObstacle() {
        isActivated=true;
    }

    @Override
    public boolean HasColided() {
        if(Game.hero.getxPositionLeft()<this.getxPositionRight() &&
            Game.hero.getxPositionRight()>this.getxPositionLeft() &&
                Game.hero.getyPositionTop()<this.getyPositionBottom() &&
                    Game.hero.getyPositionBottom()>this.getyPositionTop())
            return true;
        return false;
    }

    @Override
    public boolean inRange() {
        if(Game.hero.getxPositionLeft()<this.getxPositionRight() &&
                Game.hero.getxPositionRight()>this.getxPositionLeft() &&
                Game.hero.getyPositionTop()<this.getyPositionBottom() &&
                Game.hero.getyPositionBottom()>this.getyPositionTop())
            return true;
        return false;
    }


    public static void setPlacement(){
        TNT.Placement.put(1,100.0);
    }
}
