import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;


import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class testingencrypt {

	private JFrame frame;
	private JTextField salaire;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testingencrypt window = new testingencrypt();
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
	public testingencrypt() {
		initialize();
		table_update();
	}
	
	private void table_update() {
		int c;
		try {
			Connection conn;
			PreparedStatement insert;
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/supercarjava","root","");
			insert = conn.prepareStatement("select * from tes");
			ResultSet rs = insert.executeQuery();
			ResultSetMetaData Rss =  (ResultSetMetaData) rs.getMetaData();
			c=Rss.getColumnCount();
			
			DefaultTableModel tab = (DefaultTableModel)table.getModel();
			
			tab.setRowCount(0);
			while(rs.next()) {
				 
				Vector v2 = new Vector();
				
				for (int a =1; a<=c; a++) {
					
					v2.add(rs.getString("id"));
					v2.add(rs.getString("SALAIRE"));
					
					
					
					
				}
				
				tab.addRow(v2);
			}
			
			
		} catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
		}
		catch (Exception e1) {
		
			e1.printStackTrace();
		}
		
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		salaire = new JTextField();
		salaire.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			byte[] raw = null;
			byte[]skey= new byte[1000];
			String skeyString;
			
			String inputMessage, encryptedData,decryptedMessage;
			public void actionPerformed(ActionEvent e) {
				
				try {
				Connection conn;
				PreparedStatement insert;
				String SALAIRE = salaire.getText(); 
				generateSymmetricKey();
				
			
				byte[] ibyte = SALAIRE.getBytes();
				byte[]ebyte=encrypt(raw,ibyte);
				String encryptedData1 = new String(ebyte);
				System.out.println("Encrypted Message"+ encryptedData1);
				JOptionPane.showMessageDialog(null, "Encrypted Data",encryptedData1, 0 );
				byte[] dbyte= decrypt(raw, ebyte);
				String decryptedMessage = new String(dbyte);
				System.out.println("Decrypted Message"+ decryptedMessage);
				JOptionPane.showMessageDialog(null, "Decrypted",decryptedMessage, 0 );
			
					
					// cette partie on ajoute les données dans la base de dpnnées
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost/supercarjava","root","");
					insert = conn.prepareStatement("insert into tes(SALAIRE, test)values(?,?)");
					insert.setString(1, encryptedData1);
					insert.setString(2, decryptedMessage);
					
					insert.executeUpdate();
					JOptionPane.showMessageDialog( btnNewButton, this, "Record Added", 0);
					
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
				catch (Exception e1) {
				 
					e1.printStackTrace();
				}
				
				
				
				
				}

			private byte[] encrypt(byte[] raw, byte[] clear) throws Exception{
				SecretKeySpec skeySpec = new SecretKeySpec( raw, "Blowfish");
				Cipher cipher = Cipher.getInstance("Blowfish");
				cipher.init(Cipher.ENCRYPT_MODE,skeySpec);
				byte[] encrypted = cipher.doFinal(clear);
				return encrypted;
			}

			private void generateSymmetricKey() {
				try {
					
					
					Random r = new Random();
					int num = r.nextInt(1000);
					String knum = String.valueOf(num);
					byte[] knumb = knum.getBytes();
					skey=(byte[]) getRawKey(knumb);
					skeyString = new String(skey);
					System.out.println("Blowfish"+ skeyString);
				}
				catch(Exception e1) {
					System.out.println(e1);
				}
			}
			private byte[] getRawKey(byte[] seed) throws Exception {
				
				KeyGenerator kgen = KeyGenerator.getInstance("Blowfish");
				SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
				sr.setSeed(seed);
				kgen.init(128,sr);
				SecretKey skey = kgen.generateKey();
				raw = skey.getEncoded();
				return raw;
						
				
			}
			
			private byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
				SecretKeySpec skeySpec = new SecretKeySpec( raw, "Blowfish");
				Cipher cipher = Cipher.getInstance("Blowfish");
				cipher.init(Cipher.DECRYPT_MODE,skeySpec);
				byte[] decrypted = cipher.doFinal(encrypted);
				return decrypted;
			}
				
				
			
		});
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(48)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton)
								.addComponent(lblNewLabel)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(25)
							.addComponent(salaire, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)))
					.addGap(47)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 409, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(32)
							.addComponent(lblNewLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(41)
									.addComponent(salaire, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(29)
									.addComponent(btnNewButton)))))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
		
			
	
			byte[]skey= new byte[1000];
			String skeyString;
			byte[] raw = null;
			String inputMessage, encryptedData,decryptedMessage;
		
			public void mouseClicked(MouseEvent e){
				try {
				generateSymmetricKey();
				
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				String r=tab.getValueAt(selectedIndex, 1).toString();
				byte[] ibyte = r.getBytes();
			
			
				byte[]ebyte=encrypt(raw,ibyte);
		
		
				
				byte[] dbyte= decrypt(raw, ebyte);
				String decryptedMessage = new String(dbyte);
				System.out.println("Decrypted Message"+ decryptedMessage);
				JOptionPane.showMessageDialog(null, "Decrypted",decryptedMessage, 0 );
				
				salaire.setText(decryptedMessage);  
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}

			private byte[] encrypt(byte[] raw, byte[] clear) throws Exception{
				SecretKeySpec skeySpec = new SecretKeySpec( raw, "Blowfish");
				Cipher cipher = Cipher.getInstance("Blowfish");
				cipher.init(Cipher.ENCRYPT_MODE,skeySpec);
				byte[] encrypted = cipher.doFinal(clear);
				return encrypted;
			}
			private byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
				SecretKeySpec skeySpec = new SecretKeySpec( raw, "Blowfish");
				Cipher cipher = Cipher.getInstance("Blowfish");
				cipher.init(Cipher.DECRYPT_MODE,skeySpec);
				byte[] decrypted = cipher.doFinal(encrypted);
				return decrypted;
			}
			private void generateSymmetricKey() {
				try {
					
					
					Random r = new Random();
					int num = r.nextInt(1000);
					String knum = String.valueOf(num);
					byte[] knumb = knum.getBytes();
					skey=(byte[]) getRawKey(knumb);
					skeyString = new String(skey);
					System.out.println("Blowfish"+ skeyString);
				}
				catch(Exception e1) {
					System.out.println(e1);
				}
			}
			private byte[] getRawKey(byte[] seed) throws Exception {
				
				KeyGenerator kgen = KeyGenerator.getInstance("Blowfish");
				SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
				sr.setSeed(seed);
				kgen.init(128,sr);
				SecretKey skey = kgen.generateKey();
				raw = skey.getEncoded();
				return raw;
						
				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 452, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 246, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "salaire"
			}
		));
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
	}
}
