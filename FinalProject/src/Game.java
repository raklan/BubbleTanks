
import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    GRectangle enviro;
    Tank tank;

    public Game()
    {
        super("Bubble Tanks");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        enviro = new GRectangle(getWidth()*(-1), getHeight()*(-1), getWidth()*3, getHeight()*3);
        enviro.setBackground(Color.CYAN);
        add(enviro, 0);

        tank = new Tank(100, 100, 100, 100);
        tank.setVisible(true);
        add(tank, 0);
        setVisible(true);
    }
}
