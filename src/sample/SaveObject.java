package sample;

import java.io.Serial;
import java.io.Serializable;

public class SaveObject implements Serializable {
//    AllIslandPanePosition= -(125*HeroPosition)
    @Serial
    public static final long serialVersionUID = 58L;
    public String name;
    public int HeroPosition;  //Hero
    public int HeroCoins;  //Hero
    public double HeroLayoutX; //Game
    public int NITP;    //Game
    public int SwordLevel;  //Helmet
    public int SpearLevel;  //Helmet
    public String CurrentWeapon;    //Helmet

}
