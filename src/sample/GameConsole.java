package sample;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameConsole implements Initializable {
    private static AnchorPane primaryPane;
    private static Stage stage;
    private Island H;
    private Game game;
    private BackgroundController backgroundController;
    private static AnchorPane gamePane;
    private static AnchorPane homePane;

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
    private AnchorPane LoadingPaneList;

    @FXML
    void ExitGame(MouseEvent event) {
        GameConsole.stage.close();
    }

    public static void setStage(Stage S){
        GameConsole.stage=S;
    }

    private static final String path=System.getProperty("user.dir")+"\\src\\sample\\files\\";
    @FXML
    void LoadGame(MouseEvent event) throws IOException {
        Loading.setparent(LoadingPaneList,this);
        Game.Translate(LoadingPaneList,0.0,450.0,500,-1);
        Loading.show();
    }

    @FXML
    void StartNewGame(MouseEvent event) {
        LoadNewGamePane();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.game=new Game();
        Game.setGameConsole(this);
        ResurgeHandler.setGameConsole(this);
        setBackgroundelements(1500,1);
    }

    private Double launchSpeed=350.0;
    private void DemoHeroHop(){
            Timeline tl=new Timeline();
            tl.setCycleCount(Animation.INDEFINITE);
            tl.getKeyFrames().add(new KeyFrame(Duration.millis(5), event->{
                if(H.getyPositionBottom() > -50)
                    this.launchSpeed =350.0;
                double p=((this.launchSpeed)-205)/100;
                this.launchSpeed -=1.5;
                H.IncreseY(-p);
            } ));
            tl.play();
    }


    private void setBackgroundelements(Integer duration, Integer way){
        Island I1=new Island(IslandMid, 100.0);
        Island I2=new Island(IslandRight,0.0);
        Island heroIsland = new Island(IslandHomePage, 10.0);
        Island exitButton=new Island(ExitButton,0.0);
        Island loadSavedButton=new Island(LoadSavedButton,0.0);
        H=new Island(hero,0.0);
        Island orcs=new Island(this.orcs,0.0);     //this is the orcs
        Translate(I1,0.0,-250.0,duration,way);
        Translate(I2,-200.0,0.0,duration,way);
        Translate(heroIsland,0.0,-250.0,duration,way);
        Translate(exitButton,100.0,0.0,duration,way);
        Translate(loadSavedButton,-100.0,0.0,duration,way);
        Translate(H,0.0,335.0,duration,way);
        Translate(orcs,0.0,320.0,duration,way);
        if(way==1){
            Fade(WillHeroName,0.0,1.0,duration);
            Fade(StartButton,0.0,1.0,duration);
        }
        else {
            Fade(WillHeroName,1.0,0.0,duration);
            Fade(StartButton,1.0,0.0,duration);
        }
        setMotion(I1,100.0);
        setMotion(orcs,100.0);
        setMotion(I2,50.0);
        DemoHeroHop();
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
            double p=((this.LaunchSpeed)-100)/100;
            gameobject.getImageView().setLayoutY(gameobject.getImageView().getLayoutY()-p);
        } ));
        tl.play();
    }

    public void LoadNewGamePane(){
        //Removed Game console Pane (HomePage)
        GameConsole.homePane=HomePage;
        primaryPane.getChildren().remove(HomePage);
        //Adding Game Pane
        try {
            gamePane =FXMLLoader.load(getClass().getResource("Game.fxml"));
            primaryPane.getChildren().add(gamePane);
        } catch (IOException e) {
            System.out.println("Error: loading Game.Fxml");
            e.printStackTrace();
        }
    }

    public void LoadGameConsole(){
        primaryPane.getChildren().remove(gamePane);
        primaryPane.getChildren().add(homePane);
    }

    public static void setPrimaryStage(AnchorPane AP){
        GameConsole.primaryPane=AP;
    }

    public void setBackgroundController(BackgroundController B){
        this.backgroundController=B;
        this.backgroundController.setGameConsole(this);
    }

    public void ReplayGame(){
        this.LoadGameConsole();
        LoadNewGamePane();
    }

}
