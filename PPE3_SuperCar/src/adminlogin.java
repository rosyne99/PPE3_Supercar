import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;

import junit.framework.Test;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class adminlogin {

	private JFrame frame;
	private JTextField Login;
	private JPasswordField Password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminlogin window = new adminlogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void close() {	
		frame.setVisible(false);
	}

	public static void adminlogin() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminlogin window = new adminlogin();
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
	public adminlogin() {
		initialize();
	}
public class UserAccount {
		
		public String Name;
		public String Surname;
		public String Login;
		public String Password;
		public String Salary;
		
		public void main (String[] args) throws Exception {
			
			String data= "hello World";
			String algorithm="MD5";
			byte[] salt=createSalt();
			System.out.println("MD5 HASH: "+generateHash(data, algorithm,salt));
			
		}

	}

	private static String generateHash(String data, String algorithm, byte[]salt) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		digest.reset();
		digest.update(salt);
		digest.digest(data.getBytes());
		byte[] hash= digest.digest(data.getBytes());
		return bytesToStringHex(hash);
	}
	
	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	private JPasswordField MDP;
	private JPasswordField MOTDEPASSE;
	
	public static String bytesToStringHex(byte[]bytes) {
		char[] hexChars = new char[bytes.length*2];
		for (int j=0; j<bytes.length;j++) {
			int v = bytes[j]&0xFF;
			hexChars[j*2]=hexArray[v>>>4];
			hexChars[j*2+1]=hexArray[v& 0x0F];
		}
		return new String(hexChars);
		
	}
	
	public static byte[] createSalt() {
		byte[] bytes = new byte[20];
		SecureRandom random = new SecureRandom();
		random.nextBytes(bytes);
		return bytes;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		Login = new JTextField();
		Login.setColumns(10);
		
		Password = new JPasswordField();
		
		JButton btnNewButton = new JButton("Page principale");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				human_resource  s =new human_resource();
				s.human_resource();
				
				
			}
		});
		
		JButton btnSeConnecter = new JButton("Se connecter");
		btnSeConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Connection conn;
				PreparedStatement insert;
				String algorithm="MD5";
				
					
					
					
					
					String login = Login.getText(); 
					String pWd = Password.getText(); 
					
					
					byte[] salt=createSalt();
					try {
					
					
					String p = (generateHash(pWd, algorithm,salt));
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava","root","");
					insert = conn.prepareStatement("select email, password from loginadmin where email=? and password=? ");
			
					
					
					insert.setString(1, login);
					insert.setString(2, p);
					
					
					
					
					ResultSet rs = insert.executeQuery();
					
					if (rs.next()) {
						close();
						Admin  s =new Admin();
						s.Admin();
						System.out.println("sucess");
					}
					else {
						JOptionPane.showMessageDialog(   btnSeConnecter, this, "Either the password or email is incorrect", 0);
					
						System.out.println(login);
						System.out.println(p);
					}

				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
				catch (Exception e1) {
				
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
				
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("Administrateur");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
							.addComponent(btnSeConnecter, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(Password)
								.addComponent(Login, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))))
					.addContainerGap(69, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(123)
					.addComponent(lblNewLabel)
					.addContainerGap(159, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addComponent(lblNewLabel)
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEmail)
						.addComponent(Login, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(Password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(58)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnSeConnecter))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
