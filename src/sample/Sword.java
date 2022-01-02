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
    private boolean fire=false;

    public Sword(ImageView image) {
        super(image);
    }

    @Override
    public void Useweapon() {
        this.fire=true;
    }

    @Override
    public void ShowIcon() {
        Game.Fade(SwordIcon,0.0,1.0,100,1);
        Game.Fade(SwordLabel,0.0,1.0,100,1);
        this.ActivateWeapon();
        SwordLabel.setText(super.level+"");
    }

    @Override
    public void ShowWeapon(){
        this.IncreseX(Game.hero.getxPositionLeft());
        this.IncreseY(Game.hero.getyPositionTop() + 40);
        Weapon.pane.getChildren().add(this.getImageView());
        SetupTimeline();
    }

    //How far the Sword will Attack
    private Double FireLength=300.0;
    @Override
    public void SetupTimeline() {
        Timeline tl=new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(6), event->{
            if(fire){
                super.getImageView().setRotate(super.getImageView().getRotate()+3);
                FireLength-=3.0;
                if(FireLength<0){
                    FireLength=300.0;
                    fire=false;
                    super.getImageView().setRotate(0);
                }
            }
            super.getImageView().setLayoutX(Game.hero.getxPositionLeft()-23);
            super.getImageView().setLayoutY(Game.hero.getyPositionTop()+25);
        } ));
        tl.play();
    }

    public static void setAttributes(ImageView SI,Label SL){
        Sword.SwordIcon=SI;
        Sword.SwordLabel=SL;
    }
}
