import java.awt.*;

public abstract class Sprite extends EzImage {
    protected int dx, dy, x, y;//variables for speed, for x and y

    //constructor, takes in speed and filepath for the icon
    public Sprite(int deltax, int deltay, String fpath){
        super();
        this.changeImage(fpath);
        dx = deltax;
        dy = deltay;
        x=0;
        y=0;
    }

    public Sprite(String path){
        this(0,0,path);
    }

    //default constructor.  if used, all variables need to be added manually with setters
    public Sprite(){
        super();
    }

    //sets the speed
    public void setSpeed(int deltax, int deltay)
    {
        dx=deltax;
        dy=deltay;
    }

    //sets the image, takes in filepath string
    public void changeImage(String path){
        this.setImage(path);
    }

    //moves the sprite, called every frame from the game
    public abstract void move();

}