import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tank extends Sprite implements KeyListener {

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;

    public Tank(int x, int y, int width, int height){
        super(x,y,width,height, "src/resources/Lvl1Tank.png");
        addKeyListener(this);
    }

    @Override
    public void move() {
        x+=dx;
        y+=dy;
        setLocation(x,y);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                if(!up){
                    up=true;
                    dy-=10;
                }
                break;

            case KeyEvent.VK_DOWN:
                if(!down){
                    down=true;
                    dy+=10;
                }
                break;

            case KeyEvent.VK_LEFT:
                if(!left){
                    left=true;
                    dx-=10;
                }
                break;

            case KeyEvent.VK_RIGHT:
                if(!right){
                    right=true;
                    dx+=10;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_UP:
                up=false;
                dy+=10;
                break;

            case KeyEvent.VK_DOWN:
                down=false;
                dy-=10;
                break;

            case KeyEvent.VK_LEFT:
                left=false;
                dx+=10;
                break;

            case KeyEvent.VK_RIGHT:
                right=false;
                dx-=10;
                break;
        }
    }
}
