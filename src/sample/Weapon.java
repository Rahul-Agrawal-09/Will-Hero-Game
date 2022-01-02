package sample;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public abstract class Weapon extends GameObject {
    protected boolean IsWeaponActive=false;
    protected Integer level=0;

    public Weapon(ImageView image) {
        super(image, 0.0);
    }


    public void ActivateWeapon(){
        this.IsWeaponActive=true;
        this.level++;
        this.getImageView().setFitWidth(this.getImageView().getBoundsInLocal().getWidth()*(1+0.2*(this.level)));
    }

    public abstract void Useweapon();
    public abstract void addWeapon();
    public abstract void ShowIcon();
    public abstract void ShowWeapon();
    public abstract void removeWeapon();
}
