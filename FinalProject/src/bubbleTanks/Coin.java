package bubbleTanks;

public class Coin extends Sprite{

    public Coin(int x, int y, int w, int h){
        super(x,y,w,h, "src/resources/Coin.png");
    }



    //DO NOT CALL. COINS SHOULD NOT MOVE
    @Override
    public void move(){

    }
}
