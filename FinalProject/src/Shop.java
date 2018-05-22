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

    Tank player;

    public Shop(JFrame frame, Tank tank){
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

        speedUpgrade = new JButton("Upgrade Tank Speed");
        speedUpgrade.setBounds(100,200,200,100);
        speedUpgrade.setVisible(true);
        speedUpgrade.addActionListener(this);

        bulletSpeed = new JButton("Upgrade Bullet Speed");
        bulletSpeed.setBounds(700,200,200,100);
        bulletSpeed.setVisible(true);
        bulletSpeed.addActionListener(this);

        maxHP = new JButton("Upgrade Max HP");
        maxHP.setBounds(100,600,200,100);
        maxHP.setVisible(true);
        maxHP.addActionListener(this);

        life = new JButton("Buy Another Life");
        life.setBounds(700,600,200,100);
        life.setVisible(true);
        life.addActionListener(this);

        player = tank;

        add(speedUpgrade);
        add(bulletSpeed);
        add(maxHP);
        add(life);
        add(title,0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==speedUpgrade)
            player.setSpeed(player.getDx()+1, player.getDy()+1);
        else if(e.getSource()==bulletSpeed)
            System.out.println("Bullet");
        else if(e.getSource()==maxHP)
            System.out.println("HP");
        else if(e.getSource()==life)
            System.out.println("Life");
    }
}
