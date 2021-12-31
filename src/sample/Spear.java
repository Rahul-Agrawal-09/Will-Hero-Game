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

    public Spear(ImageView image) {
        super(image);
    }

    @Override
    public void Useweapon() {
        this.fire=true;
    }

    @Override
    public void ShowIcon() {
        Game.Fade(SpearIcon,0.0,1.0,100,1);
        Game.Fade(SpearLabel,0.0,1.0,100,1);
        SpearLabel.setText(super.level+"");
        this.IncreseX(Game.hero.getxPositionLeft());
        this.IncreseY(Game.hero.getyPositionTop()+40);
        Weapon.pane.getChildren().add(this.getImageView());
        SetupTimeline();
    }

    private Double FireLength=200.0;
    @Override
    public void SetupTimeline() {
        Timeline tl=new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);
        tl.getKeyFrames().add(new KeyFrame(Duration.millis(6), event->{
            if(fire){
                super.IncreseX(5.0);
                FireLength-=5.0;
                if(FireLength<0){
                    FireLength=200.0;
                    fire=false;
                }
            }
            else {
                super.getImageView().setLayoutX(Game.hero.getxPositionLeft()+12);
                super.getImageView().setLayoutY(Game.hero.getyPositionTop()+25);
            }
        } ));
        tl.play();
    }

    public static void setAttributes(ImageView SI,Label SL){
        Spear.SpearIcon=SI;
        Spear.SpearLabel=SL;
    }



}
