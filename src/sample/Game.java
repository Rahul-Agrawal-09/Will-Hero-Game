package sample;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;


public class Game implements Initializable {
    private Hero hero;
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
        hero=new Hero(setupHeroImage());
        hero.setPane(IslandPane);
        hero.getImageView().setLayoutX(200);
        hero.getImageView().setLayoutY(this.currentIsland.getyPositionTop()-hero.getImageHeight());
    }

    private void setupHomePane(){
        //setup the starting island and other elements
        this.currentIsland=Island.islands.get(6);
        ImageView IV = this.currentIsland.getImageView();
        IV.setLayoutY(230.0);
        IV.setLayoutX(35);
        IslandPane.getChildren().add(IV);

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
}
