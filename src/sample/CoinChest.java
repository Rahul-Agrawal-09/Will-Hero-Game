package sample;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public final class CoinChest extends Chest{
    private final Integer CoinNum;
    private static Label CoinLabel;

    public CoinChest(ImageView image) {
        super(image);
        this.CoinNum=10;
    }

    @Override
    public void OpenChest(Hero hero) {
        hero.increaseCoins(this.CoinNum);
        this.getImageView().setImage(Chest.CoinChests.get(1).getImageView().getImage());
        this.getImageView().setFitWidth(this.getImageWidth()*1.1);
    }
}
