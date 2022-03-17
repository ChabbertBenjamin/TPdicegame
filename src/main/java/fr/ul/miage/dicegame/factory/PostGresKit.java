package fr.ul.miage.dicegame.factory;

import fr.ul.miage.dicegame.product.Highscore;
import fr.ul.miage.dicegame.product.HighscorePostGres;

public class PostGresKit extends PersistKit {
    @Override
    public Highscore makeKit() {
        Highscore highscore = new HighscorePostGres();
        return highscore;
    }
}
