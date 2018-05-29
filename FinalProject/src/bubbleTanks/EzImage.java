package bubbleTanks;//import java.awt.Toolkit.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class EzImage extends JComponent implements MouseListener {
//implements java.awt.image.ImageObserver, MouseListener  {
protected Image content;

// Constructor methods
	public EzImage()  {
		super();
		setBounds(0, 0, 10, 10);
	}

	public EzImage(int x, int y, int w, int h)  {
		super();
		setBounds(x, y, w, h);
	} 

	public EzImage(int x, int y, int w, int h, String s)  {
		super();
		setBounds(x, y, w, h);
        setImage(s);
	} 

// ----- will set the image to a new picture named s -------------------------------    
	public void setImage(String s)  {
//         java.net.URL url = getClass().getResource(s);  
//         if (url == null)   {
//             url = getClass().getResource("/"+s);
//             if (url == null)
               try {  
                    content = ImageIO.read(new File(s));
                } catch(IOException ioe) {
                    ioe.printStackTrace();
                }
//             else
//                 content = getToolkit().getImage(url);
//         } else
//             content = getToolkit().getImage(url);

	}

	public void paint(Graphics g)  {
		g.drawImage(content, 0, 0, getWidth(), getHeight(), this);
        paintChildren(g);
	}

	public void mouseClicked(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}


}

