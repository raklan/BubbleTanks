import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class World extends JComponent{
    private ArrayList<Enemy> enemies;
    private GRectangle environment;

    public World(){
        super();

        setVisible(true);
        setBounds(0,0,1000,1000);

        environment = new GRectangle(0,0,1000,1000);
        environment.setVisible(true);
        environment.setBackground(Color.CYAN);
        add(environment);

        enemies = new ArrayList<>();
        for(int i=0; i<10; i++){
            enemies.add(new Enemy(i, i*30, i*30,50,50));
            enemies.get(i).setVisible(true);
            environment.add(enemies.get(i));
        }
    }
}
