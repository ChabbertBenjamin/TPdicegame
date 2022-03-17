package fr.ul.miage.dicegame.factory;

import fr.ul.miage.dicegame.product.Highscore;
import fr.ul.miage.dicegame.product.HighscoreMysql;

public class MysqlKit extends PersistKit {

    @Override
    public Highscore makeKit() {
        Highscore highscore = new HighscoreMysql();
        return highscore;
    }
}

