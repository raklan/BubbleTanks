public class Player{

    public int topScore;
    public int currentScore;

    public int lives;

    public Player(){
        currentScore = 0;
        lives = 1;
    }

    //Returns the High Score
    public int getTopScore(){
        return topScore;
    }
    //Returns the Player's Current Score
    public int getScore() {
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
    //Sets the Player's current score
    public void setScore(int score) {
        currentScore = score;
    }
    //Sets the Player's Lives
    public void setLives(int lives) {
        this.lives = lives;
    }
}