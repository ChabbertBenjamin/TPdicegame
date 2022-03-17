package fr.ul.miage.dicegame.product;

import fr.ul.miage.dicegame.entity.Score;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HighscorePostGres implements Highscore {

    java.sql.Connection connection;

    @Override
    public void openConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dicegame","postgres", "yourPassword");

            System.out.println("Connected");
        } catch(Exception e){
            System.out.println("Erreur de connexion à la base de données: " + e);
        }
    }

    @Override
    public void saveScore(String name, int score) {
        try {
            openConnection();
            Statement stmt = this.connection.createStatement();

            String query1 = "INSERT INTO highscores (name, score) VALUES ('" + name + "', " + score +")";

            int oldScore = getScore(name);
            if(oldScore != -1)
                query1 = "UPDATE highscores SET score = " + score + " WHERE name = '" + name + "'";

            if(oldScore < score)
            stmt.executeUpdate(query1);
            stmt.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getScore(String name) {
        openConnection();
        int score = -1;
        try {
            openConnection();
            Statement stmt = this.connection.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM highscores WHERE name = '" + name + "'");

            System.out.println("SELECT * FROM highscores WHERE name = '" + name + "'\")");
            while(res.next()) {
                score = res.getInt(2);
            }
            stmt.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return score;
    }

    @Override
    public ArrayList<Score> getTop10Score() {
        openConnection();
        ArrayList<Score> scores = new ArrayList<>();
        int score = 0;
        try {
            openConnection();
            Statement stmt = this.connection.createStatement();
            ResultSet res = stmt.executeQuery("select * from highscores order by score desc limit 10");

            while(res.next()) {
                Score scoreObj = new Score();
                scoreObj.setName(res.getString(1));
                scoreObj.setScore(res.getInt(2));
                scores.add(scoreObj);
            }

            stmt.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return scores;
    }

}
