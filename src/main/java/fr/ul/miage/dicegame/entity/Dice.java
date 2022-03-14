package fr.ul.miage.dicegame.entity;

import java.util.Random;

public class Dice {

    private int number;

    public Dice() {
    }

    public void launch(){
        Random rand = new Random();
        int max = 6;
        int min = 1;
        this.number = rand.nextInt(max - min + 1) + min;
    }

    public int getNumber(){
        return number;
    }
}
