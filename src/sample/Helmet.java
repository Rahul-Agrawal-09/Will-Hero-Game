package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

public class Helmet implements Initializable {
    private static final String path=System.getProperty("user.dir")+"\\src\\sample\\assets\\";
    public static ImageView SwordIV;
    public static ImageView SpearIV;
    public static Sword sword;
    public static Spear spear;
    private Weapon CurrentWeapon;
    protected static AnchorPane pane;

    @FXML
    private ImageView SpearIcon;

    @FXML
    private Label SpearLabel;

    @FXML
    private ImageView SwordIcon;

    @FXML
    private Label SwordLabel;

    @FXML
    void SelectSpear(MouseEvent event) {
        this.ChangeWeapon(Helmet.spear);
    }

    @FXML
    void SelectSword(MouseEvent event) {
        this.ChangeWeapon(Helmet.sword);
    }

    public Helmet(){
        this.CurrentWeapon=null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            SpearIV=new ImageView();
            SpearIV.setImage(new Image(new FileInputStream(Helmet.path+"spear.png")));
            SpearIV.setPreserveRatio(true);
            SpearIV.setFitWidth(SpearIV.getBoundsInLocal().getWidth()*0.414556962);

            SwordIV=new ImageView();
            SwordIV.setImage(new Image(new FileInputStream(Helmet.path+"sword.png")));
            SwordIV.setPreserveRatio(true);
            SwordIV.setFitWidth(SwordIV.getBoundsInLocal().getWidth()*0.18);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error: getting image of weapons");
        }
        Helmet.spear=new Spear(SpearIV);
        Helmet.sword=new Sword(SwordIV);
        Spear.setAttributes(SpearIcon,SpearLabel);
        Sword.setAttributes(SwordIcon,SwordLabel);
    }

    public void useWeapon(){
        if(CurrentWeapon!=null && CurrentWeapon.IsWeaponActive)
            CurrentWeapon.Useweapon();
    }


    public void ChangeWeapon(Weapon weapon){
        if(CurrentWeapon!=null) {
            CurrentWeapon.removeWeapon();
        }
        this.CurrentWeapon=weapon;
        this.CurrentWeapon.addWeapon();
    }

    public void setCurrentWeapon(Weapon weapon){
        if(CurrentWeapon!=null) {
            CurrentWeapon.removeWeapon();
        }
        this.CurrentWeapon=weapon;
        System.out.println("\n"+this.CurrentWeapon+"\n");
        this.CurrentWeapon.ShowWeapon();
        this.CurrentWeapon.ShowIcon();
    }

    public Weapon getCurrentWeapon(){
        return this.CurrentWeapon;
    }

    public static void setWeaponPane(AnchorPane AP){
        Helmet.pane=AP;
    }

    public void SaveAttributes(SaveObject SO){
        SO.SwordLevel=Helmet.sword.level;
        SO.SpearLevel=Helmet.spear.level;
        if(this.CurrentWeapon instanceof Sword)
            SO.CurrentWeapon="Sword";
        else
            SO.CurrentWeapon="Spear";
    }

    public void LoadAttributes(SaveObject SO){
        if(SO.CurrentWeapon.equals("Sword")){
            for(int i=0;i<SO.SpearLevel;i++)
                this.setCurrentWeapon(Helmet.spear);
            for(int i=0;i<SO.SwordLevel;i++)
                this.setCurrentWeapon(Helmet.sword);
        }
        else {
            for(int i=0;i<SO.SwordLevel;i++)
                this.setCurrentWeapon(Helmet.sword);
            for(int i=0;i<SO.SpearLevel;i++)
                this.setCurrentWeapon(Helmet.spear);
        }
    }
}
