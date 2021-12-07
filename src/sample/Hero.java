package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Hero extends GameObject{
    private AnchorPane pane;

    public Hero(ImageView IV){
        super(IV,0.0);
    }

    public void setPane(AnchorPane AP){
        this.pane=AP;
        pane.getChildren().add(super.getImageView());
    }

}
