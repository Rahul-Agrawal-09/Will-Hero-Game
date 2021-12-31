package sample;

import javafx.scene.image.ImageView;

public final class WeaponChest  extends Chest{
    private final Weapon weapon;

    public WeaponChest(ImageView image) {
        super(image);
//        if(Math.random()<0.5)
//            this.weapon=Helmet.sword;
//        else
            this.weapon=Helmet.spear;
    }

    @Override
    public void OpenChest(Hero hero) {
        weapon.ActivateWeapon();
        this.getImageView().setImage(Chest.WeaponChests.get(1).getImageView().getImage());
        this.getImageView().setFitWidth(this.getImageWidth()*1.1);
    }
}
