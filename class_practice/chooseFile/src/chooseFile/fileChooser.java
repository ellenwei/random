package chooseFile;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class fileChooser extends JFrame{
	public fileChooser()
	{
		super("File chooser");
		setSize(300,400);
		setLocation(200,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton ch = new JButton("Choose file");
		add(ch, BorderLayout.CENTER);
		final JFileChooser fc = new JFileChooser();
		ch.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if (e.getSource() == ch)
				{
					int returnVal = fc.showOpenDialog(ch);
					if (returnVal == JFileChooser.APPROVE_OPTION)
					{
						File file = fc.getSelectedFile();
						//log.append("Opening: " + file.getName() + ".");
						System.out.println(file.getAbsolutePath());
					}
				}
			}
		});
		setVisible(true);
	}
	public static void main(String[] argv)
	{
		fileChooser fc = new fileChooser();
	}
	
}
