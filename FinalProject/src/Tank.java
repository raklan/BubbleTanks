public class Tank extends Sprite{

    public Tank(){
        super("src/resources/Lvl1.png");
    }

    @Override
    public void move() {
        x+=dx;
        y+=dy;
        setLocation(x,y);
    }
}
