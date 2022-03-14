package fr.ul.miage.dicegame;

import fr.ul.miage.dicegame.database.EbeanManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DiceGame extends Application {

    public static void main(String[] args) {
  /*
        PersistKit pk = new PersistKit(){
            @Override
            public Highscore makeKit(){
                throw new UnsupportedOperationException("Not supported");
            }
        };
        Highscore highscore;
        for (int i = 0; i < 4 ; i++) {
            Random rand = new Random();
            int max = 20;
            int min = 1;
            int nombreAleatoire = rand.nextInt(max - min + 1) + min;
            if (nombreAleatoire < 10) {
                pk = new JDBCKit();
            } else {
                pk = new SrKit();
            }
            System.out.println("Random " + i + " : ");
            highscore = pk.makeKit();
            highscore.WhoTheHellAreYou();
        }*/

        initManagers();
        //createDBConnection();
        launch();
    }

    private static void createDBConnection() {
        EbeanManager.getInstance().initDB();
    }

    private static void initManagers() {
        new EbeanManager();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DiceGame.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 868, 513);
        stage.setTitle("Dice Game");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}