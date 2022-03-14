package fr.ul.miage.dicegame.factory;

import fr.ul.miage.dicegame.product.Highscore;
import fr.ul.miage.dicegame.product.HighscoreJDBC;

public class JDBCKit extends PersistKit {
    @Override
    public Highscore makeKit() {
        Highscore highscore = new HighscoreJDBC();
        return highscore;
    }
}

