import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

class ShowImage extends JPanel
{
	public BufferedImage imgIn ;
	public int max = 350;
	public void paint(Graphics g)
	{
		super.paint(g);
		if(imgIn.getWidth()>max)
			g.drawImage(imgIn, 0,0,350,350, this);
		else 
			g.drawImage(imgIn, 0,0, this);
	}
	public ShowImage(BufferedImage in)
	{
		super.paint(getGraphics());
		imgIn = in;
	}
}