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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class ChangePassword extends JPanel implements ActionListener
{
	JButton ok,close;
	JLabel l_id,l_old,l_new,l_confirm;
	JPasswordField 	t_old,t_new,t_confirm;
	JTextField id;
	JPanel imgPanel,centerPanel,buttonPanel,mainPanel;
	GridBagConstraints gc;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public ChangePassword(Connection c)
	{
		con = c;
		setLayout(new BorderLayout()); 
		
		imgPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		imgPanel.setBackground(Color.white);
		JLabel img = new JLabel(new ImageIcon("Images/pass.jpg"));
		imgPanel.add(img);
		add(BorderLayout.WEST,imgPanel);
		
		mainPanel = new JPanel(new BorderLayout());
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		centerPanel.setBackground(Color.white);
		addCenterPanel();
		mainPanel.add(BorderLayout.CENTER,centerPanel);
		
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,30,20));
		buttonPanel.setBackground(Color.white);
		addButtonPanel();
		mainPanel.add(BorderLayout.SOUTH,buttonPanel);
		
		add(BorderLayout.CENTER,mainPanel);
	}
	
	public void addCenterPanel()
	{
		Font l = new Font("Times New Roman",Font.BOLD,13);
		
		gc = new GridBagConstraints();
		l_id = new JLabel("User ID");
		l_id.setForeground(Color.blue);
		l_id.setFont(l);
		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 2;
		gc.insets = new Insets(0,150,0,150);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_id,gc);
		
		gc = new GridBagConstraints();
		id = new JTextField(40);
		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridwidth = 2;
		gc.insets = new Insets(10,150,0,150);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTH;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(id,gc);
		
		gc = new GridBagConstraints();
		l_old = new JLabel("Old Password");
		l_old.setForeground(Color.blue);
		l_old.setFont(l);
		gc.gridx = 0;
		gc.gridy = 3;
		gc.gridwidth = 2;
		gc.insets = new Insets(0,150,0,150);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_old,gc);
		
		gc = new GridBagConstraints();
		t_old = new JPasswordField(40);
		gc.gridx = 0;
		gc.gridy = 4;
		gc.gridwidth = 2;
		gc.insets = new Insets(10,150,0,150);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTH;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(t_old,gc);
		
		gc = new GridBagConstraints();
		l_new = new JLabel("New Password");
		l_new.setForeground(Color.blue);
		l_new.setFont(l);
		gc.gridx = 0;
		gc.gridy = 5;
		gc.gridwidth = 2;
		gc.insets = new Insets(0,150,0,150);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_new,gc);
		
		gc = new GridBagConstraints();
		t_new = new JPasswordField(40);
		gc.gridx = 0;
		gc.gridy = 6;
		gc.gridwidth = 2;
		gc.insets = new Insets(10,150,0,150);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTH;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(t_new,gc);
		
		gc = new GridBagConstraints();
		l_confirm = new JLabel("Confirm Password");
		l_confirm.setForeground(Color.blue);
		l_confirm.setFont(l);
		gc.gridx = 0;
		gc.gridy = 7;
		gc.gridwidth = 2;
		gc.insets = new Insets(0,150,0,150);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_confirm,gc);
		
		gc = new GridBagConstraints();
		t_confirm = new JPasswordField(40);
		gc.gridx = 0;
		gc.gridy = 8;
		gc.gridwidth = 2;
		gc.insets = new Insets(10,150,0,150);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTH;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(t_confirm,gc);
	}
	
	public void addButtonPanel()
	{
		Font b = new Font("Algerian",Font.BOLD,15);
				
		ok = new JButton("Ok",new ImageIcon("Images/ok.png"));
		ok.setBackground(new Color(233,214,199));
		ok.setForeground(new Color(207,108,40));
		ok.setFont(b);
		ok.addActionListener(this);
		buttonPanel.add(ok);	
			
		close = new JButton("Close",new ImageIcon("Images/close.png"));
		close.setBackground(new Color(233,214,199));
		close.setForeground(new Color(207,108,40));
		close.setFont(b);
		close.addActionListener(this);
		buttonPanel.add(close);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==ok)
		{
			actionOk();
		}
		
		else if(e.getSource()==close)
		{
		}
	}
	
	public void actionOk()
	{
		boolean flag = true;
		String uname,oldPass,newPass,confirmPass;
		uname = id.getText().trim();
		oldPass = String.valueOf(t_old.getPassword()).trim();
		newPass = String.valueOf(t_new.getPassword()).trim();
		confirmPass = String.valueOf(t_confirm.getPassword()).trim();
		
		if(uname.equals("")||oldPass.equals("")||newPass.equals("")||confirmPass.equals(""))
		{
			JOptionPane.showMessageDialog(LibraryManagementSystem.l,"Fill all the entries","Please",JOptionPane.WARNING_MESSAGE);
		}
		
		else if(!(newPass.equals(confirmPass)))
		{
			JOptionPane.showMessageDialog(LibraryManagementSystem.l,"New Password and Confirm Password not match","Error",JOptionPane.ERROR_MESSAGE);
			t_new.setText("");
			t_confirm.setText("");
		}
		
		else
		{
			try
			{
				String s = "SELECT * FROM login WHERE login_id=? AND password=?";
				
				pstmt = con.prepareStatement(s);
				pstmt.setString(1,uname);
				pstmt.setString(2,oldPass);
				rs = pstmt.executeQuery();
				
				if(rs.next())
				{
					int i =0;
					s = "UPDATE login SET password=? WHERE login_id=?";
					pstmt = con.prepareStatement(s);
					pstmt.setString(1,newPass);
					pstmt.setString(2,uname);
					i=pstmt.executeUpdate();
					
					if(i>0)
					{
						id.setText("");
						t_old.setText("");
						t_new.setText("");
						t_confirm.setText("");
						JOptionPane.showMessageDialog(LibraryManagementSystem.l,"Password Changed","DataBase",JOptionPane.PLAIN_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(LibraryManagementSystem.l,"Database Do not responding","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(LibraryManagementSystem.l,"ID or Password is wrong","Error",JOptionPane.ERROR_MESSAGE);
					t_old.setText("");
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}

