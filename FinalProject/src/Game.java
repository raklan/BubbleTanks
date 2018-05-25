
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Timer;

public class Game extends JFrame{

    GRectangle enviro;
    protected static Tank tank;

    ArrayList<Enemy> enemies = new ArrayList<>();
    ArrayList<Coin> allCoins = new ArrayList<>();
    ArrayList<Bullet> allBullets = new ArrayList<>();

    ArrayList<Sprite> toRemove = new ArrayList<>();

    Coin newCoin;

    protected static Timer t;
    int fps = 40;

    protected Player thePlayer;

    JLabel points;
    JLabel lives;
    JLabel wave;

    Random gen = new Random();

    private static int waveNum;

    Shop shop;

    public Game()
    {
        super("Bubble Tanks");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        thePlayer = new Player();

        this.getContentPane().setBackground(new Color(166, 180, 166, 255));

        tank = new Tank(500, 50, 100, 100, this, thePlayer);
        tank.setFocusable(true);
        add(tank, 0);

        points = new JLabel("");
        points.setBounds(10,50,200,50);
        points.setVisible(true);
        add(points, 0);

        lives = new JLabel("");
        lives.setBounds(10,100,300,50);
        lives.setVisible(true);
        add(lives, 0);

        wave = new JLabel("");
        wave.setBounds(10,150,200,50);
        wave.setVisible(true);
        add(wave, 0);

        waveNum = 0;

        shop = new Shop(this);

        setup();

        addMouseMotionListener(tank.getTurret());
        addMouseListener(tank);

        setVisible(true);
    }

    public void setup(){
        thePlayer.reset();
        shop.reset();
        tank.setLocation(getWidth()/2-50, getHeight()/2-50);
        points.setText("Money: "+String.valueOf(thePlayer.getScore()));

        t = new Timer();
        t.schedule(new MyTimerTask(), 0, 1000/fps);
    }

    public synchronized void addBullet(Bullet b){
        add(b, 0);
        allBullets.add(b);
        if(allBullets.size()>50)
        {
            remove(allBullets.get(allBullets.size()-1));
            allBullets.remove(allBullets.size()-1);
        }
    }

    public Sprite getTank(){
        return tank;
    }

    public Player getPlayer(){
        return thePlayer;
    }

    public void pause(){
        t.cancel();
        Shop.speedUpgrade.setText("Upgrade Tank Speed \n Cost: " + Shop.speedCost);
        Shop.bulletSpeed.setText("Upgrade Bullet Speed  \n Cost: " + Shop.bulletCost);
        Shop.maxHP.setText("Upgrade Max HP \n Cost: " + Shop.HPCost);
        Shop.life.setText("Buy Another Life \n Cost: " + Shop.lifeCost);
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
        tank.resetKeys();
        int pane = JOptionPane.showConfirmDialog(null, "Game Over!\nWould you like to try again?", "Game Over", JOptionPane.YES_NO_OPTION);//0=yes, 1=no
        if(pane==0){//if yes
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

    public void waveX(int waveN){
        //Instantiates waveNum number of enemy tanks. For our purposes, the number of enemies is equal to the wave the player is on.
        for(int i = 1; i<=waveN; i++){
            enemies.add(new Enemy(gen.nextInt(500)+200,gen.nextInt(500)+200,100,100,this));
        }
        for(Enemy e: enemies){
            e.setVisible(true);
            add(e,0);
        }
    }

    class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            try {
            if(enemies.isEmpty()) {
                waveNum+=1;
                wave.setText("Wave: "+waveNum);
                waveX(waveNum);
            }
            lives.setText("Lives: "+thePlayer.getLives()+" / "+ thePlayer.getMaxLives());
            tank.move();

            for(Bullet b: allBullets) {
                b.move();
                if (b.getX() > Enemy.game.getWidth())
                    toRemove.add(b);
                if (b.getY() > Enemy.game.getHeight())
                    toRemove.add(b);
                if (b.getX() < 0)
                    toRemove.add(b);
                if (b.getY() < 0)
                    toRemove.add(b);
                if (b.collides(tank) && !b.isPlayer()) {
                    toRemove.add(b);
                    thePlayer.setLives(thePlayer.getLives() - 1);
                    lives.setText("Lives: " + thePlayer.getLives());
                }
            }

                for (Enemy e : enemies) {
                    e.move();
                    for (Bullet b : allBullets) {
                        if (b.collides(e) && b.isPlayer()) {
                            newCoin = new Coin(e.getX(), e.getY(), 25, 25);
                            newCoin.setVisible(true);
                            add(newCoin, 0);
                            allCoins.add(newCoin);

                            toRemove.add(e);
                            toRemove.add(b);
                        }
                    }

                }

                for (Coin c : allCoins) {
                    if (tank.collides(c)) {
                        toRemove.add(c);
                        thePlayer.setScore(thePlayer.getScore() + 10);
                        shop.updateScore(thePlayer.getScore());
                        points.setText("Money: " + String.valueOf(thePlayer.getScore()));
                    }
                }
                for (Sprite s : toRemove) {
                    s.setVisible(false);
                    if (s instanceof Bullet)
                        allBullets.remove(s);
                    else if (s instanceof Enemy)
                        enemies.remove(s);
                    else if (s instanceof Coin)
                        allCoins.remove(s);
                    remove(s);
                }

                repaint();
                if (thePlayer.getLives() <= 0) {
                    gameOver();
                }
            }catch(ConcurrentModificationException e){}
        }
    }
}