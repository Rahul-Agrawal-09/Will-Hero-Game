package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class Orc extends GameObject implements Cloneable{
    private static final String path=System.getProperty("user.dir")+"\\src\\sample\\assets\\";
    public static ArrayList<GreenOrc> GreenOrcs= new ArrayList<>();
    public static ArrayList<RedOrc> RedOrcs= new ArrayList<>();
    private static AnchorPane pane;
    private Island myIsland;

    public Orc(ImageView image) {
        super(image, 0.0);
    }

    protected void AddOrcToIsland(Island island) {
        try {
            Orc orc= this.clone();
            orc.myIsland = island;
            orc.getImageView().setLayoutX(island.getxPositionLeft());
            orc.IncreseY(island.getyPositionTop()-orc.getImageHeight());
            Orc.pane.getChildren().add(orc.getImageView());
            orc.HopOrc();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Orc clone() throws CloneNotSupportedException {
        Orc clone=(Orc) super.clone();
//        clone.getImageView().setLayoutX(clone.myIsland.getxPositionLeft());
        return clone;
    }

    public static void initialiseOrcs(AnchorPane AP){
        Orc.pane=AP;
        try{
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
        catch (FileNotFoundException e){
            System.out.println("Error: Orcs Image Not found");
        }
    }

    private Double LaunchSpeedY=350.0;
    private void HopOrc(){
        Timeline tl =new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(5), event->{
            if(this.OcsHitIsland()){
                this.LaunchSpeedY =350.0;
            }
            double p=((this.LaunchSpeedY)-190)/100;
            this.LaunchSpeedY -=1.5;
            super.IncreseY(-p);
        } ));
        tl.play();
    }

    private boolean OcsHitIsland(){
        if(myIsland==null)
            return false;
        return this.getyPositionBottom() > myIsland.getyPositionTop();
    }


}
