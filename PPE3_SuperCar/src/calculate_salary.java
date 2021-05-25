import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.itextpdf.text.DocumentException;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.pdf.PdfDate;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



import com.mysql.cj.jdbc.result.ResultSetMetaData;



import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import com.itextpdf.text.pdf.PdfWriter;
import javax.swing.border.EtchedBorder;
import java.awt.Color;


public class calculate_salary {

	private JFrame frame;
	private JTextField nom;
	private JTextField prenom;
	private JTextField poste;
	private JTextField absence;
	private JTextField heure;
	private JTextField deduction;
	private JTextField total;
	private JTextField salaire;
	private JTable table;
	private JTextField sum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					calculate_salary window = new calculate_salary();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void calculate_salary() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					calculate_salary window = new calculate_salary();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public int total(int deduction, int sum, int salaire) {
		
		return deduction + sum + salaire;
	}
	/**
	 * Create the application.
	 */
	public calculate_salary() {
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
			insert = conn.prepareStatement("select * from employe");
			ResultSet rs = insert.executeQuery();
			ResultSetMetaData Rss =  (ResultSetMetaData) rs.getMetaData();
			c=Rss.getColumnCount();
			
			DefaultTableModel tab = (DefaultTableModel)table.getModel();
			
			tab.setRowCount(0);
			while(rs.next()) {
				 
				Vector v2 = new Vector();
				
				for (int a =1; a<=c; a++) {
					
					v2.add(rs.getString("ID_EMPLOYE"));
					v2.add(rs.getString("NOM"));
					v2.add(rs.getString("PRENOM"));
					v2.add(rs.getString("ID_DEPT"));
					v2.add(rs.getString("SALAIRE"));
					v2.add(rs.getString("TEL"));
			
					v2.add(rs.getString("TYPE"));
				
					v2.add(rs.getString("EMAIL"));
				
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
	/**
	 * Initialize the contents of the frame.
	 */public void close() {	
			frame.setVisible(false);
		}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1300, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Details de l'employ\u00E9(e)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panel_1 = new JPanel();
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
				hr  s =new hr();
				s.hr();
				
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Deduction", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Augmentation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Total", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblNewLabel_1 = new JLabel("Salaire");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(24)
							.addComponent(btnGoBack, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 597, Short.MAX_VALUE)
								.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
								.addComponent(panel_2, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 640, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(31))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnGoBack, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 443, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		JButton btnTotal = new JButton("Total");
		btnTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double val1, val2, Ans, show;
					val1=Double.parseDouble(salaire.getText());
					
					 
					 val2=Double.parseDouble(deduction.getText());
					 Ans = Double.parseDouble(sum.getText());
					 show= val1-val2+Ans;
					 int y = (int)show;
					total.setText(Integer.toString(y));
					}
					catch (Exception error){
						JOptionPane.showMessageDialog(null, "Enter une valeur numerique valide et non pas des lettres");
					
						
					}	
				
			}
		});
		
		total = new JTextField();
		total.setColumns(10);
	
		JButton btnTelechargez = new JButton("Telechargez");
		btnTelechargez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String value = nom.getText();
				String value1 = prenom.getText();
				String value2 = salaire.getText();
				String value3 = deduction.getText();
				String value4 = absence.getText();
				String value5 = total.getText();
				String value6 = heure.getText();
				String value7 = sum.getText();
				String value8 = poste.getText();
				JFileChooser dialog = new JFileChooser();
				dialog.setSelectedFile(new File(value+" "+ value1+ "-Fichedepaie"+".pdf"));
				int dialogResult = dialog.showSaveDialog(null);
				if (dialogResult==JFileChooser.APPROVE_OPTION) {
					String filePath = dialog.getSelectedFile().getPath();
					
					
					try {
						Document PPE3 = new Document();
						PdfWriter  myWriter = PdfWriter.getInstance(PPE3, new FileOutputStream(filePath));
						PPE3.open();
						
						
						
						PPE3.add(new Paragraph("Fiche de pay par l'entreprise Supercar", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						
						PPE3.add(new Paragraph("*****************************************************", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						
						PPE3.add(new Paragraph("Details de l'employé", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						PPE3.add(new Paragraph("Nom:"+value+ " "+ value1+" ", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						PPE3.add(new Paragraph("Poste:"+value8+ " ", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						PPE3.add(new Paragraph("Salaire de l'employé:"+value2+" ", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						PPE3.add(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						PPE3.add(new Paragraph(""+ " ", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						PPE3.add(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						PPE3.add(new Paragraph("Deduction", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						PPE3.add(new Paragraph("Nombre d'absence:"+value4+" ", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						PPE3.add(new Paragraph("Total nombre deduire:"+value3+" ", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						PPE3.add(new Paragraph(""+ " ", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						PPE3.add(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						PPE3.add(new Paragraph("Augmentation", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						PPE3.add(new Paragraph("Nombre Heure Suplementaire:"+value6+" ", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						PPE3.add(new Paragraph("Somme gagner:"+value7+" ", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						PPE3.add(new Paragraph(""+ " ", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						PPE3.add(new Paragraph("", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						PPE3.add(new Paragraph("Total pour ce mois:"+value5+" ", FontFactory.getFont(FontFactory.TIMES_BOLD,20)));
						
						PPE3.close();
						JOptionPane.showMessageDialog(null, "C'est téléchargé");
					} catch (FileNotFoundException | DocumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
					catch(Exception er) {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}
				
				
					
				
				

			}
		});
		
		btnTelechargez.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(btnTelechargez, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAnnuler, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 102, Short.MAX_VALUE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnTotal, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addComponent(total, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(total, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTotal))
					.addContainerGap(36, Short.MAX_VALUE))
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnTelechargez, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAnnuler, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_4.setLayout(gl_panel_4);
		
		JLabel lblNombredesHeures = new JLabel("Nombre ( heures supplementaire )");
		lblNombredesHeures.setFont(new Font("Dialog", Font.BOLD, 22));
		
		heure = new JTextField();
		heure.setColumns(10);
		
		JButton button_4 = new JButton("28");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double val1, val2, Ans, show, div;
					val1=Double.parseDouble(salaire.getText());
					
					 Ans= val1/28;
					 div= Ans/8;
					 val2=Double.parseDouble(heure.getText());
					 if (val2==0) {
						 sum.setText("0");
					 }
					 else {
					 show= div*val2;
					 int y = (int)show;
					sum.setText(Integer.toString(y));
					}
				}
					catch (Exception error){
						JOptionPane.showMessageDialog(null, "Enter une valeur numerique valide et non pas des lettres");
					
						
					}
				
			}
		});
		
		JButton button_5 = new JButton("29");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double val1, val2, Ans, show, div;
					val1=Double.parseDouble(salaire.getText());
					
					 Ans= val1/29;
					 div= Ans/8;
					 val2=Double.parseDouble(heure.getText());
					 if (val2==0) {
						 sum.setText("0");
					 }
					 else {
					 show= div*val2;
					 int y = (int)show;
					sum.setText(Integer.toString(y));
					}
					}
					catch (Exception error){
						JOptionPane.showMessageDialog(null, "Enter une valeur numerique valide et non pas des lettres");
					
						
					}
				
			}
		});
		
		JButton button_6 = new JButton("30");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double val1, val2, Ans, show, div;
					val1=Double.parseDouble(salaire.getText());
					
					 Ans= val1/30;
					 div= Ans/8;
					 val2=Double.parseDouble(heure.getText());
					 if (val2==0) {
						 sum.setText("0");
					 }
					 else {
					 show= div*val2;
					 int y = (int)show;
					sum.setText(Integer.toString(y));
					}
					}
					catch (Exception error){
						JOptionPane.showMessageDialog(null, "Enter une valeur numerique valide et non pas des lettres");
					
						
					}
				
			}
		});
		
		JButton button_7 = new JButton("31");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double val1, val2, Ans, show, div;
					val1=Double.parseDouble(salaire.getText());
					
					 Ans= val1/31;
					 div= Ans/8;
					 val2=Double.parseDouble(heure.getText());
					 if (val2==0) {
						 sum.setText("0");
					 }
					 else {
					 show= div*val2;
					 int y = (int)show;
					sum.setText(Integer.toString(y));
					}
					}
					catch (Exception error){
						JOptionPane.showMessageDialog(null, "Enter une valeur numerique valide et non pas des lettres");
					
						
					}
				
			}
				
			
		});
		
		JButton button_3 = new JButton("Total");
		
		sum = new JTextField();
		sum.setColumns(10);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblNombredesHeures, GroupLayout.PREFERRED_SIZE, 364, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(heure, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(button_4, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(button_5, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(button_6, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_7, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(button_3, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
							.addComponent(sum, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombredesHeures)
						.addComponent(heure, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_3.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_4)
								.addComponent(button_5)
								.addComponent(button_6)
								.addComponent(button_7))
							.addContainerGap(20, Short.MAX_VALUE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_3)
								.addComponent(sum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
		);
		panel_3.setLayout(gl_panel_3);
		
		JLabel lblSalaireTotal = new JLabel("salaire de l'employ\u00E9(e)");
		lblSalaireTotal.setFont(new Font("Dialog", Font.BOLD, 22));
		
		salaire = new JTextField();
		salaire.setColumns(10);
		
		JLabel lblNombreDabsence = new JLabel("Nombre d'absence");
		lblNombreDabsence.setFont(new Font("Dialog", Font.BOLD, 22));
		
		absence = new JTextField();
		absence.setColumns(10);
		
		deduction = new JTextField();
		deduction.setColumns(10);
		
		JLabel lblDeduction = new JLabel("Deduction");
		lblDeduction.setFont(new Font("Dialog", Font.BOLD, 22));
		
		JButton btnNewButton = new JButton("28");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double val1, val2, Ans, show;
					val1=Double.parseDouble(salaire.getText());
					
					 Ans= val1/28;
					 val2=Double.parseDouble(absence.getText());
					 if (val2==0) {
						 deduction.setText("0");
					 }
					 else {
					 show= Ans/val2;
					 int y = (int)show;
					deduction.setText(Integer.toString(y));
					}
			}
					catch (Exception error){
						JOptionPane.showMessageDialog(null, "Enter une valeur numerique valide et non pas des lettres");
					
						
					}
				}
				
			
		});
		
		JButton button = new JButton("29");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double val1, val2, Ans, show;
					val1=Double.parseDouble(salaire.getText());
					
					 Ans= val1/29;
					 val2=Double.parseDouble(absence.getText());
					 if (val2==0) {
						 deduction.setText("0");
					 }
					 else {
					 show= Ans*val2;
					 int y = (int)show;
					deduction.setText(Integer.toString(y));
					}
			}
					catch (Exception error){
						JOptionPane.showMessageDialog(null, "Enter une valeur numerique valide et non pas des lettres");
					
						
					}
				
			}
		});
		
		JButton button_1 = new JButton("30");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double val1, val2, Ans, show;
					val1=Double.parseDouble(salaire.getText());
					
					 Ans= val1/30;
					 val2=Double.parseDouble(absence.getText());
					 if (val2==0) {
						 deduction.setText("0");
					 }
					 else {
					 show= Ans*val2;
					 int y = (int)show;
					deduction.setText(Integer.toString(y));
					}
			}
					catch (Exception error){
						JOptionPane.showMessageDialog(null, "Enter une valeur numerique valide et non pas des lettres");
					
						
					}
				
			}
		});
		
		JButton button_2 = new JButton("31");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double val1, val2, Ans, show;
					val1=Double.parseDouble(salaire.getText());
					
					 Ans= val1/31;
					 val2=Double.parseDouble(absence.getText());
					 if (val2==0) {
						 deduction.setText("0");
					 }
					 else {
					 show= Ans*val2;
					 int y = (int)show;
					deduction.setText(Integer.toString(y));
					 }
					}
					catch (Exception error){
						JOptionPane.showMessageDialog(null, "Enter une valeur numerique valide et non pas des lettres");
					
						
					}
				
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblSalaireTotal, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNombreDabsence, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_2.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDeduction)))
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(deduction, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_panel_2.createSequentialGroup()
								.addComponent(absence, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_panel_2.createSequentialGroup()
								.addComponent(salaire, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
								.addGap(43))))
					.addGap(43))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(16)
					.addComponent(btnNewButton)
					.addGap(10)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(322))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(salaire, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSalaireTotal, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNombreDabsence)
						.addComponent(absence, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(deduction, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addComponent(lblDeduction, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton)
								.addComponent(button)
								.addComponent(button_1)
								.addComponent(button_2))))
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel tab = (DefaultTableModel)table.getModel();
				int selectedIndex = table.getSelectedRow();
				
				nom.setText(tab.getValueAt(selectedIndex, 1).toString()); 
				
				prenom.setText(tab.getValueAt(selectedIndex, 2).toString());  
				
				 
				
				salaire.setText(tab.getValueAt(selectedIndex, 8).toString());  
				
				
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(15)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nom", "Prenom", "Dept", "salaire", "Telephone", "Type", "E-mail", "t"
			}
		));
		table.getColumnModel().getColumn(8).setPreferredWidth(0);
		table.getColumnModel().getColumn(8).setMinWidth(0);
		table.getColumnModel().getColumn(8).setMaxWidth(0);
		scrollPane.setViewportView(table);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 22));
		
		JLabel lblPrenom = new JLabel("Prenom");
		lblPrenom.setFont(new Font("Dialog", Font.BOLD, 22));
		
		JLabel lblType = new JLabel("Poste");
		lblType.setFont(new Font("Dialog", Font.BOLD, 22));
		
		nom = new JTextField();
		nom.setColumns(10);
		
		prenom = new JTextField();
		prenom.setColumns(10);
		
		poste = new JTextField();
		poste.setColumns(10);
		
		JLabel label = new JLabel("");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPrenom, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(prenom, 198, 198, Short.MAX_VALUE)
								.addComponent(poste, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
								.addComponent(nom))
							.addGap(97))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label)
							.addContainerGap(569, Short.MAX_VALUE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
						.addComponent(nom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblPrenom, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblType, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(prenom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(poste, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		frame.getContentPane().setLayout(groupLayout);
	}
}
