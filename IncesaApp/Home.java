package IncesaApp;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Button;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.*;
public class Home extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	int ok = 0;
	int xx,xy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 Home frame = new Home();
					 frame.setUndecorated(true);
					 frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	

	/**
	 * Create the frame.
	 */
	public Home() throws Exception{
		
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 469);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		username = new JTextField();
		username.setBounds(407, 121, 283, 36);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsername.setForeground(Color.GREEN);
		lblUsername.setBounds(407, 81, 114, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setForeground(Color.GREEN);
		lblPassword.setBounds(405, 190, 116, 27);
		contentPane.add(lblPassword);
		
		password = new JPasswordField();
		password.setBounds(407, 234, 283, 36);
		contentPane.add(password);
		
		Button button = new Button("Login");
		button.setActionCommand("LogIn");
		button.addActionListener( new ActionListener()
    	{
         public void actionPerformed(ActionEvent x)
         {
        	 Component frame = null;
        	 String uname = username.getText();
        	 String pass = password.getText();
        	 Connection con = null;
     		 PreparedStatement pst = null;
     		 ResultSet rs = null;
     	
        	try {
        		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        			// 1. Get a connection to database
        			
        			//DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
        		//	Class.forName("org.h.jdbcDriver");
        			String sql="select * from users where username=? and password=?";
        			try{
        			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login?user=student1&password=student1&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        			pst=con.prepareStatement(sql);
        			pst.setString(1, username.getText());
        			pst.setString(2,password.getText());
        			rs=pst.executeQuery();
        			
        			}
        			catch(SQLException | HeadlessException ex)
        			{
        			JOptionPane.showMessageDialog(null,ex);
        			}
        			
        			// 2. Create a statement
        			//myStmt = myConn.createStatement();
        			
        			// 3. Execute SQL query
        			//myRs = myStmt.executeQuery("select * from users where username = ? and password = ? ");
        			
        			// 4. Process the result set
        			try {
        				if(rs.next()) {
        					        	    
        					 JOptionPane.showMessageDialog(frame,"You are successfully logged");
        					 //TypeFileSelection tsf = new TypeFileSelection();
        				//	 tsf.setVisible(true);
        					
        						MenuPage mp = new MenuPage();
        						mp.setVisible(true);
        					
        					 
        				     Home.this.setVisible(false);
        				 }
        				 else
        				 {
        					 JOptionPane.showMessageDialog(frame,"Login failed");
        				 }
        			} catch (HeadlessException | SQLException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        			
        		}
        		catch (Exception exc) {
        			exc.printStackTrace();
        		}
        		finally {
        			
        			if (rs != null) {
        				try {
							rs.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        			}
        			
        			if (pst != null) {
        				try {
							pst.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        			}
        			
        			if (con != null) {
        				try {
							con.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        			}
        			
        		}
        		
        	
        	 /*if(uname.equals("parsing")&&(pass.equals("emimi98")))
        	 {
        	    
				 JOptionPane.showMessageDialog(frame,"You are successfully logged");
				 //TypeFileSelection tsf = new TypeFileSelection();
			//	 tsf.setVisible(true);
        	     Home.this.setVisible(false);
        	 }
        	 else
        	 {
        		 JOptionPane.showMessageDialog(frame,"Login failed");
        	 }
         }
         */
        	try {
				if(rs.next()) {
					        	    
					 JOptionPane.showMessageDialog(frame,"You are successfully logged");
					 //TypeFileSelection tsf = new TypeFileSelection();
				//	 tsf.setVisible(true);
					
						MenuPage mp = new MenuPage();
						mp.setVisible(true);
					
					 
				     Home.this.setVisible(false);
				 }
				 else
				 {
					 JOptionPane.showMessageDialog(frame,"Login failed");
				 }
			} catch (HeadlessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

         }
         
    	});
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(241, 57, 83));
		button.setBounds(407, 339, 283, 36);
		contentPane.add(button);
		
		
		
		JLabel lbl_close = new JLabel("X");
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				System.exit(0);
			}
		});
		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_close.setForeground(new Color(241, 57, 83));
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_close.setBounds(691, 0, 37, 27);
		contentPane.add(lbl_close);
		
		JLabel lblIncesaapp = new JLabel("IncesaApp");
		lblIncesaapp.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblIncesaapp.setForeground(Color.RED);
		lblIncesaapp.setBounds(395, 16, 213, 36);
		contentPane.add(lblIncesaapp);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Minoiu Emi\\Pictures\\abstract-1392404_960_720.jpg"));
		lblNewLabel.setBounds(350, -17, 392, 510);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Minoiu Emi\\Pictures\\batmanarkhamorigins_prop_hacking_device_by_virgile_loth_additions_01.jpg"));
		label.setBounds(0, 0, 354, 556);
		contentPane.add(label);
	}
}
