import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.Font.*;

public class Shop extends JFrame implements ActionListener, KeyListener{

    private JLabel title;

    protected static JButton speedUpgrade;
    protected static JButton bulletSpeed;
    protected static JButton maxHP;
    protected static JButton life;

    protected static int speedCost = 100;
    protected static int bulletCost = 100;
    protected static int HPCost = 100;
    protected static int lifeCost = 50;

    Game game;
    Player player;

    JLabel points;

    int buttonWidth = 400;
    int buttonSpace;

    public Shop(Game frame){
        super();
        setUndecorated(true);
        getContentPane().setBackground(new Color(160, 157, 164, 139));
        setBackground(new Color(160, 157, 164, 139));
        game = frame;
        player = game.getPlayer();
        setBounds(frame.getX(),frame.getY(),frame.getWidth(),frame.getHeight());
        setVisible(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        buttonSpace = (getWidth()-buttonWidth*2)/3;

        title = new JLabel("SHOP");
        title.setVisible(true);
        title.setBounds(0,0,frame.getWidth(), 100);
        title.setFont(new Font("Times New Roman", ITALIC, 100));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBackground(new Color(252, 246, 20, 225));
        title.setOpaque(true);

        points = new JLabel("Coins: " + player.getScore());
        points.setBounds(getWidth()/2-50, getHeight()/2-50, 100, 50);
        points.setVisible(true);
        points.setFont(new Font("Times New Roman", ITALIC, 20));
        points.setHorizontalAlignment(SwingConstants.CENTER);
        points.setBackground(Color.white);
        points.setOpaque(true);

        speedUpgrade = new JButton("Upgrade Tank Speed \n Cost: "+speedCost);
        speedUpgrade.setBounds(buttonSpace,200,buttonWidth,100);
        speedUpgrade.setVisible(true);
        speedUpgrade.addActionListener(this);

        bulletSpeed = new JButton("Upgrade Bullet Speed  \n Cost: "+bulletCost);
        bulletSpeed.setBounds(buttonWidth+buttonSpace*2,200,buttonWidth,100);
        bulletSpeed.setVisible(true);
        bulletSpeed.addActionListener(this);

        maxHP = new JButton("Upgrade Max HP \n Cost: "+HPCost);
        maxHP.setBounds(buttonSpace,600,buttonWidth,100);
        maxHP.setVisible(true);
        maxHP.addActionListener(this);

        life = new JButton("Buy Another Life \n Cost: "+lifeCost);
        life.setBounds(buttonWidth+buttonSpace*2,600,buttonWidth,100);
        life.setVisible(true);
        life.addActionListener(this);

        add(speedUpgrade);
        add(bulletSpeed);
        add(maxHP);
        add(life);
        add(title,0);
        add(points, 0);
        addKeyListener(this);
        setFocusable(true);
    }

    public void updateScore(long p){
        points.setText("Coins: "+p);
    }

    public void reset(){
        speedCost = 100;
        bulletCost = 100;
        HPCost = 100;
        lifeCost = 50;
        speedUpgrade.setText("Upgrade Tank Speed \n Cost: "+speedCost);
        bulletSpeed.setText("Upgrade Bullet Speed  \n Cost: "+bulletCost);
        maxHP.setText("Upgrade Max HP \n Cost: "+HPCost);
        life.setText("Buy Another Life \n Cost: " + lifeCost);
        points.setText("Coins: "+player.getScore());
        game.updatePoints(player.getScore());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==speedUpgrade) {
            if(player.getScore()>=speedCost) {
                player.setSpeedBuff(player.getSpeedBuff() + 1);
                player.setScore(player.getScore() - speedCost);
                speedCost += (speedCost / 4);
                speedUpgrade.setText("Upgrade Tank Speed \n Cost: " + speedCost);
            }else{
                speedUpgrade.setText("Not Enough Money!");
            }
        }

        else if(e.getSource()==bulletSpeed) {
            if(player.getScore()>=bulletCost) {
                player.setBulletBuff(player.getBulletBuff() + 10);
                player.setScore(player.getScore() - bulletCost);
                bulletCost += (bulletCost / 4);
                bulletSpeed.setText("Upgrade Bullet Speed  \n Cost: " + bulletCost);
            }else{
                bulletSpeed.setText("Not Enough Money!");
            }
        }

        else if(e.getSource()==maxHP) {
            if(player.getScore()>=HPCost) {
                player.setMaxLives(player.getMaxLives() + 1);
                player.setScore(player.getScore() - HPCost);
                HPCost += (HPCost / 4);
                maxHP.setText("Upgrade Max HP \n Cost: " + HPCost);
                life.setText("Buy Another Life \n Cost: " + lifeCost);
            }else{
                maxHP.setText("Not Enough Money!");
            }
        }

        else if(e.getSource()==life) {
            if(player.getScore()>=lifeCost){
                if(player.getLives()<player.getMaxLives()){
                    player.setLives(player.getLives() + 1);
                    player.setScore(player.getScore() - lifeCost);
                    lifeCost += (lifeCost / 4);
                    life.setText("Buy Another Life \n Cost: " + lifeCost);
                }
                else {
                    life.setText("Max Lives Reached!");
                }
            }else{
                life.setText("Not Enough Money!");
            }
        }
        points.setText("Coins: "+player.getScore());
        game.updatePoints(player.getScore());
        requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_G){
            game.unpause();
            setVisible(false);
        }
    }
}