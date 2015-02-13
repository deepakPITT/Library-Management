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
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class AddBooks extends JPanel implements ActionListener
{
	JButton add,clear,close;
	JLabel l_sno,l_sub,l_tital,l_author,l_total;
	JTextField 	t_sub,t_tital,t_author,t_total;
	public static JTextField t_sno = new JTextField(25);
	JPanel imgPanel,centerPanel,buttonPanel,mainPanel;
	JTable table;
	DefaultTableModel dm;
	JScrollPane jsp;
	GridBagConstraints gc;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt,pstmt1;
	ResultSet rs;
	
	public AddBooks(Connection c)
	{
		con = c;
		setLayout(new BorderLayout()); 
		
		imgPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		imgPanel.setBackground(Color.white);
		JLabel img = new JLabel(new ImageIcon("Images/addBooks.jpg"));
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
	
	public void addButtonPanel()
	{
		Font b = new Font("Algerian",Font.BOLD,15);
		
		add = new JButton("Add",new ImageIcon("Images/bookAdd.png"));
		add.setBackground(new Color(233,214,199));
		add.setForeground(new Color(207,108,40));
		add.setFont(b);
		add.addActionListener(this);
		buttonPanel.add(add);
		
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
	
	public void addCenterPanel()
	{
		Font l = new Font("Times New Roman",Font.BOLD,13);
		
		gc = new GridBagConstraints();
		l_sno = new JLabel("Serial Number ");
		l_sno.setForeground(Color.blue);
		l_sno.setFont(l);
		gc.gridx = 0;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_sno,gc);
		
		gc = new GridBagConstraints();
		t_sno.setEditable(false);
		gc.gridx = 1;
		gc.gridy = 0;
		gc.anchor = GridBagConstraints.WEST;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(t_sno,gc);
		
		gc = new GridBagConstraints();
		l_sub = new JLabel("Subject");
		l_sub.setForeground(Color.blue);
		l_sub.setFont(l);
		gc.gridx = 0;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_sub,gc);
		
		gc = new GridBagConstraints();
		t_sub = new JTextField(25);
		gc.gridx = 1;
		gc.gridy = 1;
		gc.anchor = GridBagConstraints.WEST;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(t_sub,gc);
		
		gc = new GridBagConstraints();
		l_tital = new JLabel("Book Tital");
		l_tital.setForeground(Color.blue);
		l_tital.setFont(l);
		gc.gridx = 0;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_tital,gc);
		
		gc = new GridBagConstraints();
		t_tital = new JTextField(25);
		gc.gridx = 1;
		gc.gridy = 2;
		gc.anchor = GridBagConstraints.WEST;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(t_tital,gc);
		
		gc = new GridBagConstraints();
		l_author = new JLabel("Book Author");
		l_author.setForeground(Color.blue);
		l_author.setFont(l);
		gc.gridx = 0;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_author,gc);
		
		gc = new GridBagConstraints();
		t_author = new JTextField(25);
		gc.gridx = 1;
		gc.gridy = 3;
		gc.anchor = GridBagConstraints.WEST;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(t_author,gc);
		
		gc = new GridBagConstraints();
		l_total = new JLabel("Total Books");
		l_total.setForeground(Color.blue);
		l_total.setFont(l);
		gc.gridx = 0;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.CENTER;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(l_total,gc);
		
		gc = new GridBagConstraints();
		t_total = new JTextField(25);
		gc.gridx = 1;
		gc.gridy = 4;
		gc.anchor = GridBagConstraints.WEST;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(t_total,gc);
		
		gc = new GridBagConstraints();
		String colmns[] = {"S_No","Subject","Tital","Author","Total Books","Available"};
		dm = new DefaultTableModel(null,colmns);
		table = new JTable(dm);
		jsp = new JScrollPane(table);
		addTableData();
		gc.gridx = 2;
		gc.gridy = 0;
		gc.gridheight = 5;
		gc.gridwidth = 2;
		gc.insets = new Insets(10,10,10,10);
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 0.1;
		gc.weighty = 0.1;
		centerPanel.add(jsp,gc);	
	}
		
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==add)
		{
			addBooks();
		}
		
		else if(e.getSource()==clear)
		{
			t_sub.setText("");
			t_tital.setText("");
			t_author.setText("");
			t_total.setText("");
			LibraryManagementSystem.l.serialNo();
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
	
	public void addBooks()
	{
		int s_no,totalBooks;
		boolean flag = true;
		String sno,sub,tital,author,total;
		sno = t_sno.getText().trim();
		sub = t_sub.getText().trim();
		tital = t_tital.getText().trim();
		author = t_author.getText().trim();
		total = t_total.getText().trim();
		
		if(sub.equals("")||tital.equals("")||author.equals("")||total.equals(""))
		{
			JOptionPane.showMessageDialog(LibraryManagementSystem.l,"Fill all the entries","Please",JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			try
			{
				s_no = Integer.parseInt(sno);
				totalBooks = Integer.parseInt(total);
				
				String str = "SELECT s_no FROM books WHERE subject=? AND tital=? AND author=?";
				pstmt1 = con.prepareStatement(str);
				pstmt1.setString(1,sub);
				pstmt1.setString(2,tital);
				pstmt1.setString(3,author);
				rs = pstmt1.executeQuery();
				
				if(rs.next())
				{
					JOptionPane.showMessageDialog(LibraryManagementSystem.l,"Already exist","Warning",JOptionPane.WARNING_MESSAGE);
					flag = false;
				}
				
				if(flag)
				{
					String s = "INSERT INTO books VALUES(?,?,?,?,?,?)";
					pstmt = con.prepareStatement(s);
					
					pstmt.setInt(1,s_no);
					pstmt.setString(2,sub);
					pstmt.setString(3,tital);
					pstmt.setString(4,author);
					pstmt.setInt(5,totalBooks);
					pstmt.setInt(6,totalBooks);
					
					int a = 0;
					a = pstmt.executeUpdate();
					if(a>0)
					{
						t_sub.setText("");
						t_tital.setText("");
						t_author.setText("");
						t_total.setText("");
						LibraryManagementSystem.l.serialNo();
						addTableData();
						JOptionPane.showMessageDialog(LibraryManagementSystem.l,"Book Inserted into DataBase","DataBase",JOptionPane.PLAIN_MESSAGE);
					}
				}
				
			}
			catch(NumberFormatException ne)
			{
				JOptionPane.showMessageDialog(LibraryManagementSystem.l,"Invalid  Type of Total Books","Error",JOptionPane.ERROR_MESSAGE);
				t_total.setText("");
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
