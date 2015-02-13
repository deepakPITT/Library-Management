/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class IssueStudent extends JPanel implements ActionListener
{
	JButton issue,clear,close;
	JLabel l_rollno,l_name,l_bookSNo,l_bookName,l_author,l_IssueDate,l_sub;
	JTextField 	t_rollno,t_name,t_bookSNo,t_bookName,t_author,t_IssueDate,t_sub;
	JTable table;
	DefaultTableModel dm;
	JScrollPane jsp;
	JPanel imgPanel,centerPanel,buttonPanel,mainPanel;
	GridBagConstraints gc;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public IssueStudent(Connection c)
	{
		con = c;
		setLayout(new BorderLayout()); 
		
		imgPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		imgPanel.setBackground(Color.white);
		JLabel img = new JLabel(new ImageIcon("Images/issueStudent.jpg"));
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
		l_rollno = new JLabel("Roll Number");
		l_rollno.setForeground(Color.blue);
		l_rollno.setFont(l);
		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.EAST;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_rollno,gc);
		
		gc = new GridBagConstraints();
		t_rollno = new JTextField(20);
		gc.gridx = 1;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(t_rollno,gc);
		
		gc = new GridBagConstraints();
		l_name = new JLabel("Name");
		l_name.setForeground(Color.blue);
		l_name.setFont(l);
		gc.gridx = 0;
		gc.gridy = 1;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.EAST;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_name,gc);
		
		gc = new GridBagConstraints();
		t_name = new JTextField(20);
		gc.gridx = 1;
		gc.gridy = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
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
		gc.anchor = GridBagConstraints.EAST;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_bookSNo,gc);
		
		gc = new GridBagConstraints();
		t_bookSNo = new JTextField(20);
		t_bookSNo.addActionListener(this);
		gc.gridx = 1;
		gc.gridy = 2;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(t_bookSNo,gc);
		
		gc = new GridBagConstraints();
		l_sub = new JLabel("Subject");
		l_sub.setForeground(Color.blue);
		l_sub.setFont(l);
		gc.gridx = 0;
		gc.gridy = 3;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.EAST;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_sub,gc);
		
		gc = new GridBagConstraints();
		t_sub = new JTextField(20);
		t_sub.setEditable(false);
		gc.gridx = 1;
		gc.gridy = 3;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(t_sub,gc);
		
		gc = new GridBagConstraints();
		l_bookName = new JLabel("Book Name");
		l_bookName.setForeground(Color.blue);
		l_bookName.setFont(l);
		gc.gridx = 0;
		gc.gridy = 4;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.EAST;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_bookName,gc);
		
		gc = new GridBagConstraints();
		t_bookName = new JTextField(20);
		t_bookName.setEditable(false);
		gc.gridx = 1;
		gc.gridy = 4;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(t_bookName,gc);
		
		gc = new GridBagConstraints();
		l_author = new JLabel("Book Author");
		l_author.setForeground(Color.blue);
		l_author.setFont(l);
		gc.gridx = 0;
		gc.gridy = 5;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.EAST;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_author,gc);
		
		gc = new GridBagConstraints();
		t_author = new JTextField(20);
		t_author.setEditable(false);
		gc.gridx = 1;
		gc.gridy = 5;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(t_author,gc);
		
		gc = new GridBagConstraints();
		l_IssueDate = new JLabel("Issue Date");
		l_IssueDate.setForeground(Color.blue);
		l_IssueDate.setFont(l);
		gc.gridx = 0;
		gc.gridy = 6;
		gc.insets = new Insets(0,0,0,5);
		gc.anchor = GridBagConstraints.EAST;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_IssueDate,gc);
		
		String s = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		gc = new GridBagConstraints();
		t_IssueDate = new JTextField(20);
		t_IssueDate.setText(s);
		t_IssueDate.setEditable(false);
		gc.gridx = 1;
		gc.gridy = 6;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(t_IssueDate,gc);
		
		gc = new GridBagConstraints();
		String colmns[] = {"S_No","Subject","Tital","Author","Total Books","Available"};
		dm = new DefaultTableModel(null,colmns);
		table = new JTable(dm);
		jsp = new JScrollPane(table);
		gc.gridx = 2;
		gc.gridy = 0;
		gc.gridheight = 7;
		gc.gridwidth = 2;
		gc.insets = new Insets(10,10,10,10);
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(jsp,gc);	
	}
	
	public void addButtonPanel()
	{
		Font b = new Font("Algerian",Font.BOLD,15);
		
		issue = new JButton("Issue ",new ImageIcon("Images/bookIssue.png"));
		issue.setBackground(new Color(233,214,199));
		issue.setForeground(new Color(207,108,40));
		issue.setFont(b);
		issue.addActionListener(this);
		buttonPanel.add(issue);
			
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
		if(e.getSource()==issue)
		{
			actionIssue();
		}
		
		else if(e.getSource()==clear)
		{
			t_bookName.setText("");
			t_author.setText("");
			t_sub.setText("");
			t_bookSNo.setEditable(true);
		}
		
		else if(e.getSource()==close)
		{
			int a = JOptionPane.showConfirmDialog(LibraryManagementSystem.l,"Do you Want to close Application?","Confirm",JOptionPane.YES_NO_OPTION);
			if(a==JOptionPane.YES_OPTION)
			{
				System.exit(0);
			}
		}
		
		else if(e.getSource()==t_bookSNo)
		{
			actionBookSNo();
		}
	}
	
	public void actionBookSNo()
	{
		String s = t_bookSNo.getText().trim();
		int bookSNo,aval;
		
		if(s.equals(""))
		{
			JOptionPane.showMessageDialog(LibraryManagementSystem.l,"Fill Book Number","Please",JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			try
			{
				bookSNo = Integer.parseInt(s);
				
				pstmt = con.prepareStatement("SELECT * FROM books WHERE s_no=?");
				pstmt.setInt(1,bookSNo);
				
				rs = pstmt.executeQuery();
				
				if(rs.next())
				{
					aval = rs.getInt("aval_books");
				
					if(aval==0)
					{
						JOptionPane.showMessageDialog(LibraryManagementSystem.l,"Book is out of stock","Sorry",JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						String bname = rs.getString("tital");
						String bauthor = rs.getString("author");
						String bsub = rs.getString("subject");
						
						t_bookName.setText(bname);
						t_sub.setText(bsub);
						t_author.setText(bauthor);
						t_bookSNo.setEditable(false);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(LibraryManagementSystem.l,"Book Number dose not exist","Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
			catch(NumberFormatException ne)
			{
				JOptionPane.showMessageDialog(LibraryManagementSystem.l,"Invalid Book Number","Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	public void actionIssue()
	{
		String rollno,name,bookno,date;
		int sno;
		
		rollno = t_rollno.getText().toString().trim();
		name = t_name.getText().toString().trim();
		bookno = t_bookSNo.getText().toString().trim();
		date = t_IssueDate.getText().toString().trim();
		
		if(rollno.equals("")||name.equals("")||bookno.equals(""))
		{
			JOptionPane.showMessageDialog(LibraryManagementSystem.l,"Fill all the entries","Please",JOptionPane.WARNING_MESSAGE);
		}
		else if(t_bookSNo.isEditable())
		{
			JOptionPane.showMessageDialog(LibraryManagementSystem.l,"Press enter in Book Serial Number to confirm book Details","Please",JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			try
			{
				String str = "SELECT * FROM issue_student WHERE roll_no=? AND name=? AND submit_date is null";
				pstmt = con.prepareStatement(str);
				pstmt.setString(1,rollno);
				pstmt.setString(2,name);
				rs = pstmt.executeQuery();
				
				if(rs.next())
				{
					JOptionPane.showMessageDialog(LibraryManagementSystem.l,"Already have a book","Warning",JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					sno = Integer.parseInt(bookno);
					
					String s = "INSERT INTO issue_student (roll_no,name,bookno,issue_date) values(?,?,?,?)";
					pstmt = con.prepareStatement(s);
					pstmt.setString(1,rollno);
					pstmt.setString(2,name);
					pstmt.setInt(3,sno);
					pstmt.setString(4,date);
					
					int i = pstmt.executeUpdate();
					if(i>0)
					{
						s = "UPDATE books SET aval_books = aval_books-1 WHERE s_no="+sno+"";
						stmt = con.createStatement();
						stmt.executeUpdate(s);
						addTableData();
						t_rollno.setText("");
						t_name.setText("");
						t_bookName.setText("");
						t_author.setText("");
						t_sub.setText("");
						t_bookSNo.setText("");
						t_bookSNo.setEditable(true);
						JOptionPane.showMessageDialog(LibraryManagementSystem.l,"Book Issued","Success",JOptionPane.PLAIN_MESSAGE);
					}
				}
			}	
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
	}
	
	public void addTableData()
	{
		try
		{
			
			int a = dm.getRowCount();
			int i=0;
			while(i<a)
			{
				dm.removeRow(0);
				i++;
			}
			
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT * FROM books");
			
			while(rs.next())
			{
				String s[] = new String[6];
				
				s[0] = ""+rs.getInt(1);
				s[1] = rs.getString(2);
				s[2] = rs.getString(3);
				s[3] = rs.getString(4);
				s[4] = ""+rs.getInt(5);
				s[5] = ""+rs.getInt(6);
				
				dm.addRow(s);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
}