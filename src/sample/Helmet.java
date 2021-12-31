package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Helmet implements Initializable {
    private static final String path=System.getProperty("user.dir")+"\\src\\sample\\assets\\";
    public static Sword sword;
    public static Spear spear;

    @FXML
    private ImageView SpearIcon;

    @FXML
    private Label SpearLabel;

    @FXML
    private ImageView SwordIcon;

    @FXML
    private Label SwordLabel;

    public Helmet(){
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ImageView IV;
        try{
            IV=new ImageView();
            IV.setImage(new Image(new FileInputStream(Helmet.path+"spear.png")));
            IV.setPreserveRatio(true);
            IV.setFitWidth(IV.getBoundsInLocal().getWidth()*0.414556962);
            Helmet.spear=new Spear(IV);

            IV=new ImageView();
            IV.setImage(new Image(new FileInputStream(Helmet.path+"sword.png")));
            IV.setPreserveRatio(true);
            IV.setFitWidth(IV.getBoundsInLocal().getWidth()*0.414556962);
            Helmet.sword=new Sword(IV);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error: getting image of weapons");
        }
        Spear.setAttributes(SpearIcon,SpearLabel);
        Sword.setAttributes(SwordIcon,SwordLabel);
    }

    public void useSword(){
        if(Helmet.spear.IsWeaponActive)
            Helmet.spear.Useweapon();
    }
}
