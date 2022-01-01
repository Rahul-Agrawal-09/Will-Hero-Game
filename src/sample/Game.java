package sample;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
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


public final class Game implements Initializable {
    public volatile static Hero hero;
    public Thread[] threads=new Thread[7];
    private static final ArrayList<Double> xCoordinatesIsland =new ArrayList<>();
    private static final ArrayList<Double> yCoordinatesIsland =new ArrayList<>();
    private static final ArrayList<Integer> AllIslandNumbers=new ArrayList<>();
    private static final ArrayList<Island> AllIsland=new ArrayList<>();

    @FXML
    private AnchorPane AllIslandPane;
    @FXML
    private AnchorPane ViewIslandPane;
    @FXML
    private ImageView CloseGameButton;
    @FXML
    private ImageView SaveGameButton;
    @FXML
    private Label Score;
    @FXML
    private Label Position;
    @FXML
    private ImageView SettingIcon;

    @FXML
    void MoveHero(MouseEvent event) {
        hero.move();
//        AllIslandPane.setLayoutX(AllIslandPane.getLayoutX()-125);
//        hero.setCurrentIsland(this.updateCurrentIsland());
        MoveAllIslandPane(400);
    }

    private boolean settingOpened=false;
    @FXML
    void OpenSetting(MouseEvent event) {
        int way;
        if (!settingOpened)
            way=1;
        else
            way=-1;
        Translate(CloseGameButton,0.0,45.0,500,way);
        Translate(SaveGameButton,0.0,90.0,500,way);
        Fade(CloseGameButton,0.0,1.0,500,way);
        Fade(SaveGameButton,0.0,1.0,500,way);
        settingOpened=!settingOpened;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Translate(SettingIcon,0.0,70.0,500,1);
        Island.initialiseIslands(AllIslandPane);  // final
        addAllIsland(); //final
        PlaceIslands(); //final
        setupHero();    //final
    }

    private void MoveAllIslandPane(Integer moveBackTime){
        Game.Translate(AllIslandPane,-125.0,0.0,moveBackTime,1);
    }


    private Integer NITP;
    private void PlaceIslands(){
        //setting up the starting island and other elements
            try {
                for(NITP =0; NITP <5; NITP++)
                    PlaceIslandsHelper(NITP);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        Timeline tl=new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(10), event->{
            if((AllIsland.get(0).getxPositionRight()-hero.getxPositionLeft())<-150){
                try {
                    AllIsland.remove(0);
                    PlaceIslandsHelper(NITP);
                    NITP++;
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        } ));
        tl.play();
    }

    private void PlaceIslandsHelper(Integer i) throws CloneNotSupportedException {
        System.out.println(i+"");
        Island I=Island.islands.get(AllIslandNumbers.get(i)).clone();
        I.IncreseY(230.0);
        I.IncreseX(Game.xCoordinatesIsland.get(i));
        I.InitialiseIsland();
        AllIslandPane.getChildren().add(I.getImageView());
        AllIsland.add(I);
        if(Chest.ChestOnIsland.get(i)!=null)
            Chest.ChestOnIsland.get(i).AddChestToIsland(I,Chest.ChestPositionOffset.get(i));
    }

    private void setupHero(){
        hero=new Hero(setupHeroImage(),this);
        hero.setPane(AllIslandPane,Position);
        hero.getImageView().setLayoutX(200);
        hero.getImageView().setLayoutY(200);//Island.islands.get(AllIslandNumbers.get(0)).getyPositionTop()-hero.getImageHeight());
        hero.setCoinLabel(Score);
        Thread t1=new Thread(hero);
        t1.start();
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

    public static Island updateCurrentIsland(GameObject gameObject){
        for(Island I:AllIsland){
            if(gameObject.getxPositionRight()>I.getxPositionLeft() &&
                    (gameObject.getxPositionLeft()<I.getxPositionRight())){
                if(gameObject.getyPositionBottom()<I.getyPositionTop()){
                    return I;
                }
            }
        }
        return null;
    }

    public static void Translate(Node node,Double x,Double y, Integer time, Integer way) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(node);
        translate.setDuration(Duration.millis(time));
        translate.setByX(x*way);
        translate.setByY(y*way);
        translate.play();
    }

    public static void Fade(Node IV, Double from, Double to, Integer time, Integer way){
        FadeTransition fadeTransition=new FadeTransition(Duration.millis(time));
        fadeTransition.setNode(IV);
        if(way<0){
            Double temp=to;
            to=from;
            from=temp;
        }
        fadeTransition.setFromValue(from);
        fadeTransition.setToValue(to);
        fadeTransition.play();
    }

    public void AddChildren(GameObject GO){
        AllIslandPane.getChildren().add(GO.getImageView());
    }

    //Define all Islands of the Game
    private void addAllIsland(){ // all y 230.0 for now
        Game.xCoordinatesIsland.add(35.0);
        Game.AllIslandNumbers.add(6);
        Game.xCoordinatesIsland.add(400.0);
        Game.AllIslandNumbers.add(3);
        Game.xCoordinatesIsland.add(819.0);
        Game.AllIslandNumbers.add(0);
        Game.xCoordinatesIsland.add(1045.0);
        Game.AllIslandNumbers.add(4);
        Game.xCoordinatesIsland.add(1188.0);
        Game.AllIslandNumbers.add(8);
        Game.xCoordinatesIsland.add(1600.0);
        Game.AllIslandNumbers.add(2);
        Game.xCoordinatesIsland.add(1795.0);
        Game.AllIslandNumbers.add(9);
        Game.xCoordinatesIsland.add(2117.0);
        Game.AllIslandNumbers.add(4);
        Game.xCoordinatesIsland.add(2210.0);
        Game.AllIslandNumbers.add(5);
        Game.xCoordinatesIsland.add(2366.0);
        Game.AllIslandNumbers.add(1);
        Game.xCoordinatesIsland.add(2750.0);
        Game.AllIslandNumbers.add(3);
        Game.xCoordinatesIsland.add(3150.0);
        Game.AllIslandNumbers.add(7);
        Game.xCoordinatesIsland.add(3360.0);
        Game.AllIslandNumbers.add(0);
        Game.xCoordinatesIsland.add(3600.0);
        Game.AllIslandNumbers.add(3);
        Game.xCoordinatesIsland.add(3950.0);
        Game.AllIslandNumbers.add(9);
        Game.xCoordinatesIsland.add(4450.0);
        Game.AllIslandNumbers.add(7);
        Game.xCoordinatesIsland.add(4675.0);
        Game.AllIslandNumbers.add(3);
        Game.xCoordinatesIsland.add(5100.0);
        Game.AllIslandNumbers.add(1);
        Game.xCoordinatesIsland.add(5363.0);
        Game.AllIslandNumbers.add(6);
        Game.xCoordinatesIsland.add(5800.0);
        Game.AllIslandNumbers.add(5);
        Game.xCoordinatesIsland.add(5945.0);
        Game.AllIslandNumbers.add(7);
        Game.xCoordinatesIsland.add(6181.0);
        Game.AllIslandNumbers.add(0);
        Game.xCoordinatesIsland.add(6490.0);
        Game.AllIslandNumbers.add(1);
        Game.xCoordinatesIsland.add(6715.0);
        Game.AllIslandNumbers.add(8);
        Game.xCoordinatesIsland.add(7100.0);
        Game.AllIslandNumbers.add(4);
        Game.xCoordinatesIsland.add(7233.0);
        Game.AllIslandNumbers.add(7);
        Game.xCoordinatesIsland.add(7429.0);
        Game.AllIslandNumbers.add(9);
        Game.xCoordinatesIsland.add(7880.0);
        Game.AllIslandNumbers.add(5);
        Game.xCoordinatesIsland.add(8000.0);
        Game.AllIslandNumbers.add(4);
        Game.xCoordinatesIsland.add(8213.0);
        Game.AllIslandNumbers.add(0);
        Game.xCoordinatesIsland.add(8469.0);
        Game.AllIslandNumbers.add(2);
        Game.xCoordinatesIsland.add(8720.0);
        Game.AllIslandNumbers.add(8);
        Game.xCoordinatesIsland.add(9000.0);
        Game.AllIslandNumbers.add(5);
        Game.xCoordinatesIsland.add(9106.0);
        Game.AllIslandNumbers.add(8);
        Game.xCoordinatesIsland.add(9467.0);
        Game.AllIslandNumbers.add(9);
        Game.xCoordinatesIsland.add(9799.0);
        Game.AllIslandNumbers.add(1);
        Game.xCoordinatesIsland.add(10077.0);
        Game.AllIslandNumbers.add(8);
        Game.xCoordinatesIsland.add(10400.0);
        Game.AllIslandNumbers.add(6);
        Game.xCoordinatesIsland.add(10819.0);
        Game.AllIslandNumbers.add(0);
        Game.xCoordinatesIsland.add(11045.0);
        Game.AllIslandNumbers.add(4);
        Game.xCoordinatesIsland.add(11188.0);
        Game.AllIslandNumbers.add(8);
        Game.xCoordinatesIsland.add(11600.0);
        Game.AllIslandNumbers.add(2);
        Game.xCoordinatesIsland.add(11795.0);
        Game.AllIslandNumbers.add(9);
        Game.xCoordinatesIsland.add(12117.0);
        Game.AllIslandNumbers.add(4);
        Game.xCoordinatesIsland.add(12210.0);
        Game.AllIslandNumbers.add(5);
        Game.xCoordinatesIsland.add(12366.0);
        Game.AllIslandNumbers.add(1);
        Game.xCoordinatesIsland.add(12750.0);
        Game.AllIslandNumbers.add(3);
        Game.xCoordinatesIsland.add(13150.0);
        Game.AllIslandNumbers.add(7);
        Game.xCoordinatesIsland.add(13360.0);
        Game.AllIslandNumbers.add(0);
        Game.xCoordinatesIsland.add(13600.0);
        Game.AllIslandNumbers.add(3);
        Game.xCoordinatesIsland.add(13950.0);
        Game.AllIslandNumbers.add(9);
        Game.xCoordinatesIsland.add(14450.0);
        Game.AllIslandNumbers.add(7);
        Game.xCoordinatesIsland.add(14675.0);
        Game.AllIslandNumbers.add(3);
        Game.xCoordinatesIsland.add(15100.0);
        Game.AllIslandNumbers.add(1);
        Game.xCoordinatesIsland.add(15363.0);
        Game.AllIslandNumbers.add(6);
    }

}
