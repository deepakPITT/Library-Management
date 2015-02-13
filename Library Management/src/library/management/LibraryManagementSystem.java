/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management;

/**
 *
 * @author ankita
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class LibraryManagementSystem extends JFrame implements ActionListener
{	
	public static LibraryManagementSystem l;
	JPanel main_img;
	JLabel l_main;
	JPanel centerPanel,cardPanel;
	JMenuBar mb;
	JMenu system,submit,issue,lms;
	JMenuItem addBooks, changePass, submitStudent, submitFaculty, issueStudent, issueFaculty, about, help;
	CardLayout cd;
	JButton b_home;
	private JDialog DIALOG_ABOUT;
	private JDialog DIALOG_HELP;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	Home home;
	AddBooks add;
	ChangePassword chang;
	SubmitStudent subs;
	SubmitFaculty subf;
	IssueStudent iss;
	IssueFaculty isf;
	
	public LibraryManagementSystem()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:8889/Library","root","root");
		}
		catch(ClassNotFoundException ce)
		{
			ce.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon("Images/libraryIcon.png").getImage());
		setTitle("Library Management System");
		setSize(1100,700);
		setMinimumSize(new Dimension(1100,700));
		
		DIALOG_ABOUT=new JDialog(this,"ABOUT - Library Management System",true,null);
		ABOUT();
		DIALOG_HELP=new JDialog(this,"HELP - Library Management System",true,null);
		HELP();
		
		
		main_img = new JPanel(new FlowLayout(FlowLayout.LEFT));
		imagePanel();
		
		centerPanel = new JPanel(new BorderLayout());
		
		mb = new JMenuBar();
		menuBarCoading();
		centerPanel.add(BorderLayout.NORTH,mb);
		
		cd = new CardLayout();
		cardPanel = new JPanel(cd);
		addCards();
		centerPanel.add(BorderLayout.CENTER,cardPanel);
		
		getContentPane().add(BorderLayout.CENTER,centerPanel);
		getContentPane().add(BorderLayout.NORTH,main_img);
		
		LoginForm LM = LoginForm.returnLogin(con);
	}
	
	public void imagePanel()
	{
		main_img.setBackground(new Color(233,214,199));
		l_main = new JLabel(new ImageIcon("Images/lms.jpg"));
		main_img.add(l_main);
		
		b_home = new JButton(new ImageIcon("Images/library.png"));
		b_home.addActionListener(this);
		b_home.setBackground(new Color(233,214,199));
		main_img.add(b_home);
	}
	
	public void addCards()
	{
		home = new Home();
		cardPanel.add(home,"Home");
		
		add = new AddBooks(con);
		cardPanel.add(add,"AddBooks");
		
		chang = new ChangePassword(con);
		cardPanel.add(chang,"ChangePassword");
		
		
		subs = new SubmitStudent(con);
		cardPanel.add(subs,"SubmitStudent");
		
		subf = new SubmitFaculty(con);
		cardPanel.add(subf,"SubmitFaculty");
		
		iss = new IssueStudent(con);
		cardPanel.add(iss,"IssueStudent");
		
		isf = new IssueFaculty(con);
		cardPanel.add(isf,"IssueFaculty");
	}
	
	public void menuBarCoading()
	{
		mb.setBackground(new Color(233,214,170));
		
		Font menu = new Font("Cambria",Font.BOLD,15);
		Font menuItem = new Font("Calibri",Font.BOLD,15);
		
		system = new JMenu("System Administrator");
		system.setBackground(new Color(233,214,170));
		system.setFont(menu);
		addBooks = new JMenuItem("Add Books",new ImageIcon("Images/bookAdd.png"));
		addBooks.addActionListener(this);
		addBooks.setBackground(new Color(233,214,170));
		addBooks.setFont(menuItem);
		changePass = new JMenuItem("Change Password",new ImageIcon("Images/changePass.png"));
		changePass.addActionListener(this);
		changePass.setBackground(new Color(233,214,170));
		changePass.setFont(menuItem);
		system.add(addBooks);
		system.add(changePass);
		
		submit = new JMenu("Submit Books");
		submit.setBackground(new Color(233,214,199));
		submit.setFont(menu);
		submitStudent = new JMenuItem("Student",new ImageIcon("Images/bookSubmit.png"));
		submitStudent.addActionListener(this);
		submitStudent.setBackground(new Color(233,214,170));
		submitStudent.setFont(menuItem);
		submitFaculty = new JMenuItem("Faculty",new ImageIcon("Images/bookSubmit.png"));
		submitFaculty.addActionListener(this);
		submitFaculty.setBackground(new Color(233,214,170));
		submitFaculty.setFont(menuItem);
		submit.add(submitStudent);
		submit.add(submitFaculty);
		
		issue = new JMenu("Issue books");
		issue.setBackground(new Color(233,214,170));
		issue.setFont(menu);
		issueStudent = new JMenuItem("Student",new ImageIcon("Images/bookIssue.png"));
		issueStudent.addActionListener(this);
		issueStudent.setBackground(new Color(233,214,170));
		issueStudent.setFont(menuItem);
		issueFaculty = new JMenuItem("Faculty",new ImageIcon("Images/bookIssue.png"));
		issueFaculty.addActionListener(this);
		issueFaculty.setBackground(new Color(233,214,170));
		issueFaculty.setFont(menuItem);
		issue.add(issueStudent);
		issue.add(issueFaculty);
		
		lms = new JMenu("LibraryManagementSystem Information");
		lms.setBackground(new Color(233,214,170));
		lms.setFont(menu);
		about = new JMenuItem("About Us",new ImageIcon("Images/About.png"));
		about.addActionListener(this);
		about.setBackground(new Color(233,214,170));
		about.setFont(menuItem);
		help = new JMenuItem("Help",new ImageIcon("Images/help.png"));
		help.addActionListener(this);
		help.setBackground(new Color(233,214,170));
		help.setFont(menuItem);
		lms.add(about);
		lms.add(help);
		
		mb.add(system);
		mb.add(submit);
		mb.add(issue);
		mb.add(lms);
	}	
	
	private void ABOUT()
	{
		DIALOG_ABOUT.setSize(400,450);
		DIALOG_ABOUT.setLocation(300,100);
		DIALOG_ABOUT.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		DIALOG_ABOUT.getContentPane().setLayout(null);
		DIALOG_ABOUT.getContentPane().setBackground(new Color(227,114,58));
		DIALOG_ABOUT.setResizable(false);
		DIALOG_ABOUT.setIconImage(new ImageIcon("Images/About.png").getImage());
		
		Font FONT_LABEL = new Font("Calibri (Body)",Font.BOLD,15);
		Font FONT_TEXT = new Font("Times New Roman (Body)",Font.PLAIN,18);
		
		JTextArea DIALOG_ABOUT_DEVELOPERS=new JTextArea("\n Developed By Deepak Mathur Description -");
		DIALOG_ABOUT_DEVELOPERS.append("  Library   Management  System  is    developed  in  JAVA. This   application  only   for   College   Library.   It  can   maintain   records  of   books in Database and records of issued  books   to student and faculty.");
		DIALOG_ABOUT_DEVELOPERS.setLineWrap(true);
		DIALOG_ABOUT_DEVELOPERS.setForeground(Color.white);
		DIALOG_ABOUT_DEVELOPERS.setBackground(new Color(227,114,58));
		DIALOG_ABOUT_DEVELOPERS.setEditable(false);
		DIALOG_ABOUT_DEVELOPERS.setFont(FONT_TEXT);
		DIALOG_ABOUT.getContentPane().add(DIALOG_ABOUT_DEVELOPERS);
		DIALOG_ABOUT_DEVELOPERS.setBounds(0,0,400,270);
		
		JLabel DIALOG_ABOUT_LOGO=new JLabel(new ImageIcon(""));
		DIALOG_ABOUT.getContentPane().add(DIALOG_ABOUT_LOGO);
		DIALOG_ABOUT_LOGO.setBounds(0,300,100,100);
		
		JTextArea DIALOG_ABOUT_GRRAS=new JTextArea("");
		DIALOG_ABOUT_GRRAS.setLineWrap(true);
		DIALOG_ABOUT_GRRAS.setForeground(Color.CYAN);
		DIALOG_ABOUT_GRRAS.setBackground(new Color(227,114,58));
		DIALOG_ABOUT_GRRAS.setEditable(false);
		DIALOG_ABOUT_GRRAS.setFont(FONT_LABEL);
		DIALOG_ABOUT.getContentPane().add(DIALOG_ABOUT_GRRAS);
		DIALOG_ABOUT_GRRAS.setBounds(110,300,280,100);
		
	}
	
	private void HELP()
	{
		DIALOG_HELP.setSize(400,150);
		DIALOG_HELP.setLocation(300,100);
		DIALOG_HELP.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		DIALOG_HELP.getContentPane().setLayout(null);
		DIALOG_HELP.getContentPane().setBackground(new Color(227,114,58));
		DIALOG_HELP.setResizable(false);
		DIALOG_HELP.setIconImage(new ImageIcon("Images/help.png").getImage());
		
		Font FONT_LABEL = new Font("Calibri (Body)",Font.BOLD,13);
		
		JLabel DIALOG_HELP_LOGO=new JLabel(new ImageIcon(""));
		DIALOG_HELP.getContentPane().add(DIALOG_HELP_LOGO);
		DIALOG_HELP_LOGO.setBounds(0,0,100,150);
		
		JTextArea DIALOG_HELP_GRRAS=new JTextArea(" ");
		DIALOG_HELP_GRRAS.setLineWrap(true);
		DIALOG_HELP_GRRAS.setForeground(Color.green);
		DIALOG_HELP_GRRAS.setBackground(new Color(227,114,58));
		DIALOG_HELP_GRRAS.setEditable(false);
		DIALOG_HELP_GRRAS.setFont(FONT_LABEL);
		DIALOG_HELP.getContentPane().add(DIALOG_HELP_GRRAS);
		DIALOG_HELP_GRRAS.setBounds(120,0,280,150);
		
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==b_home)
		{
			cd.show(cardPanel,"Home");
		}
		
		else if(e.getSource()==addBooks)
		{
			serialNo();
			cd.show(cardPanel,"AddBooks");
			add.addTableData();
		}
		
		else if(e.getSource()==changePass)
		{
			cd.show(cardPanel,"ChangePassword");
		}
		
		else if(e.getSource()==submitStudent)
		{
			cd.show(cardPanel,"SubmitStudent");
		}
		
		else if(e.getSource()==submitFaculty)
		{
			cd.show(cardPanel,"SubmitFaculty");
		}
		
		else if(e.getSource()==issueStudent)
		{
			cd.show(cardPanel,"IssueStudent");
			iss.addTableData();
		}
		
		else if(e.getSource()==issueFaculty)
		{
			cd.show(cardPanel,"IssueFaculty");
			isf.addTableData();
		}
		
		else if(e.getSource()==about)
		{
			DIALOG_ABOUT.setVisible(true);
		}
		
		else if(e.getSource()==help)
		{
			DIALOG_HELP.setVisible(true);
		}
	}

	public void serialNo()
	{
		try
		{
			int sno;
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT max(s_no) FROM books");
			if(rs.last())
			{
				sno = rs.getInt(1)+1;
			}
			else
			{
				sno=1;
			}
			AddBooks.t_sno.setText(""+sno);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main (String[] args)
	{
		l = new LibraryManagementSystem();
	}
}