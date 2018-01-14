package lib;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class mainclass  extends JFrame {
	private static final int BOLD = 0;
	private JPanel p1,p2,p3,p4;
	private JLabel jl1,jl2,jl3,jl4;
	public mainclass ()
	{
	    this.setLayout(new GridLayout(2,2));
	    p1 = new JPanel();
	    p2 = new JPanel();
	    p3 = new JPanel();
	    p4 = new JPanel();
	    jl1 = new JLabel("增书");
	    jl1.setFont(new Font("",BOLD,30));
	    p1.addMouseListener(new MouseAdapter()
	    {
	    	public void mouseEntered(MouseEvent e)   
			{
	    		jl1.setFont(new Font("",BOLD,40));
			}
			
			public void mouseExited(MouseEvent e)   
			{
				jl1.setFont(new Font("",BOLD,30));
			}
			
			public void mouseClicked(MouseEvent e)
			{
				addbook ab = new addbook();
				close();
			}
	    }
		);
	    jl2 = new JLabel("还书");
	    jl2.setFont(new Font("",BOLD,30));
	    p2.addMouseListener(new MouseAdapter()
	    {
	    	public void mouseEntered(MouseEvent e)   
			{
	    		jl2.setFont(new Font("",BOLD,40));
			}
			
			public void mouseExited(MouseEvent e)  
			{
				jl2.setFont(new Font("",BOLD,30));
			}
			
			public void mouseClicked(MouseEvent e)
			{
				close();
				Return rt = new Return();
			}
	    }
		);	    
	    jl3 = new JLabel("借书");
	    jl3.setFont(new Font("",BOLD,30));
	    p3.addMouseListener(new MouseAdapter()
	    {
	    	public void mouseEntered(MouseEvent e)  
			{
	    		jl3.setFont(new Font("",BOLD,40));
			}
			
			public void mouseExited(MouseEvent e)  
			{
				jl3.setFont(new Font("",BOLD,30));
			}
			
			public void mouseClicked(MouseEvent e)
			{
				close();
				Lend l = new Lend();
			}
	    }
	    );
	    jl4 = new JLabel("充值");
	    jl4.setFont(new Font("",BOLD,30));
	    p4.addMouseListener(new MouseAdapter()
	    {
	    	public void mouseEntered(MouseEvent e)   
			{
	    		jl4.setFont(new Font("",BOLD,40));
			}
			
			public void mouseExited(MouseEvent e)   
			{
				jl4.setFont(new Font("",BOLD,30));
			}
			
			public void mouseClicked(MouseEvent e)
			{
				close();
				Money m = new Money();
			}
	    }
	    );
	    p2.setLayout(null);
	    p1.setLayout(null);
	    p3.setLayout(null);
	    p4.setLayout(null);
	    
	    jl1.setBounds(new Rectangle(150,60,200,150));
	    jl2.setBounds(new Rectangle(150,60,200,150));
	    jl3.setBounds(new Rectangle(150,60,200,150));
	    jl4.setBounds(new Rectangle(150,60,200,150));
	    
	    p1.setBackground(Color.gray);
	    p4.setBackground(Color.orange);
	    p1.add(jl1);
	    p2.add(jl2);
	    p3.add(jl3);
	    p4.add(jl4);
	
	    this.add(p1);
	    this.add(p2);
	    this.add(p3);
	    this.add(p4);
	    this.setSize(800,600);
	    this.setVisible(true);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    
	}
	public void close()
    {
       this.setVisible(false);
    }
	
	
	
	public static void main(String args[])
	{
		mainclass  cl  = new mainclass(); 
	}
	
	


}
