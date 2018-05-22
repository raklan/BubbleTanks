import java.awt.*;
import java.util.Random;

public class Enemy extends Sprite{
    private int dx;
    private int dy;

    Random gen = new Random();

    public static Game game;

    Turret turret;
    double error = 0.00;
    double maxError = 0.50;

    int time;
    int interval = 100;

    public Enemy(int x, int y, int width, int height, Game theGame){
        super(x,y,width,height, "src/resources/Lvl1Tank.png");

        game = theGame;

        dx=4;
        dy=4;

        turret = new Turret(this);
        add(turret);

        time = gen.nextInt(100);
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

        //=============TURRET CODE==============
        int x = game.getTank().getX()+50;
        int y = game.getTank().getY()+50;
        double angle = Math.atan2(getY()+50-y,getX()+50-x);
        error += (chance%2==0)? 0.01:-0.01;
        if(Math.abs(error)>maxError){
            error -= (chance%2==0)? 0.01:-0.01;
        }
        angle+=error;
        turret.setAngle(Math.PI*2-angle);
        //============END TURRET CODE==============

        //==============SHOOTING CODE=============
        if(--time<=0){
            angle = angle - Math.PI/2;
            game.addBullet(new Bullet(getX()+25, getY()+25, angle, false));
            time=interval;
        }
        //===========END SHOOTING CODE===========
    }

    public void paint(Graphics g)  {
        g.setClip(null);
        Graphics2D graphics2D = (Graphics2D)g.create();
        graphics2D.rotate(Math.PI*2-Math.atan2(dy,dx), getWidth()/2, getHeight()/2);
        graphics2D.drawImage(content, 0, 0, getWidth(), getHeight(), this);
        paintChildren(g);
        graphics2D.dispose();
    }
}
