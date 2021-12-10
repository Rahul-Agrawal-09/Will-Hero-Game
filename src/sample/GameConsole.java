package sample;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameConsole implements Initializable {
    private static AnchorPane primaryPane;

    @FXML
    private AnchorPane LoadingPane;
    @FXML
    private ImageView ExitButton;
    @FXML
    private ImageView hero;
    @FXML
    private AnchorPane HomePage;
    @FXML
    private ImageView IslandHomePage;
    @FXML
    private ImageView IslandMid;
    @FXML
    private ImageView IslandRight;
    @FXML
    private ImageView LoadSavedButton;
    @FXML
    private ImageView StartButton;
    @FXML
    private ImageView WillHeroName;
    @FXML
    private ImageView orcs;

    @FXML
    void ExitGame(MouseEvent event) {

    }

    @FXML
    void LoadGame(MouseEvent event) {

    }

    @FXML
    void StartNewGame(MouseEvent event) {
        Fade(LoadingPane.getChildren().get(0),0.0,1.0,1000);
        Fade(LoadingPane.getChildren().get(1),0.0,1.0,1000); // circle
        setupBackgroundelements(1000,-1);
//        sleep(5000);          //problem check sleep
        LoadNewGamePane();      //comment this to see loading page
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupBackgroundelements(1500,1);
    }

    private void setDemoHero(){

    }

    private void setupBackgroundelements(Integer duration, Integer way){
        Island I1=new Island(IslandMid, 100.0);
        Island I2=new Island(IslandRight,0.0);
        Island I3=new Island(IslandHomePage,50.0);
        Island exitButton=new Island(ExitButton,0.0);
        Island loadSavedButton=new Island(LoadSavedButton,0.0);
        Island H=new Island(hero,0.0);
        Island I=new Island(orcs,0.0);     //this is the orcs
        Translate(I1,0.0,-250.0,duration,way);
        Translate(I2,-200.0,0.0,duration,way);
        Translate(I3,0.0,-250.0,duration,way);
        Translate(exitButton,100.0,0.0,duration,way);
        Translate(loadSavedButton,-100.0,0.0,duration,way);
        Translate(H,0.0,335.0,duration,way);
        Translate(I,0.0,320.0,duration,way);
        if(way==1){
            Fade(WillHeroName,0.0,1.0,duration);
            Fade(StartButton,0.0,1.0,duration);
        }
        else {
            Fade(WillHeroName,1.0,0.0,duration);
            Fade(StartButton,1.0,0.0,duration);
        }
        setMotion(I1,40.0);
    }

    private void Fade(Node IV, Double from, Double to, Integer time){
        FadeTransition fadeTransition=new FadeTransition(Duration.millis(time));
        fadeTransition.setNode(IV);
        fadeTransition.setFromValue(from);
        fadeTransition.setToValue(to);
        fadeTransition.play();
    }

    private void Translate(GameObject gameobject,Double x,Double y, Integer time, Integer way) {
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(gameobject.getImageView());
        translate.setDuration(Duration.millis(time));
        translate.setByX(x*way);
        translate.setByY(y*way);
        translate.play();
    }

    private Double LaunchSpeed=150.0;
    private final Double SpeedDecrement=1.5;
    private void setMotion (GameObject gameobject, Double speed){
        Timeline tl=new Timeline();
        Double mean=gameobject.getyPositionBottom();
        tl.setCycleCount(Animation.INDEFINITE);
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(speed), event->{
            if(gameobject.getyPositionBottom()>mean){
                this.LaunchSpeed +=this.SpeedDecrement;
            }
            else {
                this.LaunchSpeed -=this.SpeedDecrement;
            }
            double p=((this.LaunchSpeed)-120)/100;
            gameobject.getImageView().setLayoutY(gameobject.getImageView().getLayoutY()-p);
        } ));
        tl.play();
    }

    private void LoadNewGamePane(){
        //Removed Game consol Pane (HomePage)
        primaryPane.getChildren().remove(HomePage);
        //Adding Game Pane
        try {
            AnchorPane game=FXMLLoader.load(getClass().getResource("Game.fxml"));
            primaryPane.getChildren().add(game);
        } catch (IOException e) {
            System.out.println("Error: loading Game.Fxml");
            e.printStackTrace();
        }
    }

    private void sleep(Integer duration){
        Timeline tl=new Timeline();
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(duration), event->{;}));
        tl.play();
    }

    public static void setPrimaryStage(AnchorPane AP){
        GameConsole.primaryPane=AP;
    }

}
