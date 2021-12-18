package sample;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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

    protected void setOrcIsland(Island island) {
        this.myIsland = island;
        this.IncreseX(island.getxPositionLeft());
        this.IncreseY(island.getyPositionTop()-this.getImageHeight());
        Orc.pane.getChildren().add(this.getImageView());
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


}
