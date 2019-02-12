import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.sun.java.swing.plaf.windows.WindowsFileChooserUI;


public class MyJFileChooser extends JFileChooser{
	public static File fi;
	public static String[] extensions = {"png", "jpg", "jpeg", "gif"};
	public static final FileNameExtensionFilter filter = new FileNameExtensionFilter(" png jpg jpej او gif", extensions);
	public static final JPanel accessory = new JPanel();
	public static final TitledBorder border = BorderFactory.createTitledBorder("صورة مصغرة");
	public static final JLabel icon = new JLabel();
	public MyJFileChooser(String title) throws  InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		super(title);
		// pour le fenetre de choi de l'image 
		
		
		addChoosableFileFilter(filter);
		setAcceptAllFileFilterUsed(false);
		
		accessory.setLayout(new BorderLayout());
		accessory.setPreferredSize(new Dimension(150,250));
		
		border.setTitleJustification(TitledBorder.RIGHT);
		accessory.setBorder(border);
		
		accessory.add(icon, BorderLayout.CENTER);
		setAccessory(accessory);
		final JFileChooser fc = this;
		
		
		
		
		
		addPropertyChangeListener(new PropertyChangeListener(){
			public void propertyChange(PropertyChangeEvent pe)
			{
				if(pe.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) && fc.getSelectedFile()!= null)
				{
					fi = fc.getSelectedFile();
					if(fi.getPath().substring(fi.getPath().lastIndexOf(".")+1).equalsIgnoreCase("gif") || fi.getPath().substring(fi.getPath().lastIndexOf(".")+1).equalsIgnoreCase("jpg") || fi.getPath().substring(fi.getPath().lastIndexOf(".")+1).equalsIgnoreCase("jpeg") || fi.getPath().substring(fi.getPath().lastIndexOf(".")+1).equalsIgnoreCase("png"))
					{
						icon.setIcon(new ImageIcon(new ImageIcon(fi.getPath()).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
					}
				}
			}
		});
		// end fenetre de choi de limage 
	}
}
