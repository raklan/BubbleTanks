import javax.swing.*;

public class Tank extends Sprite{

    private JLabel tur;

    public Tank(){
        super("src/resources/Lvl1_Vert.png");
        tur = new JLabel(new ImageIcon("src/resources/Lvl1_Vert.png"));
        add(tur);
        tur.setVisible(true);
        tur.setBounds(0,0,this.getWidth(), this.getHeight());
    }

    public Tank(int x, int y){
        super("src/resources/Lvl1_Vert.png");
        tur = new JLabel(new ImageIcon("src/resources/Lvl1_Vert.png"));
        add(tur);
        tur.setVisible(true);
        tur.setBounds(0,0,this.getWidth(), this.getHeight());
        setLocation(x,y);

    }

    @Override
    public void move() {
        setLocation(x+dx,y+dy);
    }
}
