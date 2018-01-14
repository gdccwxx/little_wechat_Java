package lib;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.sf.json.JSONObject;



    public class addbook extends JFrame {
	/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
	private JLabel l1,l2;
	private JTextField jt1,jt2;
	private JTextArea jt3;
	private JButton j1,j2;
	
	public addbook()
	{
		
		this.setSize(500,300);
		this.setLayout(new GridLayout(2,0));
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
	    l1 = new JLabel("ISBN");
	    l2 = new JLabel("Count");
	    jt1 = new JTextField();
	    jt2 = new JTextField();
	    jt3 = new JTextArea();
	    JPanel panelOutput;
	    panelOutput = new JPanel();
	    //panelOutput.add(new JScrollPane(jt3));
	    jt3.add(new JScrollPane(panelOutput));
	    //换行
	    jt3.setLineWrap(true);
	    jt3.setWrapStyleWord(true);
	    j1 = new JButton("增书");
	    j2 = new JButton("查书");
       this.add(j1);
       this.add(jt1);
       this.add(l1);
       this.add(j2);
       this.add(jt2);
       this.add(l2);
       this.add(jt3);	  
	    l1.setBounds(new Rectangle(45,30,50,20));
	    jt1.setBounds(new Rectangle(110,30,200,20));
	    //j1.setLocation(330,30);
	    j1.setBounds(new Rectangle(330,30,100,20));
	    l2.setBounds(new Rectangle(45,60,50,20));
	    jt2.setBounds(new Rectangle(110,60,200,20));
	    j2.setBounds(new Rectangle(330,60,100,20));
	    jt3.setBounds(new Rectangle(15,130,460,120));
	    //增书按钮
	    j1.addMouseListener(new MouseAdapter()
	    {
			public void mouseClicked(MouseEvent e)
			{
				String isbn = jt1.getText().toString();
				String count = jt2.getText().toString();
				GetPlaceByIp u= new GetPlaceByIp();
				Map<String,String> json = new HashMap<>();
				json.put("isbn", isbn);
				json.put("count", count);
				try {
					String x = u.sendPostData("http://localhost:8080/admin/addbook",json.toString());
					jt3.setText(x);
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
	    }
		);
	    //查书按钮
	    j2.addMouseListener(new MouseAdapter()
	    {
			public void mouseClicked(MouseEvent e)
			{
				String isbn = jt1.getText().toString();
				GetPlaceByIp u= new GetPlaceByIp();
				try {
					String x = u.getResponse("http://localhost:8080/book/isbn/"+isbn);
					jt3.setText(x);
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
	    }
		);
	    this.setTitle("增书");
	    this.setLocationRelativeTo(null);
	    //jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    this.setVisible(true);
	    this.setResizable(false);
	    this.addWindowListener(new WindowAdapter(){
	    	public void windowClosing(WindowEvent we)
	    	{
	    		close();
	    	}
	    	public void windowClosed(WindowEvent we)
	    	{ 
	    		mainclass cl = new mainclass();
	    	}
	    });	   
	}
	
	public static void main(String args[])
	{
		addbook ad = new addbook();
	}
	public void close()
    {
       this.setVisible(false);
    }
	

}
