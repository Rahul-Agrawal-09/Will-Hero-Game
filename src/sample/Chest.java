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
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Chest extends GameObject implements Cloneable {
    private static final String path=System.getProperty("user.dir")+"\\src\\sample\\assets\\";
    protected static ArrayList<ImageView> CoinChests=new ArrayList<>();
    protected static ArrayList<ImageView> WeaponChests=new ArrayList<>();
    public static HashMap<Integer,Chest> ChestOnIsland =new HashMap<>();
    public static HashMap<Integer,Integer> ChestPositionOffset =new HashMap<>();
    private static AnchorPane pane;
    private Island myIsland;
    private boolean isOpened;

    public Chest(ImageView image) {
        super(image, 0.0);
        this.isOpened =false;
    }

    @Override
    public Chest clone() throws CloneNotSupportedException {
        return (Chest) super.clone();
    }

    //Place Chest on an Island
    protected void AddChestToIsland(Island island, Integer offset) {
        try {
            Chest chest= this.clone();
            chest.myIsland = island;
            chest.getImageView().setLayoutX(island.getxPositionLeft()+offset);
            chest.IncreseY(island.getyPositionTop()-chest.getImageHeight());
            Chest.pane.getChildren().add(chest.getImageView());
            chest.HeroHitTimeline();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    //prepares all images into ImageView
    public static void initialiseChests(AnchorPane AP) throws FileNotFoundException {
        Chest.pane=AP;
        //two coin chest (0 and 1) and two weapon cheat (2 and 3)
        for(int i = 1; i<=4; i++){
            FileInputStream input = new FileInputStream(Chest.path+"Chest"+i+".png");
            ImageView IV=new ImageView();
            IV.setImage(new Image(input));
            IV.setPreserveRatio(true);
            IV.setFitWidth(IV.getBoundsInLocal().getWidth()*0.37);
            if(i<=2)
                Chest.CoinChests.add(IV);
            else
                Chest.WeaponChests.add(IV);
        }
        Chest.addAllChest();
//        Chest.CoinChests.get(0).AddChestToIsland(AllIsland.get(0),250.0); //temp
    }

    public void HeroHitTimeline(){
        Timeline tl=new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(5), event->{
            if(!isOpened && HeroHit()){
                this.OpenChest(Game.hero);
                isOpened=true;
            }
        } ));
        tl.play();
    }

    private boolean HeroHit(){
        return Game.hero.getxPositionLeft()<this.getxPositionRight() &&
                Game.hero.getxPositionRight()>this.getxPositionLeft() &&
                 Game.hero.getyPositionTop()<this.getyPositionBottom() &&
                   Game.hero.getyPositionBottom()>this.getyPositionTop();
    }

    //Abstract Method
    public abstract void OpenChest(Hero hero);


    //Define all Chest of the Game
    public static void addAllChest(){
        //weapon Chests
        ChestOnIsland.put(1,new WeaponChest(WeaponChests.get(0)));
        ChestPositionOffset.put(1,200);
        ChestOnIsland.put(18,new WeaponChest(WeaponChests.get(0)));
        ChestPositionOffset.put(18,250);
        ChestOnIsland.put(37,new WeaponChest(WeaponChests.get(0)));
        ChestPositionOffset.put(37,100);

        //Coin Chest
        ChestOnIsland.put(11,new CoinChest(CoinChests.get(0)));
        ChestPositionOffset.put(11,10);
        ChestOnIsland.put(15,new CoinChest(CoinChests.get(0)));
        ChestPositionOffset.put(15,100);
        ChestOnIsland.put(20,new CoinChest(CoinChests.get(0)));
        ChestPositionOffset.put(20,50);
        ChestOnIsland.put(25,new CoinChest(CoinChests.get(0)));
        ChestPositionOffset.put(25,30);
        ChestOnIsland.put(47,new CoinChest(CoinChests.get(0)));
        ChestPositionOffset.put(47,80);
        ChestOnIsland.put(51,new CoinChest(CoinChests.get(0)));
        ChestPositionOffset.put(51,70);
    }
}
