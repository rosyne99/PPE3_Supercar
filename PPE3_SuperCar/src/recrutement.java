import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Component;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.Vector;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class recrutement {

	private JFrame frame;
	private JTextField request;
	private JTextField nom;
	private JTextField date1;
	private JTextField date2;
	private JTextField cmt;
	private JTable table;
	private JComboBox status;
	private JComboBox state;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					recrutement window = new recrutement();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void recrutement() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					recrutement window = new recrutement();
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
	public recrutement() {
		initialize();
		table_update();	}
	
	private void table_update() {
		int c;
		try {
			Connection conn;
			PreparedStatement insert;
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/supercarjava","root","");
			insert = conn.prepareStatement("select * from todolist");
			ResultSet rs = insert.executeQuery();
			ResultSetMetaData Rss =  (ResultSetMetaData) rs.getMetaData();
			c=Rss.getColumnCount();
			
			DefaultTableModel tab = (DefaultTableModel)table.getModel();
			
			tab.setRowCount(0);
			while(rs.next()) {
				 
				Vector v2 = new Vector();
				
				for (int a =1; a<=c; a++) {
					
					v2.add(rs.getString("id"));
					v2.add(rs.getString("request_name"));
					v2.add(rs.getString("for_whom"));
					v2.add(rs.getString("received"));
					v2.add(rs.getString("deadline"));
					v2.add(rs.getString("status"));
					v2.add(rs.getString("comment"));
					v2.add(rs.getString("state"));
					
					
					
					
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
	public void close() {	
		frame.setVisible(false);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1300, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				hr  s =new hr();
				s.hr();
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "Pending", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblPourLeRecrutement = new JLabel("To Do List");
		lblPourLeRecrutement.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(29)
							.addComponent(btnGoBack, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPourLeRecrutement, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 764, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(98, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGoBack, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPourLeRecrutement))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 395, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				
				
				request.setText(tab.getValueAt(selectedIndex, 1).toString());  
				nom.setText(tab.getValueAt(selectedIndex, 2).toString());
				date1.setText(tab.getValueAt(selectedIndex, 3).toString());
				date2.setText(tab.getValueAt(selectedIndex, 4).toString());
				
				
				status.setToolTipText(tab.getValueAt(selectedIndex, 5).toString());
				cmt.setText(tab.getValueAt(selectedIndex, 6).toString());
				state.setToolTipText(tab.getValueAt(selectedIndex, 7).toString());
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(47, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 624, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Request", "For Whom", "Date Received", "Deadline", "Status", "Comment", "State"
			}
		));
		scrollPane.setViewportView(table);
		panel_1.setLayout(gl_panel_1);
		DefaultTableModel model = new DefaultTableModel();
		Object[] column = {"Nom", "Prenom", "Age", "Adresse ","Telephone", "Email", "poste"};
		final Object[]row = new Object[7];
		model.setColumnIdentifiers(column);
		
		JLabel lblNom = new JLabel("The Request");
		lblNom.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblPrnom = new JLabel("For Whom?");
		lblPrnom.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblAdresse = new JLabel("Deadline");
		lblAdresse.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblTlphone = new JLabel("Status");
		lblTlphone.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblEmail = new JLabel("comment");
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblQuelPoste = new JLabel("state");
		lblQuelPoste.setFont(new Font("Dialog", Font.BOLD, 15));
		
		JLabel lblAge = new JLabel("Date received");
		lblAge.setFont(new Font("Dialog", Font.BOLD, 15));
		
		request = new JTextField();
		request.setColumns(10);
		
		nom = new JTextField();
		nom.setColumns(10);
		
		date1 = new JTextField();
		date1.setColumns(10);
		
		date2 = new JTextField();
		date2.setColumns(10);
		
		cmt = new JTextField();
		cmt.setColumns(10);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Connection conn;
				PreparedStatement insert;
				String request_name = request.getText(); 
				String for_whom = nom.getText(); 
				String received = date1.getText(); 
				
				String deadline = date2.getText();  
				String comment = cmt.getText(); 
				String s2 = status.getSelectedItem().toString();
				
				String s1 = state.getSelectedItem().toString();
				
				
				try {
					
					
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost/supercarjava","root","");
					insert = conn.prepareStatement("insert into todolist(request_name,for_whom,received,deadline,status,comment,state)values(?,?,?,?,?,?,?)");
					insert.setString(1, request_name);
					insert.setString(2, for_whom);
					insert.setString(3, received);
					
					insert.setString(4, deadline);
					
					insert.setString(6, comment);
					insert.setString(5, s2);
					insert.setString(7, s1);
					
					insert.executeUpdate();
					JOptionPane.showMessageDialog( btnNewButton, this, "Record Added", 0);
					
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
				catch (Exception e1) {
				
					e1.printStackTrace();
				}
				
				
				table_update();
				
				}
			
			
		});
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				request.setText("");
				nom.setText("");
				date2.setText("");
				
				cmt.setText("");
				
				date1.setText("");	
				
			
			}
		});
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn;
				PreparedStatement insert;
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();	
				try {
					int id= Integer.parseInt(tab.getValueAt(selectedIndex, 0).toString());
					int dialogResult = JOptionPane.showConfirmDialog(null, "Voulez- vous vraiment effacer cette donnés","warning", JOptionPane.YES_NO_OPTION);
					if(dialogResult==JOptionPane.YES_OPTION) {
						Class.forName("com.mysql.cj.jdbc.Driver");
						conn = DriverManager.getConnection("jdbc:mysql://localhost/supercarjava","root","");
						insert = conn.prepareStatement("delete from todolist where id=? ");
					
						insert.setInt(1, id);
						insert.executeUpdate();
						JOptionPane.showMessageDialog( btnSupprimer, this, "Deleted", dialogResult);
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
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn;
				PreparedStatement insert;
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				try {
					int id= Integer.parseInt(tab.getValueAt(selectedIndex, 0).toString());
				
					String request_name = request.getText(); 
					String for_whom = nom.getText(); 
					String received = date1.getText(); 
					
					String deadline = date2.getText();  
					String comment = cmt.getText(); 
					String s2 = status.getSelectedItem().toString();
					
					String s1 = state.getSelectedItem().toString();
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost/supercarjava","root","");
					insert = conn.prepareStatement("update todolist set request_name=?, for_whom=?, received=?, deadline=?, status=?, comment=?, state=? where id=?");
			
					insert.setString(1, request_name);
					insert.setString(2, for_whom);
					insert.setString(3, received);
					insert.setString(4, deadline);
					insert.setString(5, s2);
					insert.setString(6, comment);
					
					insert.setString(7, s1);

					insert.setInt(8, id);
					insert.executeUpdate();
					JOptionPane.showMessageDialog( btnNewButton, this, "Record Modified", 0);
					
				} catch (ClassNotFoundException e1) {
					
					e1.printStackTrace();
				}
				catch (Exception e1) {
				
					e1.printStackTrace();
				}
				table_update();
				
			}
		});
		
		 state = new JComboBox();
		state.setModel(new DefaultComboBoxModel(new String[] {"State", "Received", "In Progess", "Finished"}));
		
	 status = new JComboBox();
		status.setModel(new DefaultComboBoxModel(new String[] {"Status", "Urgent", "ok"}));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblPrnom, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAdresse, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQuelPoste, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNom, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
								.addComponent(lblAge, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblTlphone, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblEmail, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(59)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(status, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(cmt, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
								.addComponent(date2, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
								.addComponent(date1, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
								.addComponent(nom, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
								.addComponent(request, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
								.addComponent(state, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(43)
									.addComponent(btnSupprimer, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))
							.addGap(31)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnModifier, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAnnuler, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(52, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNom, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(request, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrnom, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(nom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAge, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(date1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdresse, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(date2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTlphone, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(status, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQuelPoste, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(state, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAnnuler, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnModifier, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSupprimer, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
	}
}
