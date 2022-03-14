package fr.ul.miage.dicegame.controller.fxml;

import fr.ul.miage.dicegame.entity.Dice;
import fr.ul.miage.dicegame.entity.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HomeController {

    int nbThrows;
    Player player;

    @FXML
    private Button btn_launch;

    @FXML
    private Button btn_play;

    @FXML
    private TextField txt_name;


    @FXML
    private Label lbl_res_de1;

    @FXML
    private Label lbl_res_de2;

    @FXML
    private Label lbl_nb_lancer;

    @FXML
    private Label lbl_score;

    @FXML
    private Label lbl_best_score;


    @FXML
    protected void onClick_btn_launch(){
        if (txt_name.getLength() >=1){

            Dice dice1 = new Dice();
            Dice dice2 = new Dice();
            this.player = new Player(txt_name.getText(), dice1, dice2);
            txt_name.setDisable(true);
            btn_launch.setDisable(true);
            btn_play.setDisable(false);
        }
    }

    @FXML
    protected void onClick_btn_play(){

        nbThrows += 1;

        player.play();
        lbl_res_de1.setText(String.valueOf(player.getDice1().getNumber()));
        lbl_res_de2.setText(String.valueOf(player.getDice2().getNumber()));
        int score = player.getDice1().getNumber()+player.getDice2().getNumber();
        lbl_score.setText(String.valueOf(score));

        lbl_nb_lancer.setText(String.valueOf(nbThrows));

        if(score == 7){
            player.setScore(player.getScore()+1);
            lbl_best_score.setText(String.valueOf(player.getScore()));
        }


        if(nbThrows == 10){
            btn_play.setDisable(true);
        }

    }
}