
import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    World world;
    Tank tank;

    public Game()
    {
        super("Bubble Tanks");
        setBounds(50,50,1000, 1000);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Enemy en = new Enemy(0,100,100,500,500);
        en.setVisible(true);
        add(en);

        world = new World();
        add(world);

        setVisible(true);
    }
}
