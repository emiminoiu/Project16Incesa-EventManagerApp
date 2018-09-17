package IncesaApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Encoder;
import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Vector;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import javax.swing.ImageIcon;

public class MainContent extends JFrame{
	public MenuPage mpage;
    public String path;
    public static Vector<String> v = new Vector<String>();
    public static Vector<Vector> rowData = new Vector<Vector>();
    public static Vector<String> rowOne = new Vector<String>();
    public static Vector<Vector> data = new Vector<Vector>();
    public static Vector<String> columns = new Vector<String>();
    public static Vector<String> myPlaces = new Vector<String>();
    public static Vector<String> myHours = new Vector<String>();
    public static Vector<String> myDescription = new Vector<String>();
	MainContent mc;
	private static final JLabel lblIncesaapp = new JLabel("IncesaApp");
	private String pattern = "dd.MM.yyyy";
	String dateInString =new SimpleDateFormat(pattern).format(new Date());
    public static Vector<String> myDates = new Vector<String>();
    public int Nr = -1;
    public int counter = 0;
    public MainContent(MenuPage page) throws ParserConfigurationException, SAXException, IOException{
    	MainContent mc = this;
        // create JFrame and JTable
        JFrame frame = new JFrame();
        mpage = page;
        
        System.out.println(dateInString);
        // create a table model and set a Column Identifiers to this model 
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
         DefaultHandler handler;
         handler = new DefaultHandler() {
             // private List<Personne> satff;
        	 boolean bdata = false;
        	 boolean btime = false;
        	 boolean bplace = false;
        	 boolean bdescription = false;
             private int i;

             public void startElement(String uri, String localName, String
                 qName, Attributes attributes) throws SAXException {
                 System.out.println("Start Element :" + qName);
                 if(qName.equalsIgnoreCase("event"))
                 {
                     rowOne = new Vector<String>();  
                     String id = attributes.getValue("id");
                    
                 }
                 if (qName.equalsIgnoreCase("data")) {
                     bdata = true;
                 }
                 if (qName.equalsIgnoreCase("time")) {
                     btime = true;
                 }
                 if (qName.equalsIgnoreCase("place")) {
                     bplace = true;
                 }
                 if (qName.equalsIgnoreCase("description")) {
                     bdescription = true;
                 }
                
             }

             public void endElement(String uri, String localName,
                 String qName) throws SAXException {
                 System.out.println("End Element :" + qName);
                 if ("event".equals(qName)) {
                     rowData.addElement(rowOne);
                 };
             }

             @Override
             public void characters(char ch[], int start, int length) {
                 if (bdata) {
                     String s = new String(ch, start, length);
                     rowOne.addElement(s);
                     System.out.println("Date : " + new String(ch, start, length));
                     bdata = false;
                     myDates.addElement(s);
                 }
                 if (btime) {
                	 String s = new String(ch, start, length);
                     rowOne.addElement(new String(ch, start, length));
                     System.out.println("Time : " + new String(ch, start, length));
                     btime = false;
                     myHours.addElement(s);
                 }
                 if (bplace) {
                	 String s = new String(ch, start, length);
                     rowOne.addElement(new String(ch, start, length));
                     System.out.println("Place : " + new String(ch, start, length));
                     bplace = false;
                     myPlaces.addElement(s);
                 }
                 if (bdescription) {
                	 String s = new String(ch, start, length);
                     rowOne.addElement(new String(ch, start, length));
                     System.out.println("Description : " + new String(ch, start, length));
                     //rowData.addElement(rowOne);
                     bdescription = false;
                     myDescription.addElement(s);
                 }
              
                 System.out.println("longueur" + rowOne.size());
             }
         };
           path = mpage.getLinkField();
     //    System.out.println("Message!!!!!!!" +path);
        //InputStream stream = new ByteArrayInputStream(path.getBytes(StandardCharsets.UTF_8));
        saxParser.parse(path, handler);
        // saxParser.parse("C:\\Users\\Minoiu Emi\\eclipse-workspace\\EventManagerFile.txt", handler);
         Vector<String> columnNames = new Vector<String>();
         columnNames.addElement("data");
         columnNames.addElement("time");
         columnNames.addElement("place");
         columnNames.addElement("description");
         DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
         JTable table = new JTable(rowData, columnNames);
         JScrollPane scrollPane = new JScrollPane(table);
       //  RowSorter<TableModel> sorter =  new TableRowSorter<TableModel>(model);
         //table.setRowSorter(sorter);
         frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        
   
        
        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.BLUE);
        table.setForeground(Color.GREEN);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(25);
        
        // create JTextFields
        JTextField date = new JTextField();
        JTextField time = new JTextField();
        JTextField place = new JTextField();
        JTextField description = new JTextField();
        
        // create JButtons
        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnAdd.setBackground(Color.RED);
        btnAdd.setForeground(Color.WHITE);
        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnDelete.setBackground(Color.RED);
        btnDelete.setForeground(Color.WHITE);
        JButton btnUpdate = new JButton("Update");     
        btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnUpdate.setBackground(Color.RED);
        btnUpdate.setForeground(Color.WHITE);
        JButton export = new JButton("ExportEvents");
        export.setFont(new Font("Tahoma", Font.BOLD, 16));
        date.setBounds(241, 291, 162, 40);
        time.setBounds(15, 291, 170, 40);
        place.setBounds(463, 291, 137, 40);
        description.setBounds(638, 291, 306, 40);
        
        btnAdd.setBounds(638, 349, 131, 25);
        btnUpdate.setBounds(463, 349, 137, 25);
        btnDelete.setBounds(241, 349, 162, 25);
        JButton btnCheckMyEvents = new JButton("Check my Events");
        btnCheckMyEvents.setBackground(Color.RED);
        btnCheckMyEvents.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnCheckMyEvents.setForeground(Color.WHITE);
       
        btnCheckMyEvents.setBounds(15, 347, 170, 29);
        frame.getContentPane().add(btnCheckMyEvents);

        export.setBackground(Color.RED);
        export.setForeground(Color.WHITE);
        export.setBounds(798, 349, 146, 27);
        frame.getContentPane().add(export);
        
        // create JScrollPane
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 42, 959, 181);
        
        frame.getContentPane().setLayout(null);
        
        frame.getContentPane().add(pane);
        
        // add JTextFields to the jframe
        frame.getContentPane().add(date);
        frame.getContentPane().add(time);
        frame.getContentPane().add(place);
        frame.getContentPane().add(description);
    
        // add JButtons to the jframe
        frame.getContentPane().add(btnAdd);
        frame.getContentPane().add(btnDelete);
        frame.getContentPane().add(btnUpdate);
        
                
        JLabel lblNewLabel = new JLabel("IncesaApp - EventManagerApp");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblNewLabel.setForeground(Color.RED);
        lblNewLabel.setBounds(336, 0, 385, 36);
        frame.getContentPane().add(lblNewLabel);
        
       
        
        JLabel lblDate = new JLabel("Date");
        lblDate.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblDate.setForeground(Color.RED);
        lblDate.setBounds(289, 250, 69, 25);
        frame.getContentPane().add(lblDate);
        
        JLabel lblTime = new JLabel("Time");
        lblTime.setForeground(Color.RED);
        lblTime.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblTime.setBounds(59, 255, 69, 20);
        frame.getContentPane().add(lblTime);
        
        JLabel lblPlace = new JLabel("Place");
        lblPlace.setForeground(Color.RED);
        lblPlace.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblPlace.setBounds(491, 257, 69, 20);
        frame.getContentPane().add(lblPlace);
        
        JLabel lblEventdescription = new JLabel("EventDescription");
        lblEventdescription.setForeground(Color.RED);
        lblEventdescription.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblEventdescription.setBounds(668, 243, 247, 40);
        frame.getContentPane().add(lblEventdescription);
        
        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon("C:\\Users\\Minoiu Emi\\Pictures\\50-Beautiful-and-Minimalist-Presentation-Backgrounds-02.jpg"));
        label.setBounds(0, 225, 959, 167);
        frame.getContentPane().add(label);
        
        JLabel label_1 = new JLabel("");
        label_1.setForeground(new Color(144, 238, 144));
        label_1.setIcon(new ImageIcon("C:\\Users\\Minoiu Emi\\Pictures\\50-Beautiful-and-Minimalist-Presentation-Backgrounds-02.jpg"));
        label_1.setBounds(0, 0, 959, 45);
        frame.getContentPane().add(label_1);
        
        // create an array of objects to set the row data
        Object[] row = new Object[4];
        
        // button add row
        btnAdd.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
             
                row[0] = date.getText();
                row[1] = time.getText();
                row[2] = place.getText();
                row[3] = description.getText();
                
                // add row to the model
                model.addRow(row);
                myDates.addElement((String) row[0]);
                myHours.addElement((String) row[1]);
                myPlaces.addElement((String) (row[2]));
                myDescription.addElement((String) row[3]);
            }
        });
        
        // button remove row
        btnDelete.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            
                // i = the index of the selected row
                int i = table.getSelectedRow();
                if(i >= 0){
                    // remove a row from jtable
                      model.removeRow(i);
                      myDates.remove(i);
                }
                else{
                    System.out.println("Delete Error");
                }
            }
        });
      
        export.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		

        		FileOutputStream fos;
				try {
					fos = new FileOutputStream("C:\\Users\\Minoiu Emi\\eclipse-workspace\\go.txt");
				
        		BufferedOutputStream bos = new BufferedOutputStream(fos);
				
        		XMLEncoder xmlEncoder = new XMLEncoder(bos);
				
        		xmlEncoder.writeObject(myHours);
        		xmlEncoder.writeObject(myPlaces);
        		xmlEncoder.writeObject(myDates);
        		xmlEncoder.writeObject(myDescription);
        		
        		xmlEncoder.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		 /*
        		    FileOutputStream fos;
					try {
						fos = new FileOutputStream("./go.txt");
					
        		    XMLEncoder encoder = new XMLEncoder(fos);
        		    encoder.setExceptionListener(new ExceptionListener() {
        		            public void exceptionThrown(Exception e) {
        		                System.out.println("Exception! :"+e.toString());
        		            }
        		    });
        		    encoder.writeObject(table);
        		    encoder.close();
        		    fos.close();
        		
        	} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			*/
        	}
        });
        table.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){
                
                // i = the index of the selected row
                int i = table.getSelectedRow();
                
                date.setText(model.getValueAt(i, counter).toString());
                counter++;
                time.setText(model.getValueAt(i, counter).toString());
                counter++;
                place.setText(model.getValueAt(i, counter).toString());
                counter++;
                description.setText(model.getValueAt(i, counter).toString());
                counter++;
            }
            });
           
            // button update row
            btnUpdate.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                 
                    // i = the index of the selected row
                    int i = table.getSelectedRow();
                    myDates.insertElementAt(date.getText(), i);
                    myHours.insertElementAt(time.getText(), i);
                    myPlaces.insertElementAt(place.getText(), i);
                    myDescription.insertElementAt(description.getText(), i);
                    if(i >= 0) 
                    {
                       model.setValueAt(date.getText(), i, 0);
                       model.setValueAt(time.getText(), i, 1);
                       model.setValueAt(place.getText(), i, 2);
                       model.setValueAt(description.getText(), i, 3);
                    }
                   
                    else{
                        System.out.println("Update Error");
                    }
                      model.fireTableDataChanged();
               //     model.fireTableChanged(null);
                }
            });
        btnCheckMyEvents.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("ARIAL",Font.PLAIN,25)));
        		UIManager.put("OptionPane.minimumSize",new Dimension(400,200)); 
        		UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(  
        		          "Arial", Font.BOLD, 18)));
        		UIManager.put("OptionPane.background", Color.GREEN);
        		UIManager.put("Button.background",Color.green); 
        		//UIManager.put("OptionPane.foreground", Color.BLUE);
        		
        		for(String i:myDates)
        		{   
        			int reply = 1;
        			Nr++;
        			if (i.equals(dateInString) )
        			{
        			
    				     //System.out.println(i);
            			// System.out.println(dateInString);
      			     reply = JOptionPane.showConfirmDialog(null,  "You have some event/events today!Do you want to show the location and hour?","IncesaApp-EventManagerApp", JOptionPane.YES_NO_OPTION,
                                 JOptionPane.PLAIN_MESSAGE);
      			    
        			 if (reply == JOptionPane.YES_OPTION)
    			       {
    			    	   JOptionPane.showConfirmDialog(null, "Your events are taking place in " + myPlaces.get(Nr) + " at " + myHours.get(Nr), "IncesaApp-EventManagerApp",JOptionPane.OK_CANCEL_OPTION,
                                 JOptionPane.PLAIN_MESSAGE);
    			       }
        			}
        		
        		}
        		Nr = -1;
        		
        	   
        	}
        	
        });
       
        // get selected row data From table to textfields 
       
      
        frame.setSize(981,448);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    
    }
}