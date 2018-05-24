
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
public class Game extends JFrame {

    GRectangle enviro;
    protected static Tank tank;

    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Coin> allCoins = new ArrayList<>();
    ArrayList<Bullet> allBullets = new ArrayList<>();

    ArrayList<Sprite> toRemove = new ArrayList<>();

    Coin newCoin;

    Timer t;
    int fps = 40;

    protected Player thePlayer;

    JLabel points;
    JLabel lives;

    Shop shop;

    public Game()
    {
        super("Bubble Tanks");
        setBounds(100,100,1500, 1000);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        thePlayer = new Player();

        enviro = new GRectangle(getWidth()*(-1), getHeight()*(-1), getWidth()*3, getHeight()*3);
        enviro.setBackground(Color.CYAN);
        add(enviro, 0);

        tank = new Tank(getWidth()/2-50, getHeight()/2-50, 100, 100, this, thePlayer);
        tank.setFocusable(true);
        add(tank, 0);

        points = new JLabel("");
        points.setBounds(0,0,200,50);
        points.setVisible(true);
        add(points, 0);

        lives = new JLabel("");
        lives.setBounds(0,50,200,50);
        lives.setVisible(true);
        add(lives, 0);

        setup();

        shop = new Shop(this);

        addMouseMotionListener(tank.getTurret());
        addMouseListener(tank);

        setVisible(true);
    }

    public void setup(){
        thePlayer.reset();
        tank.setLocation(getWidth()/2-50, getHeight()/2-50);
        points.setText("Money: "+String.valueOf(thePlayer.getScore()));
        lives.setText("Lives: "+String.valueOf(thePlayer.getLives()));

        //Instantiates X number of enemy tanks. Change the i<=X to add more enemies
        for(int i = 1; i<=10; i++){
            enemies.add(new Enemy(i*50,i*50,100,100,this));
        }
        for(Enemy e: enemies){
            e.setVisible(true);
            add(e,0);
        }
        t = new Timer();
        t.schedule(new MyTimerTask(), 0, 1000/fps);
    }

    public void addBullet(Bullet b){
        add(b, 0);
        allBullets.add(b);
    }

    public Sprite getTank(){
        return tank;
    }

    public Player getPlayer(){
        return thePlayer;
    }

    public void pause(){
        t.cancel();
        shop.setVisible(true);
    }

    public void unpause(){
        toFront();
        requestFocus();
        tank.requestFocus();
        t = new Timer();
        t.schedule(new MyTimerTask(), 0, 1000/fps);
    }

    public void updatePoints(long p){
        points.setText("Money: "+String.valueOf(p));
    }

    public void gameOver(){
        t.cancel();
        int pane = JOptionPane.showConfirmDialog(null, "Game Over!\nWould you like to try again?", "Game Over", JOptionPane.YES_NO_OPTION);//0=yes, 1=no
        if(pane==0){
            for(Bullet b: allBullets)
                remove(b);
            for(Enemy e: enemies)
                remove(e);
            for(Coin c: allCoins)
                remove(c);
            allBullets.clear();
            enemies.clear();
            allCoins.clear();
            setup();
        }
    }

    class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            lives.setText("Lives: "+thePlayer.getLives());
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
                    if (b.collides(e)&&b.isPlayer()) {
                        newCoin = new Coin(e.getX(), e.getY(), 25, 25);
                        newCoin.setVisible(true);
                        add(newCoin,0);
                        allCoins.add(newCoin);

                        toRemove.add(e);
                        toRemove.add(b);
                    }
                }

            }

            for(Bullet b: allBullets){
                if(b.collides(tank)&&!b.isPlayer()) {
                    toRemove.add(b);
                    thePlayer.setLives(thePlayer.getLives() - 1);
                    lives.setText("Lives: "+thePlayer.getLives());
                }
            }

            for(Coin c: allCoins){
                if(tank.collides(c)){
                    toRemove.add(c);
                    thePlayer.setScore(thePlayer.getScore()+10);
                    shop.updateScore(thePlayer.getScore());
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
            if(thePlayer.getLives()<=0){
                gameOver();
            }
        }
    }
}