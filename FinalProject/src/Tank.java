import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

public class
Tank extends Sprite implements KeyListener {

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;

    Player player;

    int dir; //0-7, every 45 deg, clockwise starting up

    Turret turret;
    Game win;

    public Tank(int x, int y, int width, int height, Game j, Player p){
        super(x,y,width,height, "src/resources/Lvl1Tank.png");
        addKeyListener(this);
        turret = new Turret(this);
        add(turret);
        win = j;
        player = p;
    }

    public Turret getTurret(){
        return turret;
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
            case KeyEvent.VK_W:
                if(!up){
                    up=true;
                    dy-=(10+player.getSpeedBuff());
                }
                break;

            case KeyEvent.VK_S:
                if(!down){
                    down=true;
                    dy+=(10+player.getSpeedBuff());
                }
                break;

            case KeyEvent.VK_A:
                if(!left){
                    left=true;
                    dx-=(10+player.getSpeedBuff());
                }
                break;

            case KeyEvent.VK_D:
                if(!right){
                    right=true;
                    dx+=(10+player.getSpeedBuff());
                }
                break;
        }
        setDirection();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode())
        {
            case KeyEvent.VK_W:
                up=false;
                dy+=(10+player.getSpeedBuff());
                break;

            case KeyEvent.VK_S:
                down=false;
                dy-=(10+player.getSpeedBuff());
                break;

            case KeyEvent.VK_A:
                left=false;
                dx+=(10+player.getSpeedBuff());
                break;

            case KeyEvent.VK_D:
                right=false;
                dx-=(10+player.getSpeedBuff());
                break;

            case KeyEvent.VK_G:
                win.pause();
                break;
        }
        setDirection();
    }

    public void resetKeys(){
        dx=0;
        dy=0;
        up=false;
        down=false;
        left=false;
        right=false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        double angle = turret.getAngle();
        angle = angle - Math.PI/2;
        win.addBullet(new Bullet(x+25, y+25, angle, true, player));
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
