
import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    World world;
    Tank tank;

    public Game()
    {
        super("Bubble Tanks");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        world = new World();

        tank = new Tank(100, 100, 100, 100);
        tank.setVisible(true);
        add(tank, 0);
        setVisible(true);
    }
}
