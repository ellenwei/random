import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class handleFiles extends JFrame{
	
	JTextArea jText = new JTextArea();
	JPanel main = new JPanel();
	JTabbedPane tabbedPane = new JTabbedPane();
	
	JPanel textPanel = new JPanel();
	
	{	
		main.setLayout(new BorderLayout());
		main.add(tabbedPane,BorderLayout.CENTER);
		getContentPane().add(main);
		 createMenuBar();
		 
	        setTitle("201 Office");
	        setSize(640, 480);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void createMenuBar() {
		
        JMenuBar menubar = new JMenuBar();
       
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenuItem newItem = new JMenuItem("New");
        file.add(newItem);
        newItem.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent ae)
        	{
        		System.out.println("Insider actionlistener new");
        		
        		
        		textPanel.setLayout(new BorderLayout()); 		
        		textPanel.add(jText, BorderLayout.CENTER);
        		
        		tabbedPane.add("New",textPanel);
        		
        		
        	      		
        	}
        });
        
        JMenuItem openItem = new JMenuItem("Open");
        file.add(openItem);
        
        JMenuItem saveItem = new JMenuItem("Save");
        file.add(saveItem);
        
        
        JMenuItem eMenuItem = new JMenuItem("Close");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit application");
        eMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        file.add(eMenuItem);
        
        
        
        menubar.add(file);

        setJMenuBar(menubar);
    }

	public static void main(String[] argv)
	{
		/*
		 EventQueue.invokeLater(new Runnable() {

	            @Override
	            public void run() {*/
	                handleFiles ex = new handleFiles();
	                
	                ex.setVisible(true);
	                /*
	            }
	        });*/
	    }
	}

