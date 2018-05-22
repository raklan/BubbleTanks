public class Bullet extends Sprite {

    int speed = 10;
    int dx;
    int dy;

    boolean player;

    public Bullet(int x, int y, double angle, boolean p){
        super(x,y,50, 50, "src/resources/TankBullet.png");
        dx = (int)(Math.cos(angle) * speed);
        dy = (int)(Math.sin(angle) * speed);
        player = p;
    }

    @Override
    public void move() {
        setLocation(getX()+dx, getY()+dy);
    }
}
