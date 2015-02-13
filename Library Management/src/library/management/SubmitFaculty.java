/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class SubmitFaculty extends JPanel implements ActionListener
{
	JButton submit,clear,close;
	JLabel l_department,l_name,l_bookSNo,l_bookName,l_author,l_IssueDate,l_submitDate;
	JComboBox dept;
	JTextField t_name,t_bookSNo,t_bookName,t_author,t_IssueDate,t_submitDate;
	JPanel imgPanel,centerPanel,buttonPanel,mainPanel;
	GridBagConstraints gc;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;	
	
	public SubmitFaculty(Connection c)
	{
		con = c;
		setLayout(new BorderLayout()); 
		
		imgPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		imgPanel.setBackground(Color.white);
		JLabel img = new JLabel(new ImageIcon("Images/submitFaculty.jpg"));
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
		l_department = new JLabel("Department");
		l_department.setForeground(Color.blue);
		l_department.setFont(l);
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_department,gc);
		
		gc = new GridBagConstraints();
		String items[] = {"IT","CS","ECE","EE"};
		dept = new JComboBox(items);
		gc.gridx = 1;
		gc.gridy = 0;
		gc.insets = new Insets(0,150,0,150);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(dept,gc);
		
		gc = new GridBagConstraints();
		l_name = new JLabel("Name");
		l_name.setForeground(Color.blue);
		l_name.setFont(l);
		gc.gridx = 0;
		gc.gridy = 1;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_name,gc);
		
		gc = new GridBagConstraints();
		t_name = new JTextField(30);
		t_name.addActionListener(this);
		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(t_name,gc);
		
		gc = new GridBagConstraints();
		l_bookSNo = new JLabel("Book Serial Number");
		l_bookSNo.setForeground(Color.blue);
		l_bookSNo.setFont(l);
		gc.gridx = 0;
		gc.gridy = 2;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_bookSNo,gc);
		
		gc = new GridBagConstraints();
		t_bookSNo = new JTextField(30);
		t_bookSNo.setEditable(false);
		gc.gridx = 1;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(t_bookSNo,gc);
		
		gc = new GridBagConstraints();
		l_bookName = new JLabel("Book Name");
		l_bookName.setForeground(Color.blue);
		l_bookName.setFont(l);
		gc.gridx = 0;
		gc.gridy = 3;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_bookName,gc);
		
		gc = new GridBagConstraints();
		t_bookName = new JTextField(30);
		t_bookName.setEditable(false);
		gc.gridx = 1;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(t_bookName,gc);
		
		gc = new GridBagConstraints();
		l_author = new JLabel("Book Author");
		l_author.setForeground(Color.blue);
		l_author.setFont(l);
		gc.gridx = 0;
		gc.gridy = 4;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_author,gc);
		
		gc = new GridBagConstraints();
		t_author = new JTextField(30);
		t_author.setEditable(false);
		gc.gridx = 1;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(t_author,gc);
		
		gc = new GridBagConstraints();
		l_IssueDate = new JLabel("Issue Date");
		l_IssueDate.setForeground(Color.blue);
		l_IssueDate.setFont(l);
		gc.gridx = 0;
		gc.gridy = 5;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_IssueDate,gc);
		
		gc = new GridBagConstraints();
		t_IssueDate = new JTextField(30);
		t_IssueDate.setEditable(false);
		gc.gridx = 1;
		gc.gridy = 5;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(t_IssueDate,gc);
		
		gc = new GridBagConstraints();
		l_submitDate = new JLabel("Submit Date");
		l_submitDate.setForeground(Color.blue);
		l_submitDate.setFont(l);
		gc.gridx = 0;
		gc.gridy = 6;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_submitDate,gc);
		
		String s = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		gc = new GridBagConstraints();
		t_submitDate = new JTextField(30);
		t_submitDate.setText(s);
		t_submitDate.setEditable(false);
		gc.gridx = 1;
		gc.gridy = 6;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(t_submitDate,gc);
	}
	
	public void addButtonPanel()
	{
		Font b = new Font("Algerian",Font.BOLD,15);
		
		submit = new JButton("Submit",new ImageIcon("Images/bookSubmit.png"));
		submit.setBackground(new Color(233,214,199));
		submit.setForeground(new Color(207,108,40));
		submit.setFont(b);
		submit.addActionListener(this);
		buttonPanel.add(submit);
		
		clear = new JButton("Clear",new ImageIcon("Images/clear.png"));
		clear.setBackground(new Color(233,214,199));
		clear.setForeground(new Color(207,108,40));
		clear.setFont(b);
		clear.addActionListener(this);
		buttonPanel.add(clear);	
			
		close = new JButton("Close",new ImageIcon("Images/close.png"));
		close.setBackground(new Color(233,214,199));
		close.setForeground(new Color(207,108,40));
		close.setFont(b);
		close.addActionListener(this);
		buttonPanel.add(close);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==submit)
		{
			actionSubmit();
		}
		
		else if(e.getSource()==t_name)
		{
			actionName();
		}
		
		else if(e.getSource()==clear)
		{
			dept.setEnabled(true);
			t_name.setEditable(true);
			t_bookName.setText("");
			t_author.setText("");
			t_bookSNo.setText("");
			t_IssueDate.setText("");
		}
		
		else if(e.getSource()==close)
		{
			int a = JOptionPane.showConfirmDialog(LibraryManagementSystem.l,"Do you Want to close Application?","Confirm",JOptionPane.YES_NO_OPTION);
			if(a==JOptionPane.YES_OPTION)
			{
				System.exit(0);
			}
		}
	}
	
	public void actionName()
	{
		String name = t_name.getText().trim();
		String dpt = dept.getSelectedItem().toString();
		
		if(name.equals(""))
		{
			JOptionPane.showMessageDialog(LibraryManagementSystem.l,"Enter name","Please",JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			try
			{
				String s = "SELECT * FROM issue_faculty WHERE name=? AND department=? AND submit_date is null";
				pstmt = con.prepareStatement(s);
				pstmt.setString(1,name);
				pstmt.setString(2,dpt);
				rs = pstmt.executeQuery();
				
				if(rs.next())
				{
					dept.setEnabled(false);
					t_name.setEditable(false);
					t_name.setText(rs.getString("name"));
					t_bookSNo.setText(""+rs.getInt("bookno"));
					t_IssueDate.setText(rs.getString("issue_date"));
					
					pstmt = con.prepareStatement("SELECT * FROM books WHERE s_no=?");
					pstmt.setInt(1,rs.getInt("bookno"));
					rs = pstmt.executeQuery();
					rs.next();
					t_bookName.setText(rs.getString("tital"));
					t_author.setText(rs.getString("author"));
				}
				else
				{
					JOptionPane.showMessageDialog(LibraryManagementSystem.l,"This faculty does not have issued any book","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
	}
	
	public void actionSubmit()
	{
		if(t_name.isEditable())
		{
			JOptionPane.showMessageDialog(LibraryManagementSystem.l,"Select department and enter name then press-enter","Please",JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			try
			{
				String name = t_name.getText().trim();
				String dpt = dept.getSelectedItem().toString();
				String submitDate = t_submitDate.getText();
				int sno = Integer.parseInt(t_bookSNo.getText());
				
				String s = "UPDATE issue_faculty SET submit_date=? WHERE name=? AND department=?";
				pstmt = con.prepareStatement(s);
				pstmt.setString(1,submitDate);
				pstmt.setString(2,name);
				pstmt.setString(3,dpt);
				int i = pstmt.executeUpdate();
				
				if(i>0)
				{
					s = "UPDATE books SET aval_books = aval_books+1 WHERE s_no="+sno+"";
					stmt = con.createStatement();
					stmt.executeUpdate(s);
					t_name.setText("");
					t_bookName.setText("");
					t_author.setText("");
					t_bookSNo.setText("");
					t_IssueDate.setText("");
					t_name.setEditable(true);
					dept.setEnabled(true);
					JOptionPane.showMessageDialog(LibraryManagementSystem.l,"Book Submitted","Success",JOptionPane.PLAIN_MESSAGE);
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}	
		}
	}
		
}