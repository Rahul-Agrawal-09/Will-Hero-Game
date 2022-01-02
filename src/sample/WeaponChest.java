package sample;

import javafx.scene.image.ImageView;

public final class WeaponChest  extends Chest{
    private final Weapon weapon;

    public WeaponChest(ImageView image) {
        super(image);
        if(Math.random()<0.5)
            this.weapon = Helmet.sword;
        else
            this.weapon = Helmet.spear;
    }

    @Override
    public void OpenChest(Hero hero) {
        hero.getMyHelmet().setCurrentWeapon(weapon);
        this.getImageView().setImage(Chest.WeaponChests.get(1).getImage());
        this.getImageView().setFitWidth(this.getImageWidth()*1.1);
    }
}
