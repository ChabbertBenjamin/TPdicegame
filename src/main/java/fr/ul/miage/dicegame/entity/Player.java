package fr.ul.miage.dicegame.entity;

public class Player {
    private String name;
    private int score;
    private Dice dice1;
    private Dice dice2;

    public Player(String name, Dice dice1, Dice dice2) {
        this.name = name;
        this.dice1 = dice1;
        this.dice2 = dice2;
        this.score = 0;
    }

    public void play(){
        dice1.launch();
        dice2.launch();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Dice getDice1() {
        return dice1;
    }

    public void setDice1(Dice dice1) {
        this.dice1 = dice1;
    }

    public Dice getDice2() {
        return dice2;
    }

    public void setDice2(Dice dice2) {
        this.dice2 = dice2;
    }
}
