package bubbleTanks;

public class Player{

    private int topScore;
    private long currentScore;

    private int bulletBuff;

    private int speedBuff;

    private int lives;
    private int maxLives = 5;

    public Player(){
        reset();
    }

    public void reset(){
        currentScore = 0;
        maxLives=5;
        lives = maxLives;
        bulletBuff=0;
        speedBuff=0;
    }

    //Returns the High Score
    public int getTopScore(){
        return topScore;
    }

    //Returns the bubbleTanks.Player's Current Score
    public long getScore() {
        return currentScore;
    }

    //Returns the Current number of lives
    public int getLives() {
        return lives;
    }

    //Sets a new High Score. Checks to make sure
    public void setTopScore(int newScore) {
        if(topScore<newScore)
            topScore = newScore;
    }

    //Sets the bubbleTanks.Player's current score
    public void setScore(long score) {
        currentScore = score;
    }

    //Sets the bubbleTanks.Player's Lives
    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setBulletBuff(int newBuff){
        bulletBuff = newBuff;
    }

    public int getBulletBuff(){
        return bulletBuff;
    }

    public int getMaxLives(){return maxLives;}

    public void setMaxLives(int newMax){maxLives = newMax;}

    public int getSpeedBuff() {
        return speedBuff;
    }

    public void setSpeedBuff(int speedBuff) {
        this.speedBuff = speedBuff;
    }
}