package sample;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Game implements Initializable {
    private Hero hero;
    private static ArrayList<Double> xCoordinates;
    private static ArrayList<Double> yCoordinates;
    private static ArrayList<Integer> AllIslandNumbers;
    private static ArrayList<Island> AllIsland;

    @FXML
    private AnchorPane AllIslandPane;
    @FXML
    private AnchorPane ViewIslandPane;
    @FXML
    private ImageView CloseGameButton;
    @FXML
    private ImageView SaveGameButton;

    @FXML
    void MoveHero(MouseEvent event) {
        hero.move();
//        AllIslandPane.setLayoutX(AllIslandPane.getLayoutX()-125);
//        hero.setCurrentIsland(this.updateCurrentIsland());
        MoveAllIslandPane(400);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        xCoordinates=new ArrayList<>();
        yCoordinates=new ArrayList<>();
        AllIslandNumbers =new ArrayList<>();
        AllIsland=new ArrayList<>();
        Translate(CloseGameButton,0.0,70.0,500,1);
        Translate(SaveGameButton,0.0,70.0,500,1);
        Island.setPane(AllIslandPane);  // final
        addAllIsland(); //final
        setupHomePane();
        setupHero();    //final
    }

    private void MoveAllIslandPane(Integer moveBackTime){
        this.Translate(AllIslandPane,-125.0,0.0,moveBackTime,1);
    }


    private void setupHomePane(){
        //setup the starting island and other elements
            try {
                for(int i=0;i<10;i++){
                    Island I=Island.islands.get(AllIslandNumbers.get(i)).clone();
                    ImageView IV = I.getImageView();
                    IV.setLayoutY(230.0);
                    IV.setLayoutX(Game.xCoordinates.get(i));
                    AllIslandPane.getChildren().add(IV);
                    AllIsland.add(I);
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
    }

    private ImageView setupHeroImage(){
        FileInputStream input;
        try {
            input = new FileInputStream(System.getProperty("user.dir")+"\\src\\sample\\assets\\Hero.png");
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

    private void setupHero(){
        hero=new Hero(setupHeroImage(),this);
        hero.setPane(AllIslandPane);
        hero.getImageView().setLayoutX(200);
        hero.getImageView().setLayoutY(Island.islands.get(AllIslandNumbers.get(0)).getyPositionTop()-hero.getImageHeight());
        Thread t1=new Thread(hero);
//        hero.hop();
//        hero.MoveTimeline();
        t1.start();
    }

    public Island updateCurrentIsland(){
        for(Island I:AllIsland){
            if(hero.getxPositionRight()>I.getxPositionLeft() &&
                    (hero.getxPositionLeft()<I.getxPositionRight())){
                if(hero.getyPositionBottom()<I.getyPositionTop()){
                    return I;
                }
            }
//            System.out.println(I.getxPositionLeft()+"    "+I.getxPositionRight()+"   "+hero.getxPositionRight());
        }
        return null;
    }

    private void Translate(Node node,Double x,Double y, Integer time, Integer way) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(node);
        translate.setDuration(Duration.millis(time));
        translate.setByX(x*way);
        translate.setByY(y*way);
        translate.play();
    }

    private void addAllIsland(){ // all y 230.0 for now
        Game.xCoordinates.add(35.0);
        Game.AllIslandNumbers.add(6);
        Game.xCoordinates.add(400.0);
        Game.AllIslandNumbers.add(3);
        Game.xCoordinates.add(819.0);
        Game.AllIslandNumbers.add(0);
        Game.xCoordinates.add(1045.0);
        Game.AllIslandNumbers.add(4);
        Game.xCoordinates.add(1188.0);
        Game.AllIslandNumbers.add(8);
        Game.xCoordinates.add(1600.0);
        Game.AllIslandNumbers.add(2);
        Game.xCoordinates.add(1795.0);
        Game.AllIslandNumbers.add(9);
        Game.xCoordinates.add(2117.0);
        Game.AllIslandNumbers.add(4);
        Game.xCoordinates.add(2210.0);
        Game.AllIslandNumbers.add(5);
        Game.xCoordinates.add(2366.0);
        Game.AllIslandNumbers.add(1);
        Game.xCoordinates.add(2750.0);
        Game.AllIslandNumbers.add(3);
        Game.xCoordinates.add(3150.0);
        Game.AllIslandNumbers.add(7);
        Game.xCoordinates.add(3360.0);
        Game.AllIslandNumbers.add(0);
        Game.xCoordinates.add(3600.0);
        Game.AllIslandNumbers.add(3);
        Game.xCoordinates.add(3950.0);
        Game.AllIslandNumbers.add(9);
        Game.xCoordinates.add(4450.0);
        Game.AllIslandNumbers.add(7);
        Game.xCoordinates.add(4675.0);
        Game.AllIslandNumbers.add(3);
        Game.xCoordinates.add(5100.0);
        Game.AllIslandNumbers.add(1);
        Game.xCoordinates.add(5363.0);
        Game.AllIslandNumbers.add(6);
        Game.xCoordinates.add(5800.0);
        Game.AllIslandNumbers.add(5);
        Game.xCoordinates.add(5945.0);
        Game.AllIslandNumbers.add(7);
        Game.xCoordinates.add(6181.0);
        Game.AllIslandNumbers.add(0);
        Game.xCoordinates.add(6490.0);
        Game.AllIslandNumbers.add(1);
        Game.xCoordinates.add(6715.0);
        Game.AllIslandNumbers.add(8);
        Game.xCoordinates.add(7100.0);
        Game.AllIslandNumbers.add(4);
        Game.xCoordinates.add(7233.0);
        Game.AllIslandNumbers.add(7);
        Game.xCoordinates.add(7429.0);
        Game.AllIslandNumbers.add(9);
        Game.xCoordinates.add(7880.0);
        Game.AllIslandNumbers.add(5);
        Game.xCoordinates.add(8000.0);
        Game.AllIslandNumbers.add(4);
        Game.xCoordinates.add(8213.0);
        Game.AllIslandNumbers.add(0);
        Game.xCoordinates.add(8469.0);
        Game.AllIslandNumbers.add(2);
        Game.xCoordinates.add(8720.0);
        Game.AllIslandNumbers.add(8);
        Game.xCoordinates.add(9000.0);
        Game.AllIslandNumbers.add(5);
        Game.xCoordinates.add(9106.0);
        Game.AllIslandNumbers.add(8);
        Game.xCoordinates.add(9467.0);
        Game.AllIslandNumbers.add(9);
        Game.xCoordinates.add(9799.0);
        Game.AllIslandNumbers.add(1);
        Game.xCoordinates.add(10077.0);
        Game.AllIslandNumbers.add(8);
        Game.xCoordinates.add(10400.0);
        Game.AllIslandNumbers.add(6);
        Game.xCoordinates.add(10819.0);
        Game.AllIslandNumbers.add(0);
        Game.xCoordinates.add(11045.0);
        Game.AllIslandNumbers.add(4);
        Game.xCoordinates.add(11188.0);
        Game.AllIslandNumbers.add(8);
        Game.xCoordinates.add(11600.0);
        Game.AllIslandNumbers.add(2);
        Game.xCoordinates.add(11795.0);
        Game.AllIslandNumbers.add(9);
        Game.xCoordinates.add(12117.0);
        Game.AllIslandNumbers.add(4);
        Game.xCoordinates.add(12210.0);
        Game.AllIslandNumbers.add(5);
        Game.xCoordinates.add(12366.0);
        Game.AllIslandNumbers.add(1);
        Game.xCoordinates.add(12750.0);
        Game.AllIslandNumbers.add(3);
        Game.xCoordinates.add(13150.0);
        Game.AllIslandNumbers.add(7);
        Game.xCoordinates.add(13360.0);
        Game.AllIslandNumbers.add(0);
        Game.xCoordinates.add(13600.0);
        Game.AllIslandNumbers.add(3);
        Game.xCoordinates.add(13950.0);
        Game.AllIslandNumbers.add(9);
        Game.xCoordinates.add(14450.0);
        Game.AllIslandNumbers.add(7);
        Game.xCoordinates.add(14675.0);
        Game.AllIslandNumbers.add(3);
        Game.xCoordinates.add(15100.0);
        Game.AllIslandNumbers.add(1);
        Game.xCoordinates.add(15363.0);
        Game.AllIslandNumbers.add(6);
    }

}
