/***
 * Auteur: Rosine Laridain
 * Groupe: F-society
 * Developer: En Mars-Avril
 */

/***
 * Consiste a developpé une interface pour le humaine ressources, pour gerer la partie employé
 */
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.SecureRandom;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.*;

public class supercaremployee {

	private JFrame frame;
	private JTextField nom;
	private JTextField prenom;
	private JTextField adresse;
	private JTextField salaire;
	private JTextField date_entree;
	private JTextField mail;
	private JTextField tel;
	private JTable table;
	private JComboBox deptcombobox;
	private JComboBox typecomboBox;
	private JComboBox actcomboBox;

	/**
	 * Launch the application.
	 */
	
	public static void supercaremployee() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					supercaremployee window = new supercaremployee();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
/** 
 * pour pouvoir ouvrir la page employee cette page 
 * @param args
 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					supercaremployee window = new supercaremployee();
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
	public supercaremployee() {
		initialize();
		/**
		 * pour mettre a jour la table employé
		 */
		table_update();
	}
	
	/**
	 * Pour mettre a jour la table employé
	 * 
	 */
	private void table_update() {
		int c;
		try {
			Connection conn;
			PreparedStatement insert;
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/supercarjava","root","");
			insert = conn.prepareStatement("select * from employe");
			ResultSet rs = insert.executeQuery();
			ResultSetMetaData Rss =  (ResultSetMetaData) rs.getMetaData();
			c=Rss.getColumnCount();
			
			DefaultTableModel tab = (DefaultTableModel)table.getModel();
			
			tab.setRowCount(0);
			while(rs.next()) {
				 
				Vector v2 = new Vector();
				
				for (int a =1; a<=c; a++) {
					/**
					 *  pour afficher les données qui sont dans la base de données 
					 */
					v2.add(rs.getString("ID_EMPLOYE"));
					v2.add(rs.getString("NOM"));
					v2.add(rs.getString("PRENOM"));
					v2.add(rs.getString("ADRESSE"));
					v2.add(rs.getString("SALAIRE"));
					v2.add(rs.getString("DATE_ENTREE"));
					v2.add(rs.getString("EMAIL"));
					v2.add(rs.getString("TEL"));
					v2.add(rs.getString("TYPE"));
					v2.add(rs.getString("ID_DEPT"));
					v2.add(rs.getString("ACTIVE"));
					v2.add(rs.getString("test"));
					
					
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
public void fillcombobox() {
/**
 * 	pour afficher les departments disponible comme list
 */
	try {
		Connection conn;
		PreparedStatement insert;
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost/supercarjava","root","");
		
		//pour selectionner les données
		String query= "select * from dept";
		PreparedStatement test = conn.prepareStatement(query);
		ResultSet rs= test.executeQuery();
		while(rs.next()) {
			deptcombobox.addItem(rs.getString("ID_DEPT"));
		}
	}
	catch (ClassNotFoundException e1) {
		
		e1.printStackTrace();
	}
	catch (Exception e1) {
	
		e1.printStackTrace();
	}
	
}
public void close() {	
	frame.setVisible(false);
}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1300, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblEmploye = new JLabel("Employ\u00E9e");
		lblEmploye.setFont(new Font("Tahoma", Font.BOLD, 37));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		/**
		 * pour retourner en arriers
		 */
		JButton button = new JButton("Go Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**
				 * pour fermer la page supercaremployee
				 */
			close();
			
		/**
		 * et ouvrir la page hr
		 */
				hr  s =new hr();
				s.hr();
			
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 445, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(68)
							.addComponent(lblEmploye))
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 820, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEmploye)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(144, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(13, Short.MAX_VALUE)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 483, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				/**
				 * pour afficher les données selectionner
				 */
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				nom.setText(tab.getValueAt(selectedIndex, 1).toString()); 
				
				prenom.setText(tab.getValueAt(selectedIndex, 2).toString());  
				
				adresse.setText(tab.getValueAt(selectedIndex, 3).toString());  
				
				salaire.setText(tab.getValueAt(selectedIndex, 11).toString());  
				date_entree.setText(tab.getValueAt(selectedIndex, 5).toString());  
				mail.setText(tab.getValueAt(selectedIndex, 6).toString());  
				
				tel.setText(tab.getValueAt(selectedIndex, 7).toString());  
				
				typecomboBox.setToolTipText(tab.getValueAt(selectedIndex, 8).toString());  
				deptcombobox.setToolTipText(tab.getValueAt(selectedIndex, 9).toString());  
				actcomboBox.setToolTipText(tab.getValueAt(selectedIndex, 10).toString());  
				
					
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "Nom", "Pr\u00E9nom", "Adresse", "Salaire", "Date_Entr\u00E9e", "E-mail", "Tel", "Type", "Dept", "Active/Inactive", "TEST"
			}
		));
		table.getColumnModel().getColumn(11).setPreferredWidth(0);
		table.getColumnModel().getColumn(11).setMinWidth(0);
		table.getColumnModel().getColumn(11).setMaxWidth(0);
		scrollPane.setViewportView(table);
		panel_1.setLayout(gl_panel_1);
		JLabel s = new JLabel("");
		s.setForeground(Color.RED);
		JLabel d = new JLabel("");
		d.setForeground(Color.RED);
			
		JLabel g = new JLabel("");
		g.setForeground(Color.RED);
		
		JLabel p = new JLabel("");
		JLabel q = new JLabel("");
		JLabel lblNewLabel = new JLabel("Pr\u00E9nom");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblSalaire = new JLabel("Salaire");
		lblSalaire.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblDateentre = new JLabel("Date_Entr\u00E9e");
		lblDateentre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblMail = new JLabel("Mail");
		lblMail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblTel = new JLabel("Tel");
		lblTel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblIddept = new JLabel("ID_Dept");
		lblIddept.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblActiveinactive = new JLabel("Inactive(0)/Active(1)");
		lblActiveinactive.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		nom = new JTextField();
		nom.addKeyListener(new KeyAdapter() {
			
public void keyReleased(KeyEvent e) {
				
				String PATTERN ="^[a-zA-Z]{0,45}";
				Pattern patt=Pattern.compile(PATTERN);
				Matcher match= patt.matcher(nom.getText());
			
				if (!match.matches()) {
					q.setText("pas de chiffres!!");
				}
				else {
					q.setText(null);
				}
				
			}
		});
		nom.setColumns(10);
		
		prenom = new JTextField();
		prenom.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String PATTERN ="^[a-zA-Z]{0,45}";
				Pattern patt=Pattern.compile(PATTERN);
				Matcher match= patt.matcher(prenom.getText());
			
				if (!match.matches()) {
					p.setText("pas de chiffres!!");
				}
				else {
					p.setText(null);
				}
				
			}
				
			
		});
		prenom.setColumns(10);
		
		adresse = new JTextField();
		adresse.setColumns(10);
		
		salaire = new JTextField();
		salaire.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String PATTERN = "^[0-9]{0,30}$";
				Pattern patt=Pattern.compile(PATTERN);
				Matcher match= patt.matcher(salaire.getText());
			
				if (!match.matches()) {
					s.setText("pas de lettre!!");
				}
				else {
					s.setText(null);
				}
				
			}
		});
		salaire.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				
				if (salaire.getText().equals("e.g 12.00")){
					salaire.setText("");
					salaire.setForeground(new Color(153,153,153));
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (salaire.getText().equals("")){
					salaire.setText("e.g 12.00");
					salaire.setForeground(new Color(153,153,153));
				}
				
			}
		});
		salaire.setForeground(Color.LIGHT_GRAY);
		salaire.setText("e.g 12.00");
		salaire.setColumns(10);
		
		date_entree = new JTextField();
		date_entree.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent e) {

				
				
			}
		});
		/**
		 * pour afficher la fason donc les utilisateur doivent ecrire
		 */
		date_entree.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (date_entree.getText().equals("yyyy-mm-dd")){
					date_entree.setText("");
					date_entree.setForeground(new Color(153,153,153));
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (date_entree.getText().equals("")){
					date_entree.setText("yyyy-mm-dd");
					date_entree.setForeground(new Color(153,153,153));
				}
			}
		});
		date_entree.setForeground(Color.LIGHT_GRAY);
		date_entree.setText("yyyy-mm-dd");
		date_entree.setColumns(10);
		
		mail = new JTextField();
		mail.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				String PATTERN = "^[a-zA-Z0-9]{0,30}[@][a-zA-Z]{0,10}[.][a-zA-Z]{0,10}$";
				Pattern patt=Pattern.compile(PATTERN);
				Matcher match= patt.matcher(mail.getText());
			
				if (!match.matches()) {
					g.setText("incorect!!");
				}
				else {
					g.setText(null);
				}
			}
			
		});
		mail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (mail.getText().equals("john@gmail.com")){
					mail.setText("");
					mail.setForeground(new Color(153,153,153));
				}
				
			}
			@Override
			public void focusLost(FocusEvent e) {
				if (mail.getText().equals("")){
					mail.setText("john@gmail.com");
					mail.setForeground(new Color(153,153,153));
				}
				
			}
		});
		mail.setForeground(Color.LIGHT_GRAY);
		mail.setText("john@gmail.com");
		mail.setColumns(10);
		
		tel = new JTextField();
		tel.setColumns(10);
		
		 deptcombobox = new JComboBox();
		 table_update();
		 fillcombobox();
		 actcomboBox = new JComboBox();
		actcomboBox.setModel(new DefaultComboBoxModel(new String[] {"0", "1"}));
		
		/***
		 * pour ajouter les données
		 */
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			byte[] raw = null;
			byte[]skey= new byte[1000];
			String skeyString;
			
			String inputMessage, encryptedData,decryptedMessage;
			public void actionPerformed(ActionEvent e) {
				try {
					
				Connection conn;
				PreparedStatement insert;
				String NOM = nom.getText(); 
				String PRENOM = prenom.getText(); 
				String ADRESSE = adresse.getText(); 
				
				String SALAIRE = salaire.getText();  
				String DATE_ENTREE = date_entree.getText(); 
				String EMAIL = mail.getText(); 
				String TEL = tel.getText();
				String TYPE = typecomboBox.getSelectedItem().toString();
				
				String ID_DEPT = deptcombobox.getSelectedItem().toString();
				String ACTIVE = actcomboBox.getSelectedItem().toString();
				
				/**
				 * pour crypter la parti salaire 
				 */
				
				generateSymmetricKey();
				byte[] ibyte = SALAIRE.getBytes();
				byte[]ebyte=encrypt(raw,ibyte);
				String encryptedData1 = new String(ebyte);
				System.out.println("Encrypted Message"+ encryptedData1);
				JOptionPane.showMessageDialog(null, "Encrypted Data",encryptedData1, 0 );
				byte[] dbyte= decrypt(raw, ebyte);
				/**
				 * pour decrypter la parti salaire
				 */
				String decryptedMessage = new String(dbyte);
				System.out.println("Decrypted Message"+ decryptedMessage);
				JOptionPane.showMessageDialog(null, "Decrypted",decryptedMessage, 0 );
				
				
				/**
				 * si le champs est vide, on va aficher un erreur
				 */
				if (NOM.equals("")) {
					JOptionPane.showMessageDialog(null, "champs Nom est vide ");
				}
				if (PRENOM.equals("")) {
					JOptionPane.showMessageDialog(null, "champs Prenom est vide ");
				}
				if (ADRESSE.equals("")) {
					JOptionPane.showMessageDialog(null, "champs Adresse est vide ");
				}if (SALAIRE.equals("")) {
					JOptionPane.showMessageDialog(null, "champs Salaire est vide ");
				}if (DATE_ENTREE.equals("")) {
					JOptionPane.showMessageDialog(null, "champs date est vide ");
				}if (EMAIL.equals("")) {
					JOptionPane.showMessageDialog(null, "champs Email est vide ");
				}if (TEL.equals("")) {
					JOptionPane.showMessageDialog(null, "champs Telephone est vide ");
				}
				
				/**
				 * afficher des erreurs si contient des chiffres
				 */
				if (!NOM.matches("[A-Za-z]+")) {
					JOptionPane.showMessageDialog(null, "Le champs Nom peut pas contenir des chiffres");
				}
				if (!PRENOM.matches("[a-zA-Z]+")) {
					JOptionPane.showMessageDialog(null, "Le champs Prenom peut pas contenir des chiffres");
				}
				/**
				 * pour afficher des erreurs si le champs contient des lettres
				 */
				if (!SALAIRE.matches("[0-9]+")) {
					JOptionPane.showMessageDialog(null, "Le champs Salaire peut pas contenir des chiffres");
				}
				/**
				 * pour voir si la parti emal est ecrit correctement
				 */
				if (!EMAIL.matches("[a-zA-Z0-9]{0,30}[@][a-zA-Z]{0,10}[.][a-zA-Z]{0,10}+")) {
					JOptionPane.showMessageDialog(null, "Le champs Email incorrect");
				}
				else {
				
				
					
					/**
					 * 	cette partie on ajoute les données dans la base de dpnnées
					 */
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost/supercarjava","root","");
					insert = conn.prepareStatement("insert into employe(NOM,PRENOM,ADRESSE,SALAIRE,DATE_ENTREE,EMAIL,TEL,TYPE,ID_DEPT,ACTIVE,test)values(?,?,?,?,?,?,?,?,?,?,?)");
					insert.setString(1, NOM);
					insert.setString(2, PRENOM);
					insert.setString(3, ADRESSE);
					
					insert.setString(4, encryptedData1);
					
					insert.setString(5, DATE_ENTREE);
					insert.setString(6, EMAIL);
					insert.setString(7, TEL);
					insert.setString(8, TYPE);
					
					insert.setString(9, ID_DEPT);
					
					insert.setString(10, ACTIVE);
					insert.setString(11, decryptedMessage);
					
					insert.executeUpdate();
					JOptionPane.showMessageDialog(btnAjouter, this, "Record Added", 0);
				
				} }catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
				catch (Exception e1) {
				
					e1.printStackTrace();
				}
				
				
				table_update();
				
				}
			/**
			 * pour crypter les données
			 * @param raw
			 * @param clear
			 * @return
			 * @throws Exception
			 */
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
			/**
			 * pour decrypter le salaire
			 * @param raw
			 * @param encrypted
			 * @return
			 * @throws Exception
			 */
			private byte[] decrypt(byte[] raw, byte[] encrypted) throws Exception {
				SecretKeySpec skeySpec = new SecretKeySpec( raw, "Blowfish");
				Cipher cipher = Cipher.getInstance("Blowfish");
				cipher.init(Cipher.DECRYPT_MODE,skeySpec);
				byte[] decrypted = cipher.doFinal(encrypted);
				return decrypted;
			}
				
		});
		btnAjouter.setFont(new Font("Tahoma", Font.BOLD, 15));
		/***
		 * pour modifier une données
		 */
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection conn;
				PreparedStatement insert;
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				
				String NOM = nom.getText(); 
				String PRENOM = prenom.getText(); 
				String ADRESSE = adresse.getText(); 
				String SALAIRE = salaire.getText();  
				String DATE_ENTREE = date_entree.getText(); 
				String EMAIL = mail.getText(); 
				String TEL = tel.getText();
				String TYPE = typecomboBox.getSelectedItem().toString();
				
				String ID_DEPT = deptcombobox.getSelectedItem().toString();
				String ACTIVE = actcomboBox.getSelectedItem().toString();
				
				
				
				try {
					int ID_EMPLOYE= Integer.parseInt(tab.getValueAt(selectedIndex, 0).toString());
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost/supercarjava","root","");
					insert = conn.prepareStatement("update employe set NOM=?,PRENOM=?,ADRESSE=?,SALAIRE=?,DATE_ENTREE=?,EMAIL=?,TEL=?,TYPE=?,ID_DEPT=?,ACTIVE=? where ID_EMPLOYE=? ");
					insert.setString(1, NOM);
					insert.setString(2, PRENOM);
					insert.setString(3, ADRESSE);
					insert.setString(4, SALAIRE);
					insert.setString(5, DATE_ENTREE);
					insert.setString(6, EMAIL);
					insert.setString(7, TEL);
					insert.setString(8, TYPE);
					
					insert.setString(9, ID_DEPT);
					
					insert.setString(10, ACTIVE);
					insert.setInt(11, ID_EMPLOYE);
					insert.executeUpdate();
					JOptionPane.showMessageDialog( btnModifier, this, "Record Added", 0);
					
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
				catch (Exception e1) {
				
					e1.printStackTrace();
				}
				
				
				table_update();
			}
		});
		btnModifier.setFont(new Font("Tahoma", Font.BOLD, 15));
		/***
		 * pour effaser les donnée du formulaire
		 */
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nom.setText("");
				prenom.setText("");
				adresse.setText("");
				salaire.setText("");
				date_entree.setText("");
				mail.setText("");
				tel.setText("");
			}
		});
		btnAnnuler.setFont(new Font("Tahoma", Font.BOLD, 15));
		
	 typecomboBox = new JComboBox();
		typecomboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
		
	
		q.setFont(new Font("Tahoma", Font.ITALIC, 12));
		q.setForeground(Color.RED);
		
		
		p.setForeground(Color.RED);
		
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIddept, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblActiveinactive, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnAjouter)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnModifier))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblNom, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
								.addComponent(lblAdresse, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblSalaire, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(s)
								.addComponent(p)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblDateentre, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(d))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lblTel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblMail, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(g)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(56)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(tel, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
								.addComponent(mail, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
								.addComponent(date_entree, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
								.addComponent(salaire, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
								.addComponent(adresse, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
								.addComponent(prenom, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
								.addComponent(nom, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
								.addComponent(deptcombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(actcomboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(typecomboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(btnAnnuler, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(229))))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(141)
					.addComponent(q)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNom, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(nom, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(q, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE))
					.addGap(2)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(prenom, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(p))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblAdresse, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSalaire, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(s))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDateentre, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(d))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMail, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
								.addComponent(g))
							.addGap(18)
							.addComponent(lblTel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(typecomboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIddept, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(deptcombobox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblActiveinactive, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(actcomboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(adresse, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(salaire, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(date_entree, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(mail, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(tel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAjouter)
							.addComponent(btnModifier, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnAnnuler, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(55))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
	}

	

	
}
