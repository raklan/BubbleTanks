
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
public class Game extends JFrame {

    GRectangle enviro;
    Tank tank;

    ArrayList<Enemy> enemies = new ArrayList<>();

    Timer t = new Timer();
    int fps = 40;

    public Game()
    {
        super("Bubble Tanks");
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        enviro = new GRectangle(getWidth()*(-1), getHeight()*(-1), getWidth()*3, getHeight()*3);
        enviro.setBackground(Color.CYAN);
        add(enviro, 0);

        tank = new Tank(100, 100, 100, 100);
        tank.setFocusable(true);
        add(tank, 0);

        //Instantiates X number of enemy tanks. Change the i<=X to add more enemies
        for(int i = 1; i<=10; i++){
            enemies.add(new Enemy(i*50,i*50,100,100,this));
        }
        for(Enemy e: enemies){
            e.setVisible(true);
            add(e,0);
        }

        t.schedule(new MyTimerTask(), 0, 1000/fps);
        setVisible(true);
    }

    class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            tank.move();
            for(Enemy e: enemies)
                e.move();
        }
    }
}
