import javax.swing.*;
import java.awt.*;

public class Tank extends Sprite{

    private JLabel tur;

    public Tank(){
        super("src/resources/Lvl1_Vert.png");
        tur = new JLabel(new ImageIcon("src/resources/Lvl1Turret.png"));
        add(tur,0);
        tur.setVisible(true);
        tur.setBounds(0,0,this.getWidth(), this.getHeight());
        repaint();
    }

    public Tank(int x, int y, int w, int h){
        super("src/resources/Lvl1_Vert.png");
        setBounds(x,y,w,h);
        setLayout(null);
        tur = new JLabel(new ImageIcon("src/resources/TurretLvl1.png"));
        tur.setVisible(true);
        tur.setBounds(0,0,w*10,h*10);
        this.add(tur,0);
        repaint();
    }

    public void setSize(int w, int h){
        super.setSize(w, h);
        tur.setSize(this.getWidth(), this.getHeight());
    }

    @Override
    public void move() {
        setLocation(x+dx,y+dy);
    }
}
