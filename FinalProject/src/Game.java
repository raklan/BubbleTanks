
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
public class Game extends JFrame {

    GRectangle enviro;
    Tank tank;

    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Coin> allCoins = new ArrayList<>();
    ArrayList<Bullet> allBullets = new ArrayList<>();

    Timer t = new Timer();
    int fps = 40;

    Player thePlayer;

    public Game()
    {
        super("Bubble Tanks");
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        enviro = new GRectangle(getWidth()*(-1), getHeight()*(-1), getWidth()*3, getHeight()*3);
        enviro.setBackground(Color.CYAN);
        add(enviro, 0);

        tank = new Tank(100, 100, 100, 100);
        tank.setFocusable(true);
        add(tank, 0);

        //Instantiates X number of enemy tanks. Change the i<=X to add more enemies
        for(int i = 1; i<=0; i++){
            enemies.add(new Enemy(i*50,i*50,100,100,this));
        }
        for(Enemy e: enemies){
            e.setVisible(true);
            add(e,0);
        }

        thePlayer = new Player();

        t.schedule(new MyTimerTask(), 0, 1000/fps);
        setVisible(true);
    }

    class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            tank.move();

            for(Enemy e: enemies){
                e.move();
                for(Bullet b: allBullets){
                    if(b.collides(e)) {
                        allCoins.add(new Coin(e.getX(), e.getY(),100,100));

                        e.setVisible(false);
                        b.setVisible(false);

                        enemies.remove(e);
                        remove(e);

                        allBullets.remove(b);
                        remove(b);
                    }
                }
                for(Coin c: allCoins){
                    add(c, 0);
                    c.setVisible(true);
                }
            }
            for(Bullet b: allBullets){
                if(b.collides(tank))
                    thePlayer.setLives(thePlayer.getLives()-1);
            }
            for(Coin c: allCoins){
                if(c.collides(tank)){
                    c.setVisible(false);

                    allCoins.remove(c);
                    remove(c);
                    thePlayer.setScore(thePlayer.getScore()+10);
                }
            }

            repaint();
        }
    }
}
