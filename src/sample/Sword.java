package sample;


import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public final class Sword extends Weapon {
    private static ImageView SwordIcon;
    private static Label SwordLabel;
    private boolean fire=false;

    public Sword(ImageView image) {
        super(image);
    }

    @Override
    public void Useweapon() {

    }

    @Override
    public void ShowIcon() {
        Game.Fade(SwordIcon,0.0,1.0,100,1);
        Game.Fade(SwordLabel,0.0,1.0,100,1);
        SwordLabel.setText(super.level+"");
    }

    @Override
    public void SetupTimeline() {

    }

    public static void setAttributes(ImageView SI,Label SL){
        Sword.SwordIcon=SI;
        Sword.SwordLabel=SL;
    }
}
