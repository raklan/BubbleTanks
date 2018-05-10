import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tank extends Sprite implements KeyListener {

    public Tank(int x, int y, int width, int height){
        super(x,y,width,height, "src/resources/Lvl1_Vert.png");
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
            case (KeyEvent.VK_UP):
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
