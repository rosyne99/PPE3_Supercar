import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Window;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.*;
import javax.swing.*;
import junit.framework.Test;
import java.io.*;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.event.WindowEvent;
public class hr {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hr window = new hr();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void hr() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hr window = new hr();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public hr() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */public void close() {	
			frame.setVisible(false);
		}
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 850, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Test.class.getResource("/pay.jpg")));
		JLabel label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon(Test.class.getResource("/dept.jpg")));
		
		JButton btnNewButton = new JButton("Employ\u00E9");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
						close();
				
				

				supercaremployee  s =new supercaremployee();
				s.supercaremployee();
				
				
			}

			
			
		});
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Test.class.getResource("/a.jpg")));
		
		JButton btnLaPaye = new JButton("La paye");
		btnLaPaye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				calculate_salary  s =new calculate_salary();
				s.calculate_salary();
			}
		});
		
		JButton btnRecrutement = new JButton("To - do - list");
		btnRecrutement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				recrutement  s =new recrutement();
				s.recrutement();
			}
		});
		
		JButton btnDpartement = new JButton("D\u00E9partement");
		btnDpartement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				dept  s =new dept();
				s.dept();
			}
		});
		
		JButton btnNewButton_1 = new JButton("Ressource Humaine");
		btnNewButton_1.setBackground(SystemColor.textHighlightText);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		JButton btnNewButton_2 = new JButton("Log Out");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				close();
				
				

				login  s =new login();
				s.login();
				
				
			}
		});
		btnNewButton_2.setBackground(SystemColor.window);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(86)
					.addComponent(btnRecrutement, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 283, Short.MAX_VALUE)
					.addComponent(btnDpartement, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
					.addGap(87))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
							.addGap(58))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnNewButton_2)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)))
							.addGap(40))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(84)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 285, Short.MAX_VALUE)
					.addComponent(btnLaPaye, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)
					.addGap(87))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(209)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 420, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(205, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addComponent(btnNewButton_2)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLaPaye, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnRecrutement, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnDpartement, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(49, Short.MAX_VALUE))
		);
		
		JLabel show_image = new JLabel("");
		show_image.setIcon(new ImageIcon(Test.class.getResource("/emp.jpg")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(show_image, GroupLayout.PREFERRED_SIZE, 263, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(show_image, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
	}
	
}
