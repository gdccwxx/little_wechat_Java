package lib;

import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Lend extends JFrame {
	private JLabel l1,l2;
	private JTextField jt1,jt2;
	private JTextArea jt3;
	private JButton j1,j2;
	private JPanel jp1,jp2;
	
	public Lend()
	{		
	this.setSize(500, 260);
	this.setLayout(new GridLayout(2,0));
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		jp1 = new JPanel();
		jp2 = new JPanel();
	    l2= new JLabel("Wechat");
	    l1= new JLabel("ISBN");
	    jt1 = new JTextField();
	    jt2 = new JTextField();
	    jt3 = new JTextArea();
	    j1 = new JButton("借书");
	   // JScrollPane jsp = new JScrollPane(jt3);
	    jp1.setLayout(null);
	    jp2.setLayout(null);
        jp1.add(j1);
        jp1.add(jt1);
        jp1.add(l1);
        jp1.add(jt2);
        jp1.add(l2);
        jp2.add(jt3);	  
	    l1.setBounds(new Rectangle(45,30,50,20));
	    jt1.setBounds(new Rectangle(110,30,200,20));
	    //j1.setLocation(330,30);
	    j1.setBounds(new Rectangle(330,30,100,48));
		j1.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				String isbn = jt1.getText();
				String wechat=jt2.getText();
				GetPlaceByIp u= new GetPlaceByIp();
				Map<String,String> json = new HashMap<>();
				json.put("isbn",isbn);
				json.put("wechat",wechat);
				try {
					String x = u.sendPostData("http://localhost:8080/user/lendbook",json.toString());
					jt3.setText(x);
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}

		});
	    l2.setBounds(new Rectangle(45,60,50,20));
	    jt2.setBounds(new Rectangle(110,60,200,20));
	    jt3.setBounds(new Rectangle(10,10,460,95));
	    this.add(jp1);
	    this.add(jp2);
	    this.setTitle("借书");
	    this.setLocationRelativeTo(null);
	    //jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    this.setVisible(true);
	    this.setResizable(false);
	    this.addWindowListener(new WindowAdapter(){
	    	public void windowClosed(WindowEvent we)
	    	{ 
	    		mainclass cl = new mainclass();
	    	}
	    });	   
	
	}
	public static void main(String args[])
	{
		Lend l = new Lend();
	}

}



