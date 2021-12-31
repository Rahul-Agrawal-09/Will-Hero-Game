package sample;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public abstract class Weapon extends GameObject {
    protected boolean IsWeaponActive=false;
    protected static AnchorPane pane;
    protected Integer level=0;


    public Weapon(ImageView image) {
        super(image, 0.0);
    }


    public void ActivateWeapon(){
        this.IsWeaponActive=true;
        this.level++;
        ShowIcon();
    }

    public static void setWeaponPane(AnchorPane AP){
        Weapon.pane=AP;
    }

    public abstract void Useweapon();
    public abstract void ShowIcon();
    public abstract void SetupTimeline();
}
