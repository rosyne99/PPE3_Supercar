import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
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

public class changepassword {

	private JFrame frame;
	private JTextField couriel;
	private JTextField MDPP;
	private JTextField newp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					changepassword window = new changepassword();
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
	public class UserAccount {
		
		public String name;
		public String surname;
		public String login;
		public String password;
		public String salary;
		
		public void main (String[] args) throws Exception {
			
			String data= "hello World";
			String algorithm="MD5";
			byte[] salt=createSalt();
			System.out.println("MD5 HASH: "+generateHash(data, algorithm,salt));
			
		}

	}
//pour crypter les données 
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
	public static void changepassword() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					changepassword window = new changepassword();
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
	public changepassword() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblChangezVotreMoy = new JLabel("Changez votre mot de passe");
		lblChangezVotreMoy.setFont(new Font("Dialog", Font.BOLD, 16));
		
		JLabel lblEmail = new JLabel("Email");
		
		couriel = new JTextField();
		couriel.setColumns(10);
		
		JLabel mdp = new JLabel("Password");
		
		MDPP = new JTextField();
		MDPP.setColumns(10);
		
		JLabel lblNewPassword = new JLabel("New Password");
		
		newp = new JTextField();
		newp.setColumns(10);
		
		JButton btnNewButton = new JButton("Change");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				Connection conn;
				PreparedStatement insert;
				
				try {
				
				
					String email = couriel.getText(); 
					String password = MDPP.getText(); 
					String newe = newp.getText(); 
					String algorithm="MD5";
					byte[] salt=createSalt();
					String r = (generateHash(newe, algorithm,salt));
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost/supercarjava","root","");
					insert = conn.prepareStatement("select email, password from admin where email=? and password=?");
			
					insert.setString(1, email);
					insert.setString(2, password);
					ResultSet rs = insert.executeQuery();
					
					if (rs.next()) {
						
						insert = conn.prepareStatement("update admin set password=? where email=?");
						
						insert.setString(1, r);
						insert.setString(2, email);
						insert.executeUpdate();
						JOptionPane.showMessageDialog(null, "Changer");
						
						close();
						login  s =new login();
						s.login();
						
						
					}
					else {
						JOptionPane.showMessageDialog(null, "Error");	
					}
				
				}
				
				
				catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		
		JLabel label = new JLabel("New label");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(49)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblEmail)
										.addComponent(mdp, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewPassword, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
									.addGap(47)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(newp, 165, 165, 165)
										.addComponent(MDPP, 175, 175, 175)
										.addComponent(couriel, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(89)
							.addComponent(lblChangezVotreMoy)))
					.addGap(206))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(155)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(371, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblChangezVotreMoy)
					.addGap(18)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(couriel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(mdp)
						.addComponent(MDPP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(24)
							.addComponent(lblNewPassword))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(newp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(30)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(23))
		);
		frame.getContentPane().setLayout(groupLayout);
	}

}
