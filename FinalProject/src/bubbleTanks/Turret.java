package bubbleTanks;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Turret extends Sprite implements MouseMotionListener {

    Sprite tank;
    double angle = 0.00;

    int mx, my;

    public Turret(Sprite t){
        super(0, 0, 100, 100, "src/resources/Lvl1Turret.png");
        tank = t;
    }

    public double getAngle(){
        return angle;
    }

    public void setAngle(double a){
        angle = a;
    }

    @Override
    public void move() {}

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        updateRotation();
    }

    protected void updateRotation(){
        int x = tank.getX()+tank.getWidth()/2;
        int y = tank.getY()+tank.getHeight()/2;
        angle = Math.atan2(x-mx, y-my);
        angle = 2 * Math.PI - angle;
    }

    public void paint(Graphics g)  {
        g.setClip(null);
        Graphics2D graphics2D = (Graphics2D)g.create();
        graphics2D.rotate(angle, getWidth()/2, getHeight()/2);
        graphics2D.drawImage(content, 0, 0, getWidth(), getHeight(), this);
        paintChildren(g);
        graphics2D.dispose();
    }
}
