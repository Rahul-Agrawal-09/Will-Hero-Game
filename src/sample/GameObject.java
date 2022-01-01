package sample;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public abstract class GameObject implements Cloneable, Serializable {
    private ImageView Element;
    private final Double ImageHeight;
    private final Double ImageWidth;
    private final Double IgnoreHeight;

    @Override
    public GameObject clone() throws CloneNotSupportedException {
        GameObject GO=(GameObject) super.clone();
        ImageView IV=new ImageView();
        IV.setImage(this.getImageView().getImage());
        IV.setPreserveRatio(true);
        IV.setFitWidth(this.getImageWidth());
        GO.Element=IV;
        GO.IncreseX(this.getxPositionLeft());
        return GO;
    }

    public GameObject(ImageView image, Double D){
        this.Element=image;
        this.ImageHeight=image.getBoundsInLocal().getHeight();  //not adjusting ignore_height here
        this.ImageWidth=image.getBoundsInLocal().getWidth();
        this.IgnoreHeight =D;
    }

    public Double getxPositionLeft() {
        return this.Element.getLayoutX();
    }

    public Double getxPositionRight(){
        return this.Element.getLayoutX()+this.ImageWidth;
    }

    public Double getyPositionTop(){
        return this.Element.getLayoutY()+this.IgnoreHeight;
    }

    public Double getyPositionBottom(){
        return this.Element.getLayoutY()+this.ImageHeight;
    }

    public void IncreseX(Double I){
        this.Element.setLayoutX(this.Element.getLayoutX()+I);
    }

    public void IncreseY(Double I){
        this.Element.setLayoutY(this.Element.getLayoutY()+I);
    }

    public ImageView getImageView(){
        return this.Element;
    }

    public Double getImageHeight(){
        return this.ImageHeight;
    }

    public Double getOffset(){
        return this.IgnoreHeight;
    }

    public Double getImageWidth(){
        return this.ImageWidth;
    }

}
