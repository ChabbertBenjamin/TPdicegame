package fr.ul.miage.dicegame.factory;

import fr.ul.miage.dicegame.product.Highscore;
import fr.ul.miage.dicegame.product.HighscoreSr;

public class SrKit extends PersistKit {
    @Override
    public Highscore makeKit() {
        Highscore highscore = new HighscoreSr();
        return highscore;
    }
}
