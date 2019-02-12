import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class images
{
	public BufferedImage in;
	public BufferedImage out;
	boolean ok=false;
	String inputFileName;
	public images (String  inputFileName)
	{
		this.inputFileName = inputFileName;
	}
	public void rgb2gray (JPanel affichage)
	{
		try{
			in = ImageIO.read(new File(inputFileName));
			out = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
			int g = 0, r,b,gr;
			Color c1;
			int min = 255, max = 0;
			
			//rgb2gray transformation$
			
			for(int i =0; i<in.getWidth(); i++)
			{
				for(int j=0; j<in.getHeight(); j++)
				{
					c1 = new Color(in.getRGB(i, j));
					in.setRGB(i,j,new Color(c1.getRed(),c1.getBlue(),c1.getGreen()).getRGB());
				}
			}
			
			
			
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.print("exception");
		}
	}
	
	public void rgb2bw (int seill)
	{
		try{
			in = ImageIO.read(new File(inputFileName));
			out = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
			int g = 0, r,b,gr;
			Color c1;
			int min = 255, max = 0;
			
			//rgb2gray transformation$
			
			for(int i =0; i<in.getWidth(); i++)
			{
				for(int j=0; j<in.getHeight(); j++)
				{
					c1 = new Color(in.getRGB(i, j));
					g = ((int) (c1.getRed()*0.299)+(int) (c1.getBlue()*0.587)+(int)(c1.getGreen()*0.114));
					out.setRGB(i,j,new Color((int)(c1.getRed()),(int)(c1.getBlue()),(int)(c1.getGreen())).getRGB());
				}
			}
			
			
			// max
			for(int i =2; i<out.getWidth()-2; i++)
			{
				for(int j=2; j<out.getHeight()-2; j++)
				{
					g = 0;
					c1 = null;
					c1 = new Color(out.getRGB(i, j));
					if(c1.getBlue()>seill)
						c1 = new Color(255,255,255);
					else
						c1 = new Color(out.getRGB(i, j));
					out.setRGB(i, j,c1.getRGB());
				}
			}
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.print("exception");
		}
	}
	
	public void show(JPanel affichage){
		if(out != null)
		{
			ShowImage pan0 = new ShowImage(in);
			ShowImage pan1 = new ShowImage(out);
			affichage.removeAll();
			affichage.add(pan0);
			affichage.add(pan1);
			affichage.repaint();
			affichage.revalidate();
		}else{
			JOptionPane.showMessageDialog(affichage, "لم تقم بكعالجة معالجة الصورة بعد");
		}
	}
}