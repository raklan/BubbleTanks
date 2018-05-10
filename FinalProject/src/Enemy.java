public class Enemy extends Tank{

    private final int id;

    public Enemy(int i, int x, int y, int w, int h){
        super(x,y,w,h);
        id = i;
    }

    public int getId(){
        return id;
    }
}
