import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.Font.*;

public class Shop extends JFrame implements ActionListener, KeyListener{

    private JLabel title;

    private JButton speedUpgrade;
    private JButton bulletSpeed;
    private JButton maxHP;
    private JButton life;

    private static int speedCost = 100;
    private static int bulletCost = 100;
    private static int HPCost = 100;
    private static int lifeCost = 50;

    Game game;

    public Shop(Game frame){
        super();
        game = frame;
        setBounds(frame.getX(),frame.getY(),frame.getWidth(),frame.getHeight());
        setVisible(false);
        setBackground(Color.RED);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        title = new JLabel("SHOP");
        title.setVisible(true);
        title.setBounds(0,0,frame.getWidth(), 100);
        title.setFont(new Font("Times New Roman", ITALIC, 100));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        speedUpgrade = new JButton("Upgrade Tank Speed \n Cost: "+speedCost);
        speedUpgrade.setBounds(100,200,400,100);
        speedUpgrade.setVisible(true);
        speedUpgrade.addActionListener(this);

        bulletSpeed = new JButton("Upgrade Bullet Speed  \n Cost: "+bulletCost);
        bulletSpeed.setBounds(500,200,400,100);
        bulletSpeed.setVisible(true);
        bulletSpeed.addActionListener(this);

        maxHP = new JButton("Upgrade Max HP \n Cost: "+HPCost);
        maxHP.setBounds(100,600,400,100);
        maxHP.setVisible(true);
        maxHP.addActionListener(this);

        life = new JButton("Buy Another Life \n Cost: "+lifeCost);
        life.setBounds(500,600,400,100);
        life.setVisible(true);
        life.addActionListener(this);

        add(speedUpgrade);
        add(bulletSpeed);
        add(maxHP);
        add(life);
        add(title,0);
        addKeyListener(this);
        setFocusable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==speedUpgrade) {
            Game.thePlayer.setSpeedBuff(Game.thePlayer.getSpeedBuff()+1);
            Game.thePlayer.setScore(Game.thePlayer.getScore()-speedCost);
            speedCost+=(speedCost/4);
            speedUpgrade.setText("Upgrade Tank Speed \n Cost: "+speedCost);
        }

        else if(e.getSource()==bulletSpeed) {
            Game.thePlayer.setBulletBuff(Game.thePlayer.getBulletBuff() + 1000000);
            Game.thePlayer.setScore(Game.thePlayer.getScore()-bulletCost);
            bulletCost+=(bulletCost/4);
            bulletSpeed.setText("Upgrade Bullet Speed  \n Cost: "+bulletCost);
        }

        else if(e.getSource()==maxHP) {
            Game.thePlayer.setMaxLives(Game.thePlayer.getMaxLives() + 1);
            Game.thePlayer.setScore(Game.thePlayer.getScore()-HPCost);
            HPCost+=(HPCost/4);
            maxHP.setText("Upgrade Max HP \n Cost: "+HPCost);
            life.setText("Buy Another Life \n Cost: " + lifeCost);
        }

        else if(e.getSource()==life) {
            if(Game.thePlayer.getLives()<Game.thePlayer.getMaxLives()){
                Game.thePlayer.setLives(Game.thePlayer.getLives() + 1);
                Game.thePlayer.setScore(Game.thePlayer.getScore() - lifeCost);
                lifeCost += (lifeCost / 4);
                life.setText("Buy Another Life \n Cost: " + lifeCost);
            }
            else{
                life.setText("Max Lives Reached!");
            }
        }
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
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            game.unpause();
        }
    }
}