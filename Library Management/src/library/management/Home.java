/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management;

import java.awt.*;
import javax.swing.*;

class Home extends JPanel 
{
	
	public Home()
	{
		setLayout(new BorderLayout()); 
		setBackground(Color.WHITE);
		JLabel img = new JLabel(new ImageIcon("Images/home.jpg"));
		add(BorderLayout.CENTER,img);
	}
}