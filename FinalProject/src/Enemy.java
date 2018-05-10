public class Enemy extends Tank{

    private final int id;

    public Enemy(int i, int x, int y){
        super(x,y);
        id = i;
    }

    public int getId(){
        return id;
    }
}
