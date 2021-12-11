package sample;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class Game implements Initializable {
    private Hero hero;
    private static ArrayList<Double> xCoordinates;
    private static ArrayList<Double> yCoordinates;
    private static ArrayList<Integer> AllIsland;
    @FXML
    private AnchorPane AllIslandPane;
    @FXML
    private AnchorPane ViewIslandPane;

    @FXML
    void MoveHero(MouseEvent event) {
        hero.move();
        AllIslandPane.setLayoutX(AllIslandPane.getLayoutX()-125);
//        hero.setCurrentIsland(this.updateCurrentIsland());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        xCoordinates=new ArrayList<>();
        yCoordinates=new ArrayList<>();
        AllIsland=new ArrayList<>();
        Island.setPane(AllIslandPane);  // final
        addAllIsland(); //final
        setupHero();
        setupHomePane();
    }



    private void setupHomePane(){
        //setup the starting island and other elements
        for(int i=0;i<2;i++){
            ImageView IV = Island.islands.get(AllIsland.get(i)).getImageView();
            IV.setLayoutY(230.0);
            IV.setLayoutX(Game.xCoordinates.get(i));
            AllIslandPane.getChildren().add(IV);
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
        hero.getImageView().setLayoutY(Island.islands.get(AllIsland.get(0)).getyPositionTop()-hero.getImageHeight());
        Thread t1=new Thread(hero);
        t1.start();
    }

    public Island updateCurrentIsland(){
        for(Node IV:AllIslandPane.getChildren()){
            if(hero.getxPositionLeft()>IV.getLayoutX() &&
                    (hero.getxPositionRight()<(IV.getLayoutX()+IV.getBoundsInLocal().getWidth()))){
                Island I=Island.getIsland((ImageView) IV);
                if(I!=null && hero.getyPositionBottom()<I.getyPositionTop())
                    return I;
            }
        }
        return null;
    }



    private void addAllIsland(){ // all y 230.0 for now
        Game.xCoordinates.add(35.0);
        Game.AllIsland.add(6);
        Game.xCoordinates.add(400.0);
        Game.AllIsland.add(3);
        Game.xCoordinates.add(819.0);
        Game.AllIsland.add(0);
        Game.xCoordinates.add(1045.0);
        Game.AllIsland.add(4);
        Game.xCoordinates.add(1188.0);
        Game.AllIsland.add(8);
        Game.xCoordinates.add(1600.0);
        Game.AllIsland.add(2);
        Game.xCoordinates.add(1795.0);
        Game.AllIsland.add(9);
        Game.xCoordinates.add(2117.0);
        Game.AllIsland.add(4);
        Game.xCoordinates.add(2210.0);
        Game.AllIsland.add(5);
        Game.xCoordinates.add(2366.0);
        Game.AllIsland.add(1);
        Game.xCoordinates.add(2750.0);
        Game.AllIsland.add(3);
        Game.xCoordinates.add(3150.0);
        Game.AllIsland.add(7);
        Game.xCoordinates.add(3360.0);
        Game.AllIsland.add(0);
        Game.xCoordinates.add(3600.0);
        Game.AllIsland.add(3);
        Game.xCoordinates.add(3950.0);
        Game.AllIsland.add(9);
        Game.xCoordinates.add(4450.0);
        Game.AllIsland.add(7);
        Game.xCoordinates.add(4675.0);
        Game.AllIsland.add(3);
        Game.xCoordinates.add(5100.0);
        Game.AllIsland.add(1);
        Game.xCoordinates.add(5363.0);
        Game.AllIsland.add(6);
        Game.xCoordinates.add(5800.0);
        Game.AllIsland.add(5);
        Game.xCoordinates.add(5945.0);
        Game.AllIsland.add(7);
        Game.xCoordinates.add(6181.0);
        Game.AllIsland.add(0);
        Game.xCoordinates.add(6490.0);
        Game.AllIsland.add(1);
        Game.xCoordinates.add(6715.0);
        Game.AllIsland.add(8);
        Game.xCoordinates.add(7100.0);
        Game.AllIsland.add(4);
        Game.xCoordinates.add(7233.0);
        Game.AllIsland.add(7);
        Game.xCoordinates.add(7429.0);
        Game.AllIsland.add(9);
        Game.xCoordinates.add(7880.0);
        Game.AllIsland.add(5);
        Game.xCoordinates.add(8000.0);
        Game.AllIsland.add(4);
        Game.xCoordinates.add(8213.0);
        Game.AllIsland.add(0);
        Game.xCoordinates.add(8469.0);
        Game.AllIsland.add(2);
        Game.xCoordinates.add(8720.0);
        Game.AllIsland.add(8);
        Game.xCoordinates.add(9000.0);
        Game.AllIsland.add(5);
        Game.xCoordinates.add(9106.0);
        Game.AllIsland.add(8);
        Game.xCoordinates.add(9467.0);
        Game.AllIsland.add(9);
        Game.xCoordinates.add(9799.0);
        Game.AllIsland.add(1);
        Game.xCoordinates.add(10077.0);
        Game.AllIsland.add(8);
        Game.xCoordinates.add(10400.0);
        Game.AllIsland.add(6);
        Game.xCoordinates.add(10819.0);
        Game.AllIsland.add(0);
        Game.xCoordinates.add(11045.0);
        Game.AllIsland.add(4);
        Game.xCoordinates.add(11188.0);
        Game.AllIsland.add(8);
        Game.xCoordinates.add(11600.0);
        Game.AllIsland.add(2);
        Game.xCoordinates.add(11795.0);
        Game.AllIsland.add(9);
        Game.xCoordinates.add(12117.0);
        Game.AllIsland.add(4);
        Game.xCoordinates.add(12210.0);
        Game.AllIsland.add(5);
        Game.xCoordinates.add(12366.0);
        Game.AllIsland.add(1);
        Game.xCoordinates.add(12750.0);
        Game.AllIsland.add(3);
        Game.xCoordinates.add(13150.0);
        Game.AllIsland.add(7);
        Game.xCoordinates.add(13360.0);
        Game.AllIsland.add(0);
        Game.xCoordinates.add(13600.0);
        Game.AllIsland.add(3);
        Game.xCoordinates.add(13950.0);
        Game.AllIsland.add(9);
        Game.xCoordinates.add(14450.0);
        Game.AllIsland.add(7);
        Game.xCoordinates.add(14675.0);
        Game.AllIsland.add(3);
        Game.xCoordinates.add(15100.0);
        Game.AllIsland.add(1);
        Game.xCoordinates.add(15363.0);
        Game.AllIsland.add(6);
    }

}
