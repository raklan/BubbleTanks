import java.util.ArrayList;

public class World {
    private ArrayList<Enemy> enemies;
    private GRectangle environment;

    public World(){
        for(int i=0; i<10; i++){
            enemies.add(new Enemy(i, i*10, i*10));
        }
    }
}
