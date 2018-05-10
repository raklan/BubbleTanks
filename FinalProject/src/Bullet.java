public class Bullet extends Sprite {

    public Bullet(int x, int y, int dy, int dx){
        super(x,y,50, 50, "src/resources/TankBullet.png");
        this.dy = dy;
        this.dx = dx;
    }

    @Override
    public void move() {
        setLocation(getX()+dx, getY()+dy);
    }
}
