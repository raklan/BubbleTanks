
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
public class Game extends JFrame {

    GRectangle enviro;
    Tank tank;

    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Coin> allCoins = new ArrayList<>();
    ArrayList<Bullet> allBullets = new ArrayList<>();

    ArrayList<Sprite> toRemove = new ArrayList<>();

    Coin newCoin;

    Timer t = new Timer();
    int fps = 40;

    Player thePlayer;

    JLabel points;
    JLabel lives;

    public Game()
    {
        super("Bubble Tanks");
        setBounds(100,100,1000, 1000);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        enviro = new GRectangle(getWidth()*(-1), getHeight()*(-1), getWidth()*3, getHeight()*3);
        enviro.setBackground(Color.CYAN);
        add(enviro, 0);

        tank = new Tank(100, 100, 100, 100, this);
        tank.setFocusable(true);
        add(tank, 0);

        thePlayer = new Player();
        thePlayer.setScore(0);

        points = new JLabel("");
        points.setText("Money: "+String.valueOf(thePlayer.getScore()));
        points.setBounds(0,0,200,50);
        points.setVisible(true);
        add(points, 0);

        lives = new JLabel("");
        lives.setText("Lives: "+String.valueOf(thePlayer.getLives()));
        lives.setBounds(0,50,200,50);
        lives.setVisible(true);
        add(lives, 0);

        //Instantiates X number of enemy tanks. Change the i<=X to add more enemies
        for(int i = 1; i<=10; i++){
            enemies.add(new Enemy(i*50,i*50,100,100,this));
        }
        for(Enemy e: enemies){
            e.setVisible(true);
            add(e,0);
        }

        addMouseMotionListener(tank.getTurret());
        addMouseListener(tank);

        thePlayer = new Player();

        t.schedule(new MyTimerTask(), 0, 1000/fps);
        setVisible(true);
    }

    public void addBullet(Bullet b){
        add(b, 0);
        allBullets.add(b);
    }

    public Sprite getTank(){
        return tank;
    }

    class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            tank.move();

            for(Bullet b: allBullets){
                b.move();
                if(b.getX()>Enemy.game.getWidth())
                    toRemove.add(b);
                if(b.getY()>Enemy.game.getHeight())
                    toRemove.add(b);
                if(b.getX()<0)
                    toRemove.add(b);
                if(b.getY()<0)
                    toRemove.add(b);
            }

            for(Enemy e: enemies){
                e.move();
                for(Bullet b: allBullets) {
                    if (b.collides(e)) {
                        newCoin = new Coin(e.getX(), e.getY(), 25, 25);
                        newCoin.setVisible(true);
                        add(newCoin,0);
                        allCoins.add(newCoin);

                        toRemove.add(e);
                        toRemove.add(b);
                    }
                    if(b.collides(tank)) {
                        thePlayer.setLives(thePlayer.getLives() - 1);
                        lives.setText("Lives: "+thePlayer.getLives());
                    }
                }

            }
            for(Coin c: allCoins){
                if(tank.collides(c)){
                    toRemove.add(c);
                    thePlayer.setScore(thePlayer.getScore()+10);
                    points.setText("Money: "+String.valueOf(thePlayer.getScore()));
                }
            }
            for(Sprite s: toRemove){
                s.setVisible(false);
                if(s instanceof Bullet)
                    allBullets.remove(s);
                else if(s instanceof Enemy)
                    enemies.remove(s);
                else if(s instanceof Coin)
                    allCoins.remove(s);
                remove(s);
            }

            repaint();
        }
    }
}