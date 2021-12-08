package sample;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Game implements Initializable {
    private Hero hero;
    private static ArrayList<Double> xCoordinates;
    private static ArrayList<Double> yCoordinates;
    private static ArrayList<Integer> AllIsland;
    private Island currentIsland;
    @FXML
    private AnchorPane FirstIslandPane;
    @FXML
    private AnchorPane IslandPane;
    @FXML
    private AnchorPane SecondIslandPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Island.setPane(SecondIslandPane);
        setupHomePane();
        hero=new Hero(setupHeroImage(),this);
        hero.setPane(IslandPane);
        hero.getImageView().setLayoutX(200);
        hero.getImageView().setLayoutY(this.currentIsland.getyPositionTop()-hero.getImageHeight());
        Thread t1=new Thread(hero);
        t1.start();
//        Game.AllIsland.add(6);
//        Game.xCoordinates.add(50.0);
//        Game.yCoordinates.add(500.0);
    }

    private void setupHomePane(){
        //setup the starting island and other elements
        this.currentIsland=Island.islands.get(6);
        ImageView IV = currentIsland.getImageView();
        IV.setLayoutY(230.0);
        IV.setLayoutX(35.0);
        FirstIslandPane.getChildren().add(IV);
    }

    private ImageView setupHeroImage(){
        FileInputStream input = null;
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

    private Island updateCurrentIsland(){
        for(Node IV:FirstIslandPane.getChildren()){
            if(hero.getxPositionLeft()>IV.getLayoutX() &&
                    (hero.getxPositionRight()<(IV.getLayoutX()+IV.getBoundsInLocal().getWidth()))){
                return Island.getIsland((ImageView) IV);
            }
        }
        return null;
    }

    public boolean IsHeroAboveIsland(){
        this.currentIsland=updateCurrentIsland();
        if(currentIsland==null)
            return false;
        return hero.getyPositionBottom() > currentIsland.getyPositionTop();
    }
}
