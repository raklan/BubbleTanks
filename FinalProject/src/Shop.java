import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.*;

public class Shop extends JFrame implements ActionListener{

    private JLabel title;

    private JButton speedUpgrade;
    private JButton bulletSpeed;
    private JButton maxHP;
    private JButton life;

    private static int speedCost = 100;
    private static int bulletCost = 100;
    private static int HPCost = 100;
    private static int lifeCost = 50;

    Tank tank;
    Player player;

    public Shop(JFrame frame, Tank t, Player p){
        super();
        setBounds(frame.getX(),frame.getY(),frame.getWidth(),frame.getHeight());
        setVisible(true);
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

        tank = t;
        player = p;

        add(speedUpgrade);
        add(bulletSpeed);
        add(maxHP);
        add(life);
        add(title,0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==speedUpgrade) {
            tank.setSpeed(tank.getDx() + 1, tank.getDy() + 1);
            player.setScore(player.getScore()-speedCost);
            speedCost+=(speedCost/4);
            speedUpgrade.setText("Upgrade Tank Speed \n Cost: "+speedCost);
        }

        else if(e.getSource()==bulletSpeed) {
            player.setBulletBuff(player.getBulletBuff() + 1);
            player.setScore(player.getScore()-bulletCost);
            bulletCost+=(bulletCost/4);
            bulletSpeed.setText("Upgrade Bullet Speed  \n Cost: "+bulletCost);
        }

        else if(e.getSource()==maxHP) {
            player.setMaxLives(player.getMaxLives() + 1);
            player.setScore(player.getScore()-HPCost);
            HPCost+=(HPCost/4);
            maxHP.setText("Upgrade Max HP \n Cost: "+HPCost);
        }

        else if(e.getSource()==life) {
            player.setLives(player.getLives() + 1);
            player.setScore(player.getScore()-lifeCost);
            lifeCost+=(lifeCost/4);
            life.setText("Buy Another Life \n Cost: "+lifeCost);
        }
    }
}