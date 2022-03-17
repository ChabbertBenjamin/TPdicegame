package fr.ul.miage.dicegame.controller.fxml;

import fr.ul.miage.dicegame.entity.Dice;
import fr.ul.miage.dicegame.entity.Player;
import fr.ul.miage.dicegame.entity.Score;
import fr.ul.miage.dicegame.factory.MysqlKit;
import fr.ul.miage.dicegame.factory.PersistKit;
import fr.ul.miage.dicegame.factory.PostGresKit;
import fr.ul.miage.dicegame.product.Highscore;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    int nbThrows;

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
    private RadioButton radioPostGRES;

    @FXML
    RadioButton radioMysql;

    @FXML
    private Button btn_get_score;

    @FXML
    private Button btn_highscores;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btn_get_score.setDisable(true);
        radioMysql.setSelected(true);

        radioMysql.setOnAction(event -> {
            radioPostGRES.setSelected(false);
        });

        radioPostGRES.setOnAction(event -> {
            radioMysql.setSelected(false);
        });


        btn_highscores.setOnAction(actionEvent -> {

            Highscore highscore;
            if(radioMysql.isSelected()){
                PersistKit pk = new MysqlKit();
                highscore = pk.makeKit();
            }else{
                PersistKit pk = new PostGresKit();
                highscore = pk.makeKit();
            }

            ArrayList<Score> scores = new ArrayList<>(highscore.getTop10Score());

            String scoresStr = "TOP 10 - HIGHSCORES " + (radioMysql.isSelected() ? "(MySQL)" : "(PostGRES)") + "\n";

            int position = 1;
            Iterator iterator = scores.iterator();
            while (iterator.hasNext())
            {
                Score sc = (Score) iterator.next();
                scoresStr =   scoresStr + "#" + position + " | " + sc.getName() + " - " + sc.getScore() + "\n";
                position++;
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION, scoresStr, ButtonType.YES);
            alert.showAndWait();
            return;
        });

        btn_get_score.setOnAction(actionEvent -> {

            if (txt_name.getLength() < 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez saisir un nom de joueur.", ButtonType.YES);
                alert.showAndWait();
                return;
            }

            Highscore highscore;
            if(radioMysql.isSelected()){
                PersistKit pk = new MysqlKit();
                highscore = pk.makeKit();
            }else{
                PersistKit pk = new PostGresKit();
                highscore = pk.makeKit();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION,
                    "Votre score est de: " + highscore.getScore(Player.getInstance().getName()) + " (Dans " + (radioMysql.isSelected() ? "Mysql" : "PostGres") + ")"
                    , ButtonType.YES);
            alert.showAndWait();
        });

        btn_launch.setOnAction(event -> {
            if (txt_name.getLength() >= 1) {

                nbThrows = 0;

                lbl_res_de1.setText(String.valueOf(0));
                lbl_res_de2.setText(String.valueOf(0));
                lbl_score.setText(String.valueOf(0));
                lbl_best_score.setText(String.valueOf(0));
                lbl_nb_lancer.setText(String.valueOf(0));
                Dice dice1 = new Dice();
                Dice dice2 = new Dice();
                Player.getInstance().setName(txt_name.getText());
                Player.getInstance().setDice1(dice1);
                Player.getInstance().setDice2(dice2);
                Player.getInstance().setScore(0);
                txt_name.setDisable(true);
                btn_launch.setDisable(true);
                btn_play.setDisable(false);
                btn_get_score.setDisable(false);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Veuillez saisir un nom de joueur.", ButtonType.YES);
                alert.showAndWait();
            }
        });

        btn_play.setOnAction(event -> {
            nbThrows += 1;

            Player.getInstance().play();
            lbl_res_de1.setText(String.valueOf(Player.getInstance().getDice1().getNumber()));
            lbl_res_de2.setText(String.valueOf(Player.getInstance().getDice2().getNumber()));
            int score = Player.getInstance().getDice1().getNumber() + Player.getInstance().getDice2().getNumber();
            lbl_score.setText(String.valueOf(score));

            lbl_nb_lancer.setText(String.valueOf(nbThrows));

            if (score == 7) {
                Player.getInstance().incrementScore(10);
                lbl_best_score.setText(String.valueOf(Player.getInstance().getScore()));
            }


            if (nbThrows == 10) {


                Highscore highscore;

                if(radioMysql.isSelected()){
                    PersistKit pk = new MysqlKit();
                    highscore = pk.makeKit();
                }else{
                    PersistKit pk = new PostGresKit();
                    highscore = pk.makeKit();
                }


                highscore.saveScore(Player.getInstance().getName(), Player.getInstance().getScore());

                btn_play.setDisable(true);
                btn_launch.setDisable(false);


            }


        });
    }


}