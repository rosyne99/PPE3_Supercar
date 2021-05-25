import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;



import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class dept {

	private JFrame frame;
	private JTextField nom;
	private JTable table;
	private JTextField adresse;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */public void close() {	
			frame.setVisible(false);
		}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dept window = new dept();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void dept() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dept window = new dept();
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
	public dept() {
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
			insert = conn.prepareStatement("select * from dept");
			ResultSet rs = insert.executeQuery();
			ResultSetMetaData Rss =  (ResultSetMetaData) rs.getMetaData();
			c=Rss.getColumnCount();
			
			DefaultTableModel tab = (DefaultTableModel)table.getModel();
			
			tab.setRowCount(0);
			while(rs.next()) {
				 
				Vector v2 = new Vector();
				
				for (int a =1; a<=c; a++) {
					
					v2.add(rs.getString("ID_DEPT"));
					v2.add(rs.getString("NOM"));
					v2.add(rs.getString("ADRESSE"));
					v2.add(rs.getString("ACTIVE"));
					
					
					
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
		frame.setBounds(100, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_1 = new JPanel();
		
		JLabel lblLesDpartements = new JLabel("Les D\u00E9partements");
		lblLesDpartements.setFont(new Font("Times New Roman", Font.BOLD, 36));
		
		JButton button = new JButton("Go Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				hr  s =new hr();
				s.hr();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(22)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addGap(101)
							.addComponent(lblLesDpartements)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblLesDpartements))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(21)
							.addComponent(button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE))
					.addGap(119))
		);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				
				
				nom.setText(tab.getValueAt(selectedIndex, 1).toString());  
				adresse.setText(tab.getValueAt(selectedIndex, 2).toString());  
				comboBox.setToolTipText(tab.getValueAt(selectedIndex, 3).toString());  
				
				
				
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID_DEPT", "NOM", "Adresse", "Active/Inactive"
			}
		));
		table.getColumnModel().getColumn(3).setPreferredWidth(84);
		table.getColumnModel().getColumn(3).setMinWidth(17);
		scrollPane.setViewportView(table);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNomDuDepartment = new JLabel("Nom du Department");
		
		nom = new JTextField();
		nom.setColumns(10);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!nom.equals("[a-zA-Z]+")) {
					JOptionPane.showMessageDialog(null, "Le champs Nom peut pas contenir des chiffres");
				}
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/supercarjava","root","");
				PreparedStatement ps = conn.prepareStatement("insert into dept(NOM,ADRESSE,ACTIVE) values(?,?,?)");
	
				ps.setString(1, nom.getText());
				ps.setString(2, adresse.getText());
				ps.setString(3, comboBox.getSelectedItem().toString());
				
				
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
				table_update();
			}
		});
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				Connection conn;
				PreparedStatement insert;
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				try {
					int ID_DEPT= Integer.parseInt(tab.getValueAt(selectedIndex, 0).toString());
				
					String NOM = nom.getText(); 
					String ADRESSE = adresse.getText(); 
					String ACTIVE = comboBox.getSelectedItem().toString();
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost/supercarjava","root","");
					insert = conn.prepareStatement("update dept set NOM=?, ADRESSE=?, ACTIVE=? where ID_DEPT=?");
			
					insert.setString(1, NOM);
					insert.setString(2, ADRESSE);
					insert.setString(3, ACTIVE);
					insert.setInt(4, ID_DEPT);
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
		
		JButton btnSuppriemer = new JButton("Supprimer");
		btnSuppriemer.addActionListener(new ActionListener() {
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
						insert = conn.prepareStatement("delete from DEPT where ID_DEPT=? ");
					
						insert.setInt(1, ID_DEPT);
						insert.executeUpdate();
						JOptionPane.showMessageDialog( btnSuppriemer, this, "Deleted", dialogResult);
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
		
		JLabel lblAdresse = new JLabel("Adresse");
		
		JLabel lblActiveinactive = new JLabel("Active/Inactive");
		
		adresse = new JTextField();
		adresse.setColumns(10);
		
	 comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"0", "1"}));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnAjouter)
					.addGap(24)
					.addComponent(btnEdit)
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addComponent(btnSuppriemer)
					.addGap(24))
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNomDuDepartment)
						.addComponent(lblAdresse))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(nom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(adresse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(73, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblActiveinactive)
					.addGap(73)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(115, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNomDuDepartment)
						.addComponent(nom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(adresse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAdresse))
					.addGap(28)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblActiveinactive))
					.addGap(31)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEdit)
						.addComponent(btnSuppriemer)
						.addComponent(btnAjouter))
					.addContainerGap(38, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
	}
}
