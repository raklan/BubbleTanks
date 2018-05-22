import java.util.Random;

public class Enemy extends Sprite{
    private int dx;
    private int dy;

    Random gen = new Random();

    public static Game game;

    public Enemy(int x, int y, int width, int height, Game theGame){
        super(x,y,width,height, "src/resources/Lvl1Tank.png");

        game = theGame;

        dx=4;
        dy=4;
    }

    public static Game getGame(){
        return game;
    }

    @Override
    public void move() {
        //==========MOVEMENT CODE==============
        int chance = gen.nextInt(51);

        if (this.getX() <= 0 || this.getX() >= (game.getWidth() - this.getWidth())) {
                dx *= -1;
        }if (this.getY() <= 0 || this.getY() >= (game.getHeight() - (this.getHeight()))) {
                dy *= -1;
        }else{
            if(chance==1){
                chance = gen.nextInt(2);
                if(chance ==1)
                    dx*=-1;
                else
                    dy*=-1;
            }
        }
        setLocation(getX() + dx, getY() + dy);
        //=============END MOVEMENT CODE===========
    }
}
