package sample;

import javafx.scene.image.ImageView;

public final class RedOrc extends Orc{
    private Integer Power=20;
    private Integer CoinsOnElimination=10;

    public RedOrc(ImageView image) {
        super(image);
    }

    @Override
    public Integer getCoinsOnElimination() {
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
        this.HopOrc(250.0,350.0);
    }
}
