package lib;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener,KeyListener  {
	public static void main(String args[])
	{
		Login log = new Login();
	}
	String account;  
	char[] password;      
	private JButton lg1;
	private JTextField user;
	private JPasswordField pwd;
	private JPanel jp1,jp2;
    private  JLabel la;
	public Login()
	{
		this.setSize(560,450);
	    this.setLayout(new GridLayout(2,0));
	    jp1 = new JPanel();
	    la = new JLabel();
	   la.setIcon(new ImageIcon("timg.jpg"));
	    jp2 = new JPanel();
	    jp2.setLayout(null);
	    jp1.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		lg1 = new JButton("登录");
		user = new JTextField(); 
		pwd = new JPasswordField();
		la.setBounds(new Rectangle(135,20,280,155));
		lg1.setBounds(new Rectangle(150,110,250,35));
		user.setBounds(new Rectangle(150,20,250,35));
		pwd.setBounds(new Rectangle(150,55,250,35));
		jp2.add(pwd);
		jp2.add(user);
		jp2.add(lg1);
		jp2.setBackground(new Color(99,225,99));
		jp1.setBackground(Color.white);
		jp1.add(la);
		lg1.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e)
			{
				putIn();
			}
		});
	   this.add(jp1);
		this.add(jp2);
		this.setLocationRelativeTo(null);

	   user.addKeyListener(this);
	   pwd.addKeyListener(this);
	   this.setVisible(true);
	   this.setResizable(false);
       this.addKeyListener(this);
	}
	public void close()
    {
       this.setVisible(false);
    }
	public void actionPerformed(ActionEvent e)
	{
		
	}
	@Override
	public void keyPressed(KeyEvent e) 
	{
		char key = e.getKeyChar();//�õ����������ֵ
		if(key ==KeyEvent.VK_ENTER){
		putIn();
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void putIn()
	{
		account = user.getText();
		String	password = String.valueOf(pwd.getPassword());
		GetPlaceByIp u= new GetPlaceByIp();
	    String passwordx = u.getResponse("http://localhost:8080/admin/login/"+account);
	    if(password.equals(passwordx)&&account!=""&& !password.equals("")) {
	    	mainclass cl = new mainclass();
		    close();
	    }
	    else {
	    	JOptionPane.showMessageDialog(null, "账号或者密码错误!", "登录失败", JOptionPane.ERROR_MESSAGE);
	    	user.setText("");
	    	pwd.setText("");
	    	user.grabFocus();//光标返回到账号
	    }
	}

}





 