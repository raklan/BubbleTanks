import java.awt.*;

public abstract class Sprite extends EzImage {
    protected int dx, dy, x, y;//variables for speed, for x and y
    protected Rectangle rec;

    //constructor, takes in speed and filepath for the icon
    public Sprite(int deltax, int deltay, String fpath){
        this(0,0,0,0,fpath);
        dx = deltax;
        dy = deltay;
        rec = new Rectangle(x,y,getWidth(),getHeight());
    }

    public Sprite(String path){
        this(0,0,path);
    }

    //default constructor.  if used, all variables need to be added manually with setters
    public Sprite(){
        super();
    }

    public Sprite(int x, int y, int width, int height, String s) {
        super(x,y,width,height,s);
        dx=0;
        dy=0;
        rec = new Rectangle(x,y,width,height);
    }

    //sets the speed
    public void setSpeed(int deltax, int deltay)
    {
        dx=deltax;
        dy=deltay;
    }

    public void setLocation(int x, int y){
        super.setLocation(x,y);
        rec.setLocation(x,y);
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        rec.setSize(width,height);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        rec = new Rectangle(x,y,width,height);
    }

    //sets the image, takes in filepath string
    public void changeImage(String path){
        this.setImage(path);
    }

    //moves the sprite, called every frame from the game
    public abstract void move();

}