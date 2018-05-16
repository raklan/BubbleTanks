import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class
Tank extends Sprite implements KeyListener {

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;

    int dir; //0-7, every 45 deg, clockwise starting up

    public Tank(int x, int y, int width, int height){
        super(x,y,width,height, "src/resources/InkedLvl1Tank_LI.jpg");
        addKeyListener(this);
    }

    private void setDirection(){
        //if both sides are pressed, they cancel each other out
        boolean upchanged = false;
        if(up&&down){
            up=false;
            down=false;
            upchanged = true;
        }
        boolean rightchanged = false;
        if(left&&right){
            left=false;
            right=false;
            rightchanged=true;
        }

        //sets the direction
        if(right){
            if(up)
                dir=1;
            else if(down)
                dir=3;
            else
                dir=2;
        }else if(left){
            if(up)
                dir=7;
            else if(down)
                dir=5;
            else
                dir=6;
        }else if(up)
            dir=0;
        else if(down)
            dir=4;

        //resets the ones that canceled out for physics purposes
        if(upchanged){
            up=true;
            down=true;
        }
        if(rightchanged){
            left=true;
            right=true;
        }
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
        setDirection();
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
        setDirection();
    }

    public void paint(Graphics g)  {
        g.setClip(null);
        Graphics2D graphics2D = (Graphics2D)g.create();
        graphics2D.rotate(Math.toRadians(dir*45), getWidth()/2, getHeight()/2);
        graphics2D.drawImage(content, 0, 0, getWidth(), getHeight(), this);
        paintChildren(g);
        graphics2D.dispose();
    }
}
