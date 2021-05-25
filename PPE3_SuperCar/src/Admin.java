/**
 * Auteur: Rosine Laridain
 * Groupe: F-society
 * Date: "2020-2021
 */

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Admin {

	private JFrame frame;
	private JTextField Nom;
	private JTextField Prenom;
	private JTextField Email;
	private JTable table;
	private JPasswordField MDP;
	private JPasswordField CMDP;
	private JComboBox typecomboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void Admin() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin window = new Admin();
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
	/**
	 * Create the application.
	 */
	public Admin() {
		initialize();
		table_update();
	}
	
	/**
	 * Afficher les données  dans la table et aussi afficher les données modifier ou ajouter et retirer aussi la donnée supprimer 
	 */
	private void table_update() {
		int c;
		try {
			Connection conn;
			PreparedStatement insert;
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/supercarjava","root","");
			insert = conn.prepareStatement("select * from admin");
			ResultSet rs = insert.executeQuery();
			ResultSetMetaData Rss =  (ResultSetMetaData) rs.getMetaData();
			c=Rss.getColumnCount();
			
			DefaultTableModel tab = (DefaultTableModel)table.getModel();
			
			tab.setRowCount(0);
			while(rs.next()) {
				 
				Vector v2 = new Vector();
				
				for (int a =1; a<=c; a++) {
					
					v2.add(rs.getString("id"));
					v2.add(rs.getString("nom"));
					v2.add(rs.getString("prenom"));
					v2.add(rs.getString("email"));
					v2.add(rs.getString("user_type"));
					
					
					
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
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 950, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Create a new user", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		
		JButton btnNewButton = new JButton("Log out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				close();
				login  s =new login();
				s.login();
			}
		});
		
		JLabel lblNewLabel = new JLabel("Les Utilisateurs ");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 524, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addComponent(btnNewButton)
							.addGap(226)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE))
					.addGap(24))
		);
		/**
		 * la donnée qu'on va selectioner va afficher dans le formulaire
		 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				
				/**
				 * par exemple field nom aura le donné de la colonne 1
				 */
				Nom.setText(tab.getValueAt(selectedIndex, 1).toString());  
				Prenom.setText(tab.getValueAt(selectedIndex, 2).toString());  
				Email.setText(tab.getValueAt(selectedIndex, 3).toString());  
				
				 
				
				
				
			}
		});
		scrollPane.setBackground(Color.WHITE);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(21)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(501, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(331, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"id", "Nom ", "Prenom", "Email", "user_type"
			}
		));
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Dialog", Font.BOLD, 20));
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setFont(new Font("Dialog", Font.BOLD, 20));
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 20));
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setFont(new Font("Dialog", Font.BOLD, 20));
		
		JLabel lblConfirmerMotDe = new JLabel("Confirmer MDP");
		lblConfirmerMotDe.setFont(new Font("Dialog", Font.BOLD, 20));
		
		/**
		 * pour effaser les données dans le formulaire
		 */
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Nom.setText("");
				Prenom.setText("");
				Email.setText("");
				MDP.setText("");
				CMDP.setText("");
				
				
			}
		});
		btnAnnuler.setFont(new Font("Tahoma", Font.BOLD, 15));
		/**
		 * pour inserer les données dans la base de donnée
		 */
		JButton btnConfirmer = new JButton("Ajouter");
		btnConfirmer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/**
				 * pour voir si le Mot de passe contient au moins un majuscule, un caractere special, un minuscule et un chiffre 
				 */
				String regex = "^(?=.*[a-z])(?=."
	                       + "*[A-Z])(?=.*\\d)"
	                       + "(?=.*[-+_!@#$%^&*., ?]).+$";
				
				
				Pattern p = Pattern.compile(regex);
			
				String n = Nom.getText();
				String er = Prenom.getText();
				String cou = Email.getText();
				String y = MDP.getText();
				String w = CMDP.getText();
				
				
				/**
				 * Afficher des erreur si les champs sont vides
				 */
				if (n.equals("")) {
					JOptionPane.showMessageDialog(null, "champs Nom est vide ");
				}
				if (er.equals("")) {
					JOptionPane.showMessageDialog(null, "champs Prenom est vide ");
				}
				if (cou.equals("")) {
					JOptionPane.showMessageDialog(null, "champs Nom est vide ");
				}
				/**
				 * pour voir si le champs ne contient pas des chiffres
				 */
				if (!n.matches("[A-Za-z]+")) {
					JOptionPane.showMessageDialog(null, "Le champs Nom peut pas contenir des chiffres");
				}
				if (!er.matches("[a-zA-Z]+")) {
					JOptionPane.showMessageDialog(null, "Le champs Prenom peut pas contenir des chiffres");
				}
				/**
				 * pour voir si l'adresse email est ecrit correctement
				 */
				if (!cou.matches("[a-z-Z0-9]{0,30}[@][a-zA-Z]{0,10}[.][a-zA-Z]{0,10}+")) {
					JOptionPane.showMessageDialog(null, "Le champs Email incorrect");
				}
				/**
				 * pour tester si le MDP a minimum de 12 caracteres
				 */
				if (y.length()<12) {
					JOptionPane.showMessageDialog(null, "trop court faut min 12 caractere");
				}
				/**
				 * pour tester si le MDP contient les caracter demander
				 */
				Matcher s = p.matcher(y);
				if (!s.matches()) {
					JOptionPane.showMessageDialog(null, "password incorrect parce que faut respecter le format, Un capital, petit lettre, des chiffres, des caratere speciaux");
				}
				/**
				 * pour tester si le MDP et la partie Confirmer MDP est pareil
				 */
				if (!y.equals( w)) {
					JOptionPane.showMessageDialog(null, "Mot de passe pas pareil ");
				}
				
				else {
				try {
					PreparedStatement insert;
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava","root","");
					/**
					 * pour verifier si le nom, prenom et email n'existe pas
					 */
					insert = conn.prepareStatement("select nom, prenom, email from admin where nom=? and prenom=? and email=? ");
			
					
					
					insert.setString(1, n);
					insert.setString(2, er);
					insert.setString(3, cou);
					
					
					
					
					ResultSet rs = insert.executeQuery();
					
					if (rs.next()) {
						
						JOptionPane.showMessageDialog(   btnConfirmer, this, "Exist Deja", 0);
					}
					else {
						
						/**
						 * si les données n'existe pas on va inserer le nouveau utilisateur
						 */
				PreparedStatement ps = conn.prepareStatement("insert into admin(nom,prenom,email,password,user_type) values(?,?,?,?,?)");
	
				ps.setString(1, Nom.getText());
				ps.setString(2, Prenom.getText());
				ps.setString(3, Email.getText());
				ps.setString(4, MDP.getText());
				ps.setString(5,typecomboBox.getSelectedItem().toString()); 
				
				
				int x= ps.executeUpdate();
				if (x > 0) {
					System.out.println("Sucess");
				}
				else {
					System.out.println("fail");
				}
					}
				} catch (Exception e1){
					System.out.println(e1);
				}
				table_update();
				
				}
				
			}
			
			
			
			
		});
		btnConfirmer.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		Nom = new JTextField();
		Nom.setColumns(10);
		
		Prenom = new JTextField();
		Prenom.setColumns(10);
		
		Email = new JTextField();
		Email.setColumns(10);
		/**
		 * pour modifier les données
		 */
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection conn;
				PreparedStatement insert;
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				try {
					int id= Integer.parseInt(tab.getValueAt(selectedIndex, 0).toString());
				
					String nom = Nom.getText(); 
					String prenom = Prenom.getText(); 
					String email = Email.getText(); 
					
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost/supercarjava","root","");
					insert = conn.prepareStatement("update admin set nom=?, prenom=?, email=? where id=?");
			
					insert.setString(1, nom);
					insert.setString(2, prenom);
					insert.setString(3, email);
					insert.setInt(4, id);
					insert.executeUpdate();


					
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
		/**
		 * pour supprimer une donnée
		 */
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection conn;
				PreparedStatement insert;
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();	
				try {
					int ID_DEPT= Integer.parseInt(tab.getValueAt(selectedIndex, 0).toString());
					int dialogResult = JOptionPane.showConfirmDialog(null, "Voulez- vous vraiment effacer cette donnés","warning", JOptionPane.YES_NO_OPTION);
					if(dialogResult==JOptionPane.YES_OPTION) {
						Class.forName("com.mysql.cj.jdbc.Driver");
						conn = DriverManager.getConnection("jdbc:mysql://localhost/supercarjava","root","");
						insert = conn.prepareStatement("delete from admin where id=? ");
					
						insert.setInt(1, ID_DEPT);
						insert.executeUpdate();
						JOptionPane.showMessageDialog(  btnSupprimer, this, "Deleted", dialogResult);
						table_update();
					}
					

					
					
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
				catch (Exception e1) {
				
					e1.printStackTrace();
				}
				table_update();	
				
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		MDP = new JPasswordField();
		
		CMDP = new JPasswordField();
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Dialog", Font.BOLD, 20));
		
		 typecomboBox = new JComboBox();
		typecomboBox.setModel(new DefaultComboBoxModel(new String[] {"Type", "manager", "utilisateur"}));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnSupprimer, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnConfirmer, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
						.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNom, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPrenom, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 106, Short.MAX_VALUE))
								.addComponent(lblConfirmerMotDe, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(lblMotDePasse, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnAnnuler, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(CMDP, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
									.addComponent(btnModifier, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
									.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(MDP, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)))
								.addGroup(Alignment.TRAILING, gl_panel.createParallelGroup(Alignment.LEADING, false)
									.addComponent(Nom, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
									.addComponent(Prenom, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
									.addComponent(Email, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
									.addComponent(typecomboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNom, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(Nom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrenom, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(Prenom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(Email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(typecomboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblMotDePasse, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(MDP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblConfirmerMotDe, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(CMDP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAnnuler)
						.addComponent(btnModifier, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSupprimer, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnConfirmer, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
	}
}
