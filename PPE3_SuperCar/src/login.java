/***
 * Auteur: Rosine Laridain
 * Group: FSociety
 * Date:2020-2021
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import junit.framework.Test;

import java.awt.Font;
import javax.swing.JTextField;
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
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.SystemColor;

public class login {

	private JFrame frame;
	private JTextField mail;
	private JPasswordField pwd;
	private JComboBox typecomboBox;
	/**
	 * Launch the application.
	 */public void close() {	
			frame.setVisible(false);
		}
	 
	 //pour crypter les données
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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void login() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
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
	public login() {
		initialize();
	}
public void changepassword() throws ClassNotFoundException, SQLException {
	Connection conn;
	PreparedStatement insert;
	
	
	String email=mail.getText();
	String password=MDP.getText();
	// si la personne est nouvelle, on va juste lui demander de changer leur mot de passe que l'admin a créer
	Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/supercarjava","root","");
			PreparedStatement checkpassword = conn.prepareStatement("select email, password from admin where email=? and password=? ");
						
		checkpassword.setString(1, email);
		checkpassword.setString(2, password);

	    ResultSet rs = checkpassword.executeQuery();
	        if (rs.next()) {
	           
	        	close();
				changepassword  s =new changepassword();
				s.changepassword();
	        	
	        	
	        } 
	        
	        else {
	        	
	        	
	        	String Email = mail.getText(); 
				String pass = MDP.getText(); 
				String algorithm="MD5";
				byte[] salt=createSalt();
				try {
					//Pour crypter salaire et mot de passe
					String r = (generateHash(Email, algorithm,salt));
					String p = (generateHash(pass, algorithm,salt));
					
					//pour verifier si les données sont correct
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					insert = conn.prepareStatement("select email, password from admin where email=? and password=? ");
	
			
					insert.setString(1, p);
					insert.setString(2, r);
				
				
				
				 ResultSet x = insert.executeQuery();
					        if (x.next()) {
					        	close();
								hr  s =new hr();
								s.hr();
				}
				
				}        catch (NoSuchAlgorithmException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				} 
	        	
				}
	        
	    

	   
	        
	        
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.control);
		frame.setBounds(100, 100, 650, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblEmail = new JLabel("LOGIN");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblPassword = new JLabel("PWD");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		mail = new JTextField();
		mail.setColumns(10);
		
		pwd = new JPasswordField();
		
		JButton btnConnecter = new JButton("Connexion");
		btnConnecter.setBackground(Color.LIGHT_GRAY);
		btnConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection conn;
				PreparedStatement insert;
				
				try {
					//pour changer le mot de passe dans la base de donnée
				
					String email = mail.getText(); 
					String password = pwd.getText(); 
					
					String user_type = typecomboBox.getSelectedItem().toString();
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost/supercarjava","root","");
					insert = conn.prepareStatement("select email, password, user_type from admin where email=? and password=? and user_type=?");
			
					insert.setString(1, email);
					insert.setString(2, password);
					insert.setString(3, user_type);
					ResultSet rs = insert.executeQuery();
					
					if (rs.next()) {
						
						close();
						changepassword  s =new changepassword();
						s.changepassword();
						System.out.println("Sucess");
					}
					else {
					
						String algorithm="MD5";  
						byte[] salt=createSalt();
						
							//Pour crypter salaire et mot de passe
							
							String p = (generateHash(password, algorithm,salt));
							
							
							Class.forName("com.mysql.cj.jdbc.Driver");
							
							insert = conn.prepareStatement("select email, password, user_type from admin where email=? and password=? and user_type=? ");
			
					
							insert.setString(1, email);
							insert.setString(2, p);
							insert.setString(3, user_type);
						
					
						 ResultSet x = insert.executeQuery();
						
							        if (x.next()) {
							        	if (user_type=="manager") {
							        		close();
											Admin  s =new Admin();
											s.Admin();
							        	}
							        	else if (user_type=="utilisateur") {
							        		close();
											hr  s =new hr();
											s.hr();
							        	}
							        	 else {
									        	
									        	
									        	JOptionPane.showMessageDialog( null, this, "Error", 0);
											
									        }
						}
							        
							        
							        else {
							        	
							        	
							        	JOptionPane.showMessageDialog( null, this, "Error", 0);
									
							        }

					}
					
					
					}
					
				catch (ClassNotFoundException | NoSuchAlgorithmException e1) {
					
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					
				
				
				
				
				
				
				}		
				
			
				
			
		});
		btnConnecter.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblNewLabel = new JLabel("SuperCar");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		
		JLabel lblUtilisateur = new JLabel("Utilisateur");
		lblUtilisateur.setFont(new Font("Tahoma", Font.BOLD, 20));
		
	 typecomboBox = new JComboBox();
		typecomboBox.setBackground(Color.WHITE);
		typecomboBox.setModel(new DefaultComboBoxModel(new String[] {"Type", "administrateur", "utilisateur", "manager"}));
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Test.class.getResource("/client.png")));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(255, Short.MAX_VALUE)
					.addComponent(btnConnecter, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(229))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUtilisateur, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
					.addGap(61)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(typecomboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(pwd)
								.addComponent(mail, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
							.addGap(38)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEmail)
								.addComponent(mail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblUtilisateur, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(pwd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(typecomboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(41)
							.addComponent(btnConnecter, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(79)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(19, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
