package sample;

import javafx.scene.image.ImageView;

public final class GreenOrc extends Orc{
    private Integer Power=1;
    private Integer CoinsOnElimination=5;

    public GreenOrc(ImageView image) {
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
        this.HopOrc(350.0,350.0);
    }


}
