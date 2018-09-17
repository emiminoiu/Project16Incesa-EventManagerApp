package IncesaApp;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.JTree;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class MenuPage extends JFrame {
    public MenuPage mpage;
	private JPanel contentPane;
	public  JTextField linkField;
	private String fileName;
	/**
	 * Launch the application.
	 */
	MainContent mc;
	public MenuPage getInstance()
	{
		return mpage;
	}
	public String getLinkField()
	{
	return linkField.getText();
	}
	/**
	 * Create the frame.
	 */
	public MenuPage() {
	    MenuPage mpage = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 441);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 255, 0));
		contentPane.setForeground(new Color(124, 252, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMainmenu = new JLabel("MainMenu");
		lblMainmenu.setForeground(Color.RED);
		lblMainmenu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMainmenu.setBackground(Color.CYAN);
		lblMainmenu.setBounds(155, 49, 95, -9);
		contentPane.add(lblMainmenu);
		
		JLabel lblEnterTheFile = new JLabel("Drag&Drop your Xml File");
		lblEnterTheFile.setForeground(new Color(0, 255, 0));
		lblEnterTheFile.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 30));
		lblEnterTheFile.setBounds(147, 16, 417, 60);
		contentPane.add(lblEnterTheFile);
		
		JLabel lblLink = new JLabel("Link");
		lblLink.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblLink.setForeground(new Color(0, 255, 0));
		lblLink.setBounds(15, 124, 60, 60);
		contentPane.add(lblLink);
		
		linkField = new JTextField();
		linkField.setForeground(Color.BLUE);
		linkField.setBounds(90, 124, 527, 60);
		contentPane.add(linkField);
		linkField.setColumns(10);
		
		linkField.setDropTarget(new DropTarget() {
	        public synchronized void drop(DropTargetDropEvent evt) {
	            try {
	                evt.acceptDrop(DnDConstants.ACTION_COPY);
	                List<File> droppedFiles = (List<File>) evt
	                        .getTransferable().getTransferData(
	                                DataFlavor.javaFileListFlavor);
	                for (File file : droppedFiles) {
	                    /*
	                     * NOTE:
	                     *  When I change this to a println,
	                     *  it prints the correct path
	                     */
	                    linkField.setText(file.getAbsolutePath());
	                }
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	    });
			  		
		
		JButton btnNext = new JButton("NEXT");
		btnNext.addActionListener( new ActionListener()
    	{
         public void actionPerformed(ActionEvent x)
         {
        
        	 
			 try {
				MainContent mc = new MainContent(mpage);
				mc.setVisible(true);
			} catch (ParserConfigurationException | SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
        	 MenuPage.this.setVisible(false);
         }
    	});
		btnNext.setBackground(Color.RED);
		btnNext.setForeground(Color.WHITE);
		btnNext.setBounds(88, 249, 143, 52);
		contentPane.add(btnNext);
		
		JButton btnClose = new JButton("CLOSE");
		btnClose.setBackground(Color.RED);
		 btnClose.addActionListener( new ActionListener()
	    	{
	         public void actionPerformed(ActionEvent x)
	         {
	        	 System.exit(0);
	         }
	    	});
		btnClose.setForeground(Color.WHITE);
		btnClose.setBounds(474, 249, 143, 52);
		contentPane.add(btnClose);
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Minoiu Emi\\Pictures\\pexels-photo.jpg"));
		label.setBounds(0, 0, 696, 385);
		contentPane.add(label);
		
	}
}