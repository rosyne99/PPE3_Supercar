/***
 * Auteur: Rosine Laridain
 * Group: FSociety
 * Date:2020-2021
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;
import java.sql.Statement;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.*;
public class supercarclient {

	private JFrame frame;
	private JTextField NOM;
	private JTextField PRENOM;
	private JTextField LICENCE;
	private JTextField ADRESSE;
	private JTextField EMAIL;
	private JTextField TEL;
	private JTable table;
	private JTextField d;
	private JTextField identity;

	/**
	 * Launch the application.
	 */
	//pour afficher les données dans la table
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava","root","");
			Statement smt=cn.createStatement();
			
			
			String q="Select * from client";
			
		
			ResultSet rs=smt.executeQuery(q);
			
		
			if(rs.next()){ 
				do{
				System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5));
				}while(rs.next());
			}
			else{
				System.out.println("Record Not Found...");
			}
			cn.close();
		}
		catch(Exception e){
			System.out.println(e);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					supercarclient window = new supercarclient();
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
	public supercarclient() {
		initialize();
		//pour afficher les données dans la table et mise a jour si une donées a ete modifier
		table_update() ;
	}
	
	//pour afficher les données et afficher les données modifier aussi
	private void table_update() {
		int c;
		try {
			Connection conn;
			PreparedStatement insert;
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/supercarjava","root","");
			
			//selectionner dans la base de donnée de client
			insert = conn.prepareStatement("select * from client");
			ResultSet rs = insert.executeQuery();
			ResultSetMetaData Rss =  (ResultSetMetaData) rs.getMetaData();
			c=Rss.getColumnCount();
			
			DefaultTableModel tab = (DefaultTableModel)table.getModel();
			
			tab.setRowCount(0);
			while(rs.next()) {
				 
				Vector v2 = new Vector();
				
				for (int a =1; a<=c; a++) {
					//les données qu'on va afficher
					v2.add(rs.getString("ID_CLIENT"));
					v2.add(rs.getString("NOM"));
					v2.add(rs.getString("PRENOM"));
					v2.add(rs.getString("LICENCE"));
					v2.add(rs.getString("ADRESSE"));
					v2.add(rs.getString("EMAIL"));
					v2.add(rs.getString("TEL"));
				
					
					
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
		frame.setBounds(100, 100, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblClient = new JLabel("Client");
		lblClient.setFont(new Font("Times New Roman", Font.BOLD, 35));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(290)
					.addComponent(lblClient)
					.addContainerGap(472, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblClient)
							.addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE)))
					.addGap(29))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// pour afficher les données selectionner 
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				NOM.setText(tab.getValueAt(selectedIndex, 1).toString()); 
				
				PRENOM.setText(tab.getValueAt(selectedIndex, 2).toString());
				LICENCE.setText(tab.getValueAt(selectedIndex, 3).toString()); 
				
				ADRESSE.setText(tab.getValueAt(selectedIndex, 4).toString());  
				
				 
				
				EMAIL.setText(tab.getValueAt(selectedIndex, 5).toString());  
				
				TEL.setText(tab.getValueAt(selectedIndex, 6).toString());  
				
				
			}
		});
		scrollPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nom", "Pr\u00E9nom", "Licence", "Addresse", "Email", "Tel"
			}
		));
		scrollPane.setViewportView(table);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		JLabel g = new JLabel("");
		JLabel P = new JLabel("");
		JLabel N = new JLabel("");
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom");
		lblPrnom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblLicence = new JLabel("Licence");
		lblLicence.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblTlphone = new JLabel("T\u00E9l\u00E9phone");
		lblTlphone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		NOM = new JTextField();
		NOM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				String PATTERN = "^[a-zA-Z]{0,30}[ ]{0,30}[a-zA-Z]{0,30}[ ]{0,30}[a-zA-Z]{0,30}$";
				Pattern patt=Pattern.compile(PATTERN);
				Matcher match= patt.matcher(NOM.getText());
			
				if (!match.matches()) {
					N.setText("pas de CHIFFRE!!");
				}
				else {
					N.setText(null);
				}
			}
		});
		NOM.setColumns(10);
		
		PRENOM = new JTextField();
		PRENOM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String PATTERN = "^[a-zA-Z]{0,30}[ ]{0,30}[a-zA-Z]{0,30}[ ]{0,30}[a-zA-Z]{0,30}$";
				Pattern patt=Pattern.compile(PATTERN);
				Matcher match= patt.matcher(PRENOM.getText());
			
				if (!match.matches()) {
					P.setText("pas de CHIFFRE!!");
				}
				else {
					P.setText(null);
				}
				
				
			}
			
			
			
		});
		PRENOM.setColumns(10);
		
		LICENCE = new JTextField();
		LICENCE.setColumns(10);
		
		ADRESSE = new JTextField();
		ADRESSE.setColumns(10);
		
		EMAIL = new JTextField();
		EMAIL.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String PATTERN = "^[a-zA-Z0-9]{0,30}[@][a-zA-Z]{0,10}[.][a-zA-Z]{0,10}$";
				Pattern patt=Pattern.compile(PATTERN);
				Matcher match= patt.matcher(EMAIL.getText());
			
				if (!match.matches()) {
					g.setText("incorect!!");
				}
				else {
					g.setText(null);
				
			}
			}
		});
		EMAIL.setColumns(10);
		
		TEL = new JTextField();
		TEL.setColumns(10);
		
		JButton btnAnnuler = new JButton("Supprimer");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//pour supprimer la données selcetionner 
				Connection conn;
				PreparedStatement insert;
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();	
				try {
					int ID_CLIENT= Integer.parseInt(tab.getValueAt(selectedIndex, 0).toString());
					int dialogResult = JOptionPane.showConfirmDialog(null, "Voulez- vous vraiment effacer cette donnés","warning", JOptionPane.YES_NO_OPTION);
					if(dialogResult==JOptionPane.YES_OPTION) {
						Class.forName("com.mysql.cj.jdbc.Driver");
						conn = DriverManager.getConnection("jdbc:mysql://localhost/supercarjava","root","");
						insert = conn.prepareStatement("delete from client where ID_CLIENT=? ");
					
						insert.setInt(1, ID_CLIENT);
						insert.executeUpdate();
						JOptionPane.showMessageDialog( btnAnnuler, this, "Deleted", dialogResult);
						table_update();
					}
					

					
					
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
				catch (Exception e1) {
				
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnSave = new JButton("Ajouter");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				//pour ajouter les données dans la base de donnée
				if (NOM.equals("")) {
					JOptionPane.showMessageDialog(null, "champs Nom est vide ");
				}
				if (PRENOM.equals("")) {
					JOptionPane.showMessageDialog(null, "champs Prenom est vide ");
				}
				if (ADRESSE.equals("")) {
					JOptionPane.showMessageDialog(null, "champs Adresse est vide ");
				}if (LICENCE.equals("")) {
					JOptionPane.showMessageDialog(null, "champs LICENCE est vide ");
				}if (EMAIL.equals("")) {
					JOptionPane.showMessageDialog(null, "champs Email est vide ");
				}if (TEL.equals("")) {
					JOptionPane.showMessageDialog(null, "champs Telephone est vide ");
				}
				if (NOM.equals("[a-zA-Z]+")) {
					JOptionPane.showMessageDialog(null, "Le champs Nom peut pas contenir des chiffres");
				}
				if (!PRENOM.equals("[a-zA-Z]+")) {
					JOptionPane.showMessageDialog(null, "Le champs Prenom peut pas contenir des chiffres");
				}
				
				if (!EMAIL.equals("[a-zA-Z0-9]{0,30}[@][a-zA-Z]{0,10}[.][a-zA-Z]{0,10}+")) {
					JOptionPane.showMessageDialog(null, "Le champs Email peut pas contenir des chiffres");
				}
				else {
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava","root","");
				PreparedStatement ps = conn.prepareStatement("insert into client(NOM, PRENOM, LICENCE,ADRESSE,EMAIL,TEL) values(?,?,?,?,?,?)");
				ps.setString(1, NOM.getText());
				ps.setString(2, PRENOM.getText());
				ps.setString(3, LICENCE.getText());
				ps.setString(4, ADRESSE.getText());
				ps.setString(5, EMAIL.getText());
				ps.setString(6, TEL.getText());
				
				int x= ps.executeUpdate();
				if (x > 0) {
					System.out.println("Sucess");
				}
				else {
					System.out.println("fail");
				}
				} catch (Exception e1){
					System.out.println(e1);
				}
				table_update() ;
			}}
		});
		//pour modifier une données dan la base 
		JButton btnEdit = new JButton("Modifier");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				Connection conn;
				PreparedStatement insert;
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				try {
					int ID_CLIENT= Integer.parseInt(tab.getValueAt(selectedIndex, 0).toString());
					String nom = NOM.getText(); 
					String prénom = PRENOM.getText(); 
					String licence = LICENCE.getText(); 
					String adresse = ADRESSE.getText();  
					String email = EMAIL.getText(); 
					String tel = TEL.getText(); 
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost/supercarjava","root","");
					insert = conn.prepareStatement("update client set 	NOM=?,PRENOM=?,LICENCE=?,ADRESSE=?,EMAIL=?,TEL=? where ID_CLIENT=? ");
					insert.setString(1, nom);
					insert.setString(2, prénom);
					insert.setString(3, licence);
					insert.setString(4, adresse);
					
					
					insert.setString(5, email);
					insert.setString(6, tel);
			
					insert.setInt(7, ID_CLIENT);
					insert.executeUpdate();


					
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
				catch (Exception e1) {
				
					e1.printStackTrace();
				}
				table_update() ;
			}
		});
		
		
		N.setForeground(Color.RED);
		
	
		P.setForeground(Color.RED);
		
	
		g.setForeground(Color.RED);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		d = new JTextField();
		d.setColumns(10);
		
		JLabel lblNic = new JLabel("NID");
		lblNic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		identity = new JTextField();
		identity.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblNom)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(N))
										.addComponent(lblEmail)
										.addComponent(lblTlphone)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblPrnom)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(P)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(g)
									.addGap(18)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(TEL, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
										.addComponent(EMAIL, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
										.addComponent(PRENOM, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
										.addComponent(NOM, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
										.addComponent(LICENCE, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
										.addComponent(ADRESSE, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
										.addComponent(d, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
										.addComponent(identity, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblAdresse)
									.addComponent(lblLicence))))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnAnnuler)
							.addGap(30)
							.addComponent(btnSave)
							.addGap(28)
							.addComponent(btnEdit))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNic, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(83, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(45)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNom)
						.addComponent(NOM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(N))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrnom)
						.addComponent(PRENOM, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(P))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLicence)
						.addComponent(LICENCE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdresse)
						.addComponent(ADRESSE, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail)
						.addComponent(EMAIL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(g))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTlphone)
						.addComponent(TEL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(d, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(3)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNic, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
						.addComponent(identity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAnnuler)
						.addComponent(btnSave)
						.addComponent(btnEdit))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
	}
}
