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
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

public class Island extends GameObject{
    private static final String path=System.getProperty("user.dir")+"\\src\\sample\\assets\\";
    private static final HashMap<Integer,String> Name=new HashMap<>();
    private static final ArrayList<Double> IslandOffset =new ArrayList<>();
    private static AnchorPane pane;
    private Double LaunchSpeed=150.0;
    private Double SpeedDecrement=1.5;
    public static ArrayList<Island> islands= new ArrayList<>();

    public Island(ImageView image, Double offset){
        super(image,offset);
    }


    public static void setPane(AnchorPane AP){
        Island.pane=AP;
        Island.Name.put(0,"IslandBig.png"); IslandOffset.add(65.77);
        Island.Name.put(1,"IslandBig2.png"); IslandOffset.add(62.4);
        Island.Name.put(2,"IslandBlank.png"); IslandOffset.add(0.0);
        Island.Name.put(3,"IslandNoTree.png"); IslandOffset.add(0.0);
        Island.Name.put(4,"IslandSmall.png"); IslandOffset.add(35.83);
        Island.Name.put(5,"IslandSmallTree.png"); IslandOffset.add(44.26);
        Island.Name.put(6,"IslandWide.png"); IslandOffset.add(46.37);
        Island.Name.put(7,"IslandWideTree.png"); IslandOffset.add(60.28);
        Island.Name.put(8,"IslandNoTree2.png"); IslandOffset.add(0.0);
        Island.Name.put(9,"IslandWideNoTree.png"); IslandOffset.add(0.0);
        try {
            Island.setIslands();
        } catch (FileNotFoundException e) {
            System.out.println("Error: while setting Island Arraylist");
        }
//        Island.pane.getChildren().add(Island.islands.get(0).getImageView());
    }

    private static void setIslands() throws FileNotFoundException {
        for(int i = 0; i<Island.Name.size(); i++){
            FileInputStream input = new FileInputStream(Island.path+Island.Name.get(i));
            ImageView IV=new ImageView();
            IV.setImage(new Image(input));
            IV.setPreserveRatio(true);
            IV.setFitWidth(IV.getBoundsInLocal().getWidth()*0.414556962);
            System.out.println((int)IV.getFitWidth());
            Island.islands.add(new Island(IV,Island.IslandOffset.get(i)));
        }
    }

    public static Island getIsland(ImageView IV){
        for(Island I:Island.islands){
            if(I.getImageView().equals(IV))
                return I;
        }
        return null;
    }

    public void setMotion (Double speed){
        Timeline tl=new Timeline();
        Double mean=this.getyPositionBottom();
        tl.setCycleCount(Animation.INDEFINITE);
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(speed), event->{
            if(this.getyPositionBottom()>mean){
                this.LaunchSpeed +=this.SpeedDecrement;
            }
            else {
                this.LaunchSpeed -=this.SpeedDecrement;
            }
            double p=((this.LaunchSpeed)-120)/100;
            super.getImageView().setLayoutY(super.getImageView().getLayoutY()-p);
        } ));
        tl.play();
    }
}
