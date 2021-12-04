//package sample;
//
//import javafx.animation.TranslateTransition;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.util.Duration;
//
//public class Controller{
//
//    @FXML
//    private ImageView player;
//
//
//    @FXML
//    void move(MouseEvent event) {
//        TranslateTransition translate=new TranslateTransition();
//        translate.setNode(player);
//        translate.setDuration(Duration.millis(1000));
//        translate.setByX(200);
//        translate.play();
//    }
//
//    void move2(ActionEvent event) {
//        TranslateTransition translate=new TranslateTransition();
//        translate.setNode(player);
//        translate.setDuration(Duration.millis(1000));
//        translate.setByX(200);
//        translate.play();
//    }
//
//}
