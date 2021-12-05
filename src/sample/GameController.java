package sample;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;


public class GameController implements Initializable {

    @FXML
    private AnchorPane FirstIslandPane;
    @FXML
    private AnchorPane IslandPane;
    @FXML
    private AnchorPane SecondIslandPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupFirstPane();
        Button b = new Button("Button ");
        AnchorPane.setRightAnchor(b, 150.0);
        AnchorPane.setTopAnchor(b, 150.0);
        FirstIslandPane.getChildren().add(b);
    }

    private void setupFirstPane(){
        //setup the starting island and other elements
    }
}
