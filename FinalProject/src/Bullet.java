public class Bullet extends Sprite {

    int speed = 10;
    int buff = 0;
    int dx;
    int dy;

    boolean player;

    public Bullet(int x, int y, double angle, boolean p, Player thePlayer){
        super(x,y,50, 50, "src/resources/TankBullet.png");
        dx = (int)(Math.cos(angle) * speed);
        dy = (int)(Math.sin(angle) * speed);
        player = p;
        if(p) {
            buff = thePlayer.getBulletBuff();
            addBuff();
        }
    }

    public boolean isPlayer(){
        return player;
    }

    public void setSpeed(int newSpeed){
        speed = newSpeed;
    }
    public int getSpeed(){
        return speed;
    }
    public void addBuff(){
        speed+=buff;
    }

    @Override
    public void move() {
        setLocation(getX()+dx, getY()+dy);
    }
}
