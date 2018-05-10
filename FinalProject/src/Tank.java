public class Tank extends Sprite{

    public Tank(int x, int y, int width, int height){
        super(x,y,width,height, "src/resources/Lvl1.png");
    }

    @Override
    public void move() {
        x+=dx;
        y+=dy;
        setLocation(x,y);
    }
}
