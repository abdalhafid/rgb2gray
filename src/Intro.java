import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Intro extends JPanel{
	JFrame window = new JFrame();
	File img = new File("IntroBackground.png");
	public Intro() throws InterruptedException
	{
		setLayout(null);
		JLabel btn2Gray = new JLabel("rgb hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		JPanel btn2GrayPane = new JPanel();
		JLabel btn2Draw = new JLabel("draw");
		/*btn2Gray.setIcon(new ImageIcon("btn2RGray.png"));
		btn2Draw.setIcon(new ImageIcon("btn2Draw.png"));*/
		setBounds(0,0,800,600);
		btn2Gray.setBackground(new Color(255, 255, 255));
		btn2Draw.setBackground(new Color(255, 255, 255));
		btn2GrayPane.setBounds(240, 160, 320, 80);
		btn2GrayPane.setLayout(null);
		btn2GrayPane.add(btn2Gray);
		btn2Gray.setBounds(240, 160, 320, 80);
		btn2Draw.setBounds(240, 260, 320, 80);
		//add(btn2Gray);
		add(btn2Draw);
		window.setLayout(null);
		window.setSize(800,629);
		window.add(this);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		BufferedImage bg;
		try {
			bg = ImageIO.read(img);
			g.drawImage(bg, 0, 0, 800, 600, this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
