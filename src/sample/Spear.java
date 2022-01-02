package sample;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public final class Spear extends Weapon{
    private static ImageView SpearIcon;
    private static Label SpearLabel;
    private boolean fire=false;
    private static Timeline timeline;
    private boolean inUse=false;

    public Spear(ImageView image) {
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
            Game.Fade(SpearIcon, 0.0, 1.0, 100, 1);
            Game.Fade(SpearLabel, 0.0, 1.0, 100, 1);
        }
        this.ActivateWeapon();
        SpearLabel.setText(super.level+"");
    }

    @Override
    public void ShowWeapon(){
        if(super.IsWeaponActive) {
            this.addWeapon();
            return;
        }
        this.IncreseX(Game.hero.getxPositionLeft());
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

    //How far the Spade will move
    private Double FireLength=500.0;
    public void SetupTimeline() {
        timeline=new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(6), event->{
            if(inUse) {
                if (fire) {
                    super.IncreseX(5.0);
                    FireLength -= 5.0;
                    if (FireLength < 0) {
                        FireLength = 500.0;
                        fire = false;
                    }
                } else {
                    super.getImageView().setLayoutX(Game.hero.getxPositionLeft() + 12);
                    super.getImageView().setLayoutY(Game.hero.getyPositionTop() + 25);
                }
            }
        } ));
        timeline.play();
    }

    public static void setAttributes(ImageView SI,Label SL){
        Spear.SpearIcon=SI;
        Spear.SpearLabel=SL;
    }



}
