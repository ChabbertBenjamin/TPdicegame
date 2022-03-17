package fr.ul.miage.dicegame.entity;

public class Player {


        private static Player instance = null;

        private Player() {}

        public static synchronized Player getInstance() {
            if (instance == null) {
                instance = new Player();
                instance.setScore(0);
            }

            return instance;
        }


    private String name;
    private int score;
    private Dice dice1;
    private Dice dice2;

    public void play(){
        dice1.launch();
        dice2.launch();
    }

    public void incrementScore(int up){
        this.score = score + up;
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
