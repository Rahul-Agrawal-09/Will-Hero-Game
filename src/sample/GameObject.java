package sample;

import javafx.scene.image.ImageView;

public abstract class GameObject {
    private final ImageView Element;
    private final Double ImageHeight;
    private final Double ImageWidth;
    private final Double IgnoreHeight;

    public GameObject(ImageView image){
        this.Element=image;
        this.ImageHeight=image.getFitHeight();
        this.ImageWidth=image.getFitWidth();
        this.IgnoreHeight =0.0;
    }

    public GameObject(ImageView image, Double D){
        this.Element=image;
        this.ImageHeight=image.getFitHeight();  //not adjusting ignore_height here
        this.ImageWidth=image.getFitWidth();
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

    public void IncreseX(Integer I){
        this.Element.setLayoutX(this.Element.getLayoutX()+I);
    }

    public void IncreseY(Integer I){
        this.Element.setLayoutY(this.Element.getLayoutY()+I);
    }

}
