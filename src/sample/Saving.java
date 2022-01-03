package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.util.ArrayList;

public class Saving {
    private static final String path=System.getProperty("user.dir")+"\\src\\sample\\files\\";
    private static Game game;
    private static ArrayList<SaveObject> AllSavings;
    private static boolean SavingFirst=true;

    @FXML
    private TextField NameField;
    @FXML
    private ImageView SaveGameButtonName;
    @FXML
    void SaveGame(MouseEvent event) throws IOException {
        String name= NameField.getText();
        ObjectOutputStream out = null;
        try{
//            if(SavingFirst){
//                AllSavings=new ArrayList<>();
//                SavingFirst=false;
//            }
//            else
                LoadGame();
            out = new ObjectOutputStream ( new FileOutputStream(path+"games.txt"));
            SaveObject saveObject=new SaveObject();
            saveObject.name=name;
            game.SaveAttributes(saveObject);
            AllSavings.add(0,saveObject);
            out.writeObject(AllSavings);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO Error in Save Game");
        }
        finally {
            out.close();
            Game.Translate(SaveGameButtonName.getParent().getParent(),0.0,450.0,500,1);
        }
    }

    void LoadGame() throws IOException {
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

    public static void setGame(Game G){
        Saving.game=G;
    }
}
