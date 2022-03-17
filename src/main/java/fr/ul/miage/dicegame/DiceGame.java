package fr.ul.miage.dicegame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DiceGame extends Application {

    public static void main(String[] args) {
        initManagers();
        launch();
    }



    private static void initManagers() {

    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DiceGame.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1068, 453);
        stage.setTitle("Dice Game");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}