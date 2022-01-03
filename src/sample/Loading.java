package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Loading implements Initializable {
    private static ArrayList<Label> labels;
    private static ArrayList<SaveObject> AllSavings;
    private static AnchorPane parent;
    private static Integer size;
    private static GameConsole gameConsole;
    private static final String path=System.getProperty("user.dir")+"\\src\\sample\\files\\";

    @FXML
    private Label L1;
    @FXML
    private Label L2;
    @FXML
    private Label L3;
    @FXML
    private Label L4;
    @FXML
    private Label L5;
    @FXML
    void L1Load(MouseEvent event) {
        helper(0);
    }
    @FXML
    void L2Load(MouseEvent event) {
        helper(1);
    }
    @FXML
    void L3Load(MouseEvent event) {
        helper(2);
    }
    @FXML
    void L4Load(MouseEvent event) {
        helper(3);
    }
    @FXML
    void L5Load(MouseEvent event) {
        helper(4);
    }

    public void helper(Integer i){
        Game.Translate(parent,0.0,450.0,500,1);
        if(i<size){
            gameConsole.LoadNewGamePane();
            Game.LoadAttributes(AllSavings.get(i));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labels=new ArrayList<>();
        labels.add(L1);
        labels.add(L2);
        labels.add(L3);
        labels.add(L4);
        labels.add(L5);
    }

    public static void show(){
        try {
            Loading.LoadGame();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: In Loading Class");
        }
        for(int i=0;i<Math.min(AllSavings.size(),5);i++){
            labels.get(i).setText(AllSavings.get(i).name+"    "+AllSavings.get(i).HeroCoins);
        }
        size=AllSavings.size();
    }

    private static void LoadGame() throws IOException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream (new FileInputStream(path+"games.txt"));
            AllSavings = (ArrayList<SaveObject>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error: Loading The Game");
        }
        finally {
            in.close();
        }
    }

    public static void setparent(AnchorPane AP,GameConsole GC){
        Loading.parent=AP;
        gameConsole=GC;
    }

//    ObjectInputStream in = null;
//        try {
//        in = new ObjectInputStream (new FileInputStream(path+"games.txt"));
//        ArrayList<SaveObject> SO = (ArrayList<SaveObject>) in.readObject();
//        System.out.println(SO.get(0).HeroLayoutX+" "+ SO.get(0).HeroCoins+" "+SO.get(0).CurrentWeapon+" "+SO.get(0).HeroPosition);
//        LoadNewGamePane();
//        Game.LoadAttributes(SO.get(0));
//        for(SaveObject S:SO)
//            System.out.println(S.name+" ");
//    } catch (IOException | ClassNotFoundException e) {
//        e.printStackTrace();
//        System.out.println("Error: Loading The Game");
//    }
//        finally {
//        in.close();
//    }
}
