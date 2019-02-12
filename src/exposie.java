import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.java.swing.plaf.windows.WindowsFileChooserUI;


public class exposie {
	public static String inputFileName = "images-3.jpeg";
	public String outputFileName;
	public static final images imgs = new images(inputFileName);
	public static File fi;
	public static ImageIcon img = new ImageIcon("logo.PNG");
	public static final JFrame f = new JFrame("حول صورة الى رسم");
	public static final JButton browser = new JButton("تصفح حاسوبك");
	public static final JButton sauver = new JButton("حفظ الصورة");
	public static final JPanel affichage= new JPanel();
	public static final JPanel buttons= new JPanel();
	public static final FlowLayout flw = new FlowLayout();
	public static final JTextField range = new JTextField("100",20);
	public static final JComboBox option = new JComboBox();
	public static String[] extensions = {"png", "jpg", "jpeg", "gif"};
	public static final FileNameExtensionFilter filter = new FileNameExtensionFilter(" png jpg jpej او gif", extensions);
	public static final JPanel accessory = new JPanel();
	public static final TitledBorder border = BorderFactory.createTitledBorder("صورة مصغرة");
	public static final JLabel icon = new JLabel();
	public static void main(String args[]) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, InterruptedException
	{
		f.setIconImage(img.getImage());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		f.setLocation(300,200);
		f.setSize(800,500);	
		f.setVisible(true);
		
		final MyJFileChooser fileName = new MyJFileChooser("اختر صورتك ");
		WindowsFileChooserUI wfl= new WindowsFileChooserUI(fileName);
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		wfl.installUI(fileName);
		flw.setAlignment(FlowLayout.RIGHT);
		buttons.setLayout(flw);
		buttons.add(range, BorderLayout.SOUTH);
		buttons.add(sauver);
		buttons.add(browser);
		
		range.setVisible(false);
		browser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				int status = fileName.showDialog(f, "اختيار الصورة ");
				final File file =fileName.getSelectedFile();
				if(file != null && status == JFileChooser.APPROVE_OPTION)
				{
					if(range.getText().equals(""))
					{
						range.setText("100");
					}
					int seill = 100 ;
					try{
						seill = Integer.parseInt(range.getText());
					}catch(NumberFormatException nfe)
					{
						JOptionPane.showMessageDialog(f, "entrer une numéro valide (nous changons la valeur au 100)");
					}
					inputFileName = file.getPath();
					imgs.inputFileName=inputFileName;
					imgs.rgb2bw(seill);
					imgs.show(affichage);
					affichage.repaint();
					imgs.ok=true;
					sauver.setVisible(true);
					range.setVisible(true);
				}
			}
		});
		
		range.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(range.getText().equals(""))
				{
					range.setText("100");
				}
				int seill = 100 ;
				try{
					seill = Integer.parseInt(range.getText());
					if(seill>255 || seill <0)
						{seill = 100;range.setText("100");}
				}catch(NumberFormatException nfe)
				{
					JOptionPane.showMessageDialog(f, "entrer une numéro valide (nous changons la valeur au 100)");
				}
				imgs.rgb2bw(seill);
				imgs.show(affichage);
				affichage.repaint();
				imgs.ok=true;
			}
		});
		sauver.setVisible(false);
		
		sauver.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ev)
			{
				if(imgs.ok)
				{
					int st = fileName.showDialog(f, "حفظ الصورة");
					File file = fileName.getSelectedFile();
					if(file != null && st == JFileChooser.APPROVE_OPTION)
					{
						try{
							if(!file.exists())
								ImageIO.write(imgs.out,file.getPath().substring(file.getPath().lastIndexOf(".")+1), file);
							else
							{
								int c = JOptionPane.showConfirmDialog(f, "هذا الاسم موجود من قبل هل تريد ان تقوم بلاستبدال ");
								if(c == 0 && imgs.out != null)
								{
									ImageIO.write(imgs.out,file.getPath().substring(file.getPath().lastIndexOf(".")+1), file);
								}else{
									actionPerformed(ev);
								}
							}
						}catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							System.out.print("exception");
						}
					}
				}else{
					System.out.print("لايمكنك الحفظ قبل معالجة صورتك");
				}
			}
		});
		
		
		ShowImage pan = new ShowImage(ImageIO.read(new File(inputFileName)));
		
		affichage.setLayout(new BoxLayout(affichage, BoxLayout.X_AXIS));
		affichage.add(pan);
		f.add(buttons, BorderLayout.PAGE_START);
		f.add(affichage, BorderLayout.CENTER);
		f.revalidate();
	}
}
