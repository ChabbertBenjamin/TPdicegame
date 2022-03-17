package fr.ul.miage.dicegame.product;

import fr.ul.miage.dicegame.entity.Score;

import java.util.ArrayList;

public interface Highscore {

    public abstract void openConnection();
    public abstract void saveScore(String name, int score);
    public abstract int getScore(String name);
    public abstract ArrayList<Score> getTop10Score();
}