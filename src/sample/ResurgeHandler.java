package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ResurgeHandler implements Initializable {
    private static Label CoinLabel;
    private static Label W1;
    private static Label W2;
    private static GameConsole gameConsole;
    private final Integer ResurgeCoins=10;
    private boolean HaveResurged;
    private static boolean HaveWon;

    @FXML
    private ImageView BannerHomeButton;
    @FXML
    private ImageView BannerReplayButton;
    @FXML
    private ImageView BannerResurgeButton;
    @FXML
    private Label BannerWarning;
    @FXML
    private Label CoinCount;
    @FXML
    private Label winner1;
    @FXML
    private Label winner2;

    @FXML
    void BackToHome(MouseEvent event) {
        Game.Translate(BannerHomeButton.getParent().getParent(),0.0,450.0,500,-1);
        gameConsole.LoadGameConsole();
        HaveWon=false;
    }

    @FXML
    void ReplayGame(MouseEvent event) {
        Game.Translate(BannerHomeButton.getParent().getParent(),0.0,450.0,500,-1);
        gameConsole.ReplayGame();
        HaveWon=false;
    }

    @FXML
    void ResurgeHero(MouseEvent event) {
        if(HaveWon){
            BannerWarning.setText("ALREADY WON");
            Game.Fade(BannerWarning, 0.0, 1.0, 100, 1);
            return;
        }
        if(!HaveResurged) {
            if (Game.hero.getCoins() < this.ResurgeCoins) {
                BannerWarning.setText("INSUFFICIENT COINS");
                Game.Fade(BannerWarning, 0.0, 1.0, 100, 1);
            } else {
                HaveResurged=true;
                Game.hero.increaseCoins(-1*(this.ResurgeCoins));
                Game.Translate(BannerHomeButton.getParent().getParent(),0.0,450.0,500,-1);
                Game.hero.ResurgeHero();
            }
        }
        else{
            BannerWarning.setText("ALREADY RESURGED");
            Game.Fade(BannerWarning, 0.0, 1.0, 100, 1);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResurgeHandler.W1=winner1;
        ResurgeHandler.W2=winner2;
        ResurgeHandler.CoinLabel=CoinCount;
        HaveWon=false;
        HaveResurged=false;
    }

    public static void declareWinner(){
        Game.Fade(W1,0.0,1.0,100,1);
        Game.Fade(W2,0.0,1.0,100,1);
        HaveWon=true;
    }

    public static void PutCoinCount(Hero hero){
        ResurgeHandler.CoinLabel.setText(hero.getCoins()+"");
    }

    public static void setGameConsole(GameConsole GC){
        ResurgeHandler.gameConsole=GC;
    }
}
