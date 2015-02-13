/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

class LoginForm extends JFrame implements ActionListener
{
	private static LoginForm LM = null;
	JTextField TF_ID;
	JPasswordField TF_PASS;
	JButton B_LOGIN;
	Connection con;
	Statement stmt;
	ResultSet rs;
	
	private LoginForm(Connection c)
	{
		con = c;
		setResizable(false);
		setSize(800,690);
		setLocation(200,10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setTitle("Login");
		setBackground(Color.white);
		
		JPanel IMG_PANEL_TOP = new JPanel(new GridLayout(1,1));
		JLabel LMS_IMG = new JLabel(new ImageIcon("Images/LibraryManagementSystem.jpg"));
		IMG_PANEL_TOP.add(LMS_IMG);
		
		JPanel IMG_PANEL_WEST = new JPanel(new GridLayout(1,1));
		JLabel BOOKS_IMG = new JLabel(new ImageIcon("Images/Admin.jpg"));
		BOOKS_IMG.setBackground(Color.white);
		IMG_PANEL_WEST.add(BOOKS_IMG);
		
		JPanel CENTER_PANEL = new JPanel(new BorderLayout());
		
		JPanel P_BUTTON = new JPanel();
		
		JPanel T_PANEL = new JPanel(new GridBagLayout());
		GridBagConstraints gc;
		
		Font LABEL = new Font("Algerian",Font.BOLD,18);
		
		gc=new GridBagConstraints();
		JLabel L_ID = new JLabel("Administrator ID:- ");
		L_ID.setFont(LABEL);
		L_ID.setForeground(Color.BLUE);
		gc.gridx = 0;
		gc.gridy = 0;
		gc.ipadx = 10;
		gc.ipady = 5;
		gc.weightx = 0.5;
		gc.weighty = 0.1;
		T_PANEL.add(L_ID,gc);
		
		gc=new GridBagConstraints();
		TF_ID = new JTextField(20);
		gc.gridx = 1;
		gc.gridy = 0;
		gc.ipadx = 10;
		gc.ipady = 5;
		gc.weightx = 0.5;
		gc.weighty = 0.1;
		T_PANEL.add(TF_ID,gc);
		
		gc=new GridBagConstraints();
		JLabel L_PASS = new JLabel("Password:- ");
		L_PASS.setFont(LABEL);
		L_PASS.setForeground(Color.BLUE);
		gc.gridx = 0;
		gc.gridy = 1;
		gc.ipadx = 10;
		gc.ipady = 5;
		gc.weightx = 0.5;
		gc.weighty = 0.1;
		T_PANEL.add(L_PASS,gc);
		
		gc=new GridBagConstraints();
		TF_PASS = new JPasswordField(20);
		gc.gridx = 1;
		gc.gridy = 1;
		gc.ipadx = 10;
		gc.ipady = 5;
		gc.weightx = 0.5;
		gc.weighty = 0.1;
		T_PANEL.add(TF_PASS,gc);
		
		T_PANEL.setSize(400,400);
		T_PANEL.setBackground(Color.cyan);
		
		B_LOGIN = new JButton("Login",new ImageIcon("Images/login.png"));
		B_LOGIN.addActionListener(this);
		Font f = new Font("AR DELANEY",Font.BOLD,30);
		B_LOGIN.setBackground(new Color(150,255,255));
		B_LOGIN.setFont(f);
		P_BUTTON.add(B_LOGIN);
		P_BUTTON.setBackground(Color.cyan);
		
		CENTER_PANEL.add(BorderLayout.CENTER,T_PANEL);
		CENTER_PANEL.add(BorderLayout.SOUTH,P_BUTTON);
		
		add(BorderLayout.CENTER,CENTER_PANEL);
		add(BorderLayout.WEST,IMG_PANEL_WEST);
		add(BorderLayout.NORTH,IMG_PANEL_TOP);
		setVisible(true);
	}	
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==B_LOGIN)
		{
			//actionLogin();
			LibraryManagementSystem.l.setVisible(true);
			dispose();
		}
	}
	
	public void  actionLogin()
	{
		boolean  flag = true;
		String uname,pass,dbid,dbpass;
		uname = TF_ID.getText().toString().trim();
		pass = String.valueOf(TF_PASS.getPassword()).toString().trim();
		
		if(uname.equals("")||pass.equals(""))
		{
			JOptionPane.showMessageDialog(this,"Fill both entries","Warning",JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			try
			{
				stmt = con.createStatement();
				rs = stmt.executeQuery("SELECT * FROM login");
				
				while(rs.next())
				{
					dbid = rs.getString("login_id");
					dbpass = rs.getString("password");
					System.out.println("db- "+dbid+dbpass);
					
					if(dbid.equals(uname)&&dbpass.equals(pass))
					{
						LibraryManagementSystem.l.setVisible(true);
						dispose();
						flag = false;
					}
				}
				if(flag)
				{
					JOptionPane.showMessageDialog(this,"Wrong ID or Password","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
		}
	}
	
	public static LoginForm returnLogin(Connection c)
	{
			if(LM==null)
					LM=new LoginForm(c);
			return LM;
	}
}