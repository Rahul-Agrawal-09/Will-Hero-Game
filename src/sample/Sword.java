package sample;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public final class Sword extends Weapon {
    private static ImageView SwordIcon;
    private static Label SwordLabel;
    private static Timeline timeline;
    private boolean fire=false;
    private boolean inUse=false;

    public Sword(ImageView image) {
        super(image);
        SetupTimeline();
    }

    @Override
    public void Useweapon() {
        this.fire=true;
    }

    @Override
    public void ShowIcon() {
        if(!super.IsWeaponActive) {
            Game.Fade(SwordIcon, 0.0, 1.0, 100, 1);
            Game.Fade(SwordLabel, 0.0, 1.0, 100, 1);
        }
        this.ActivateWeapon();
        SwordLabel.setText(super.level+"");
    }

    @Override
    public void ShowWeapon(){
        if(super.IsWeaponActive) {
            this.addWeapon();
            return;
        }
        this.IncreseX(Game.hero.getxPositionLeft()-5);
        this.IncreseY(Game.hero.getyPositionTop() + 40);
        Helmet.pane.getChildren().add(this.getImageView());
        inUse=true;
    }

    @Override
    public void addWeapon(){
        Game.Fade(this.getImageView(),0.0,1.0,10,1);
        inUse=true;
    }

    @Override
    public void removeWeapon(){
        Game.Fade(this.getImageView(),1.0,0.0,10,1);
        inUse=false;
    }

    //How far the Sword will Attack
    private Double FireLength=300.0;

    public void SetupTimeline() {
        timeline=new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(6), event->{
            if(inUse) {
                if (fire) {
                    super.getImageView().setRotate(super.getImageView().getRotate() + 3);
                    FireLength -= 3.0;
                    if (FireLength < 0) {
                        FireLength = 300.0;
                        fire = false;
                        super.getImageView().setRotate(0);
                    }
                }
                super.getImageView().setLayoutX(Game.hero.getxPositionLeft() - 23);
                super.getImageView().setLayoutY(Game.hero.getyPositionTop() + 25);
            }
        } ));
        timeline.play();
    }

    public static void setAttributes(ImageView SI,Label SL){
        Sword.SwordIcon=SI;
        Sword.SwordLabel=SL;
    }
}
