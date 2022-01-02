package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BossOrc extends Orc{
    private Integer Power=800;
    private Integer CoinsOnElimination=100;

    public BossOrc(ImageView image) {
        super(image);
    }

    @Override
    public Integer getCoinsOnElimination(){
        return this.CoinsOnElimination;
    }

    @Override
    public void ReducePower() {
        Power--;
        if(Power<=0)
            this.eliminateOrc();
    }

    @Override
    public void StartHop() {
        this.HopOrc(150.0,300.0);
    }

    @Override
    public void eliminateOrc(){
        super.eliminateOrc();
        Game.hero.setPushedByBoss(false);
        Game.hero.DecareWinner();
    }

    private Double LaunchSpeedY;
    @Override
    protected void HopOrc(Double speedX,Double speedY){
        LaunchSpeedY=speedY;
        hopOrcTimeline =new Timeline();
        hopOrcTimeline.setCycleCount(Animation.INDEFINITE);
        hopOrcTimeline.getKeyFrames().add(new KeyFrame(Duration.millis(8), event->{
            if(this.OcsHitIsland()){
                this.LaunchSpeedY =speedY;
            }
            if(this.OrcPushedByHero()) {
                pushed = true;
                Game.hero.setPushedByBoss(true);
            }
            double p=((this.LaunchSpeedY)-150)/100;
            this.LaunchSpeedY -=1.5;
            super.IncreseY(-p);
            if(OrcEliminatesHero()) {
                Game.hero.EleminateHero();
                pushOrcTimeline.stop();
            }
        } ));
        hopOrcTimeline.play();
        PushOrcTimeline(speedX);
    }
}
