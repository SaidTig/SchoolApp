import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.Blob;
import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javax.swing.JSplitPane;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class accueilFrame extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JTextField textNom;
	private JTextField textPrenom;
	private JTextField textBirthday;
	private JTextField textEcole;
	private JTextField textNiveau;
	private JTextField textClasse;
	private JTextField textTel;
	private JTextField textAdresse;
	private JTextField textGroupe;
	private JTextField textDateInsc;
	private String Dernier_paiement;
	private JTextField textNom_Del;
	private JTextField textIdentifiant_Del;
	private JTextField textSearch;
	private File image;
	private FileInputStream input;
	private static JScrollPane scrollPane;
	private static DefaultTableModel model;
	
	private  String get_ID;
	private  String get_Nom;
	private  String get_Prenom;
	private  String get_Brithday;
	private  String get_Ecole;
	private  String get_Niveau;
	private  String get_Classe;
	private  String get_Tel;
	private  String get_Adresse;
	private  String get_Groupe;
	private  String get_DateInsc;
	private  String get_Dernier;
	private  String get_Absences;
	
	
	
	final static DateTimeFormatter OLD_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	final static DateTimeFormatter NEW_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e2){
			
		}*/
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					accueilFrame frame = new accueilFrame();
					//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					//frame.setPreferredSize(new Dimension(500, 400));
					//frame.setResizable(false);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }
	
	public static void refreshTable () {
		DefaultTableModel mod = model;
		JTable tab = table;
		JScrollPane scrl = scrollPane;
		String query = "SELECT * FROM `AppStudents`";
		try {
			PreparedStatement sta = dbConnection.getConnection().prepareStatement(query);
			ResultSet res = sta.executeQuery();
			ResultSetMetaData rsmd = (ResultSetMetaData) res.getMetaData();
			int cols = rsmd.getColumnCount();
			Object [] colName = new Object[cols];
			for (int i=0; i<cols; i++)
				colName[i] = rsmd.getColumnName(i+1);
			mod.setColumnIdentifiers(colName);
			mod.setRowCount(0);
			String[] data = new String[cols];
			while (res.next()) {
				for (int i=0; i<cols; i++) {
					data[i] = res.getString(i+1);
					if(i==3 || i==10) {
						LocalDate date = LocalDate.parse(data[i], OLD_FORMATTER);
						String newString = date.format(NEW_FORMATTER);
						data[i] = newString;
					}
				}
				mod.addRow(data);
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		scrl.setViewportView(tab);
	}
	
	/**
	 * Create the frame.
	 */
	public accueilFrame() {
		setTitle("School App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		setBounds(0, 0, 1382, 830);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(143, 0, 1382, 830);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		
		//--------- Menu ------
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(143, 0, 1382, 830);
		layeredPane.add(panel_1, "name_9208865217644");
		
		JButton btnEleves = new JButton("Elèves");
		btnEleves.setBounds(0, 0, 141, 25);
		contentPane.add(btnEleves);
		btnEleves.setBackground(Color.GREEN);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		layeredPane.add(panel_2, "name_9217171264292");
		panel_2.setLayout(null);
		
		JLabel lblCre = new JLabel("Créer un groupe");
		lblCre.setFont(new Font("Dyuthi", Font.BOLD, 20));
		lblCre.setForeground(Color.RED);
		lblCre.setBounds(54, 32, 212, 31);
		panel_2.add(lblCre);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBounds(27, 75, 146, 230);
		panel_2.add(panel_5);
		panel_5.setLayout(null);
		
		JLabel lblNomDuGroupe = new JLabel("Nom du groupe :");
		lblNomDuGroupe.setBounds(0, 23, 146, 15);
		lblNomDuGroupe.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_5.add(lblNomDuGroupe);
		
		JLabel lblNomDuGroupe_1 = new JLabel("Nom du groupe :");
		lblNomDuGroupe_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomDuGroupe_1.setBounds(0, 37, 146, 15);
		panel_5.add(lblNomDuGroupe_1);
		
		JLabel lblNomDuGroupe_1_1 = new JLabel("Nom du groupe :");
		lblNomDuGroupe_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomDuGroupe_1_1.setBounds(0, 0, 146, 15);
		panel_5.add(lblNomDuGroupe_1_1);
		
		JLabel lblNomDuGroupe_1_2 = new JLabel("Nom du groupe :");
		lblNomDuGroupe_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomDuGroupe_1_2.setBounds(0, 0, 146, 15);
		panel_5.add(lblNomDuGroupe_1_2);
		
		JLabel lblNomDuGroupe_1_3 = new JLabel("Nom du groupe :");
		lblNomDuGroupe_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomDuGroupe_1_3.setBounds(0, 0, 146, 15);
		panel_5.add(lblNomDuGroupe_1_3);
		
		JLabel lblNomDuGroupe_1_4 = new JLabel("Nom du groupe :");
		lblNomDuGroupe_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomDuGroupe_1_4.setBounds(0, 0, 146, 15);
		panel_5.add(lblNomDuGroupe_1_4);
		
		JButton btnClasses = new JButton("Groupes");
		btnClasses.setBounds(0, 26, 141, 25);
		contentPane.add(btnClasses);
		btnClasses.setBackground(Color.WHITE);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		layeredPane.add(panel_3, "name_9208865217644");
		panel_3.setLayout(null);
		
		JButton btnCompta = new JButton("Comptabilité");
		btnCompta.setBounds(0, 51, 141, 25);
		contentPane.add(btnCompta);
		btnCompta.setBackground(Color.WHITE);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		layeredPane.add(panel_4, "name_64602567828198");
		panel_4.setLayout(null);
		
		JButton btnSeance = new JButton("Séance");
		btnSeance.setBackground(Color.WHITE);
		btnSeance.setBounds(0, 76, 141, 25);
		contentPane.add(btnSeance);
		
		btnEleves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEleves.setBackground(Color.GREEN);
				btnClasses.setBackground(Color.WHITE);
				btnCompta.setBackground(Color.WHITE);
				btnSeance.setBackground(Color.WHITE);
				layeredPane.removeAll();
				layeredPane.add(panel_1);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});

		btnClasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEleves.setBackground(Color.WHITE);
				btnClasses.setBackground(Color.GREEN);
				btnCompta.setBackground(Color.WHITE);
				btnSeance.setBackground(Color.WHITE);
				layeredPane.removeAll();
				layeredPane.add(panel_2);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		
		btnCompta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEleves.setBackground(Color.WHITE);
				btnClasses.setBackground(Color.WHITE);
				btnCompta.setBackground(Color.GREEN);
				btnSeance.setBackground(Color.WHITE);
				layeredPane.removeAll();
				layeredPane.add(panel_3);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		btnSeance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEleves.setBackground(Color.WHITE);
				btnClasses.setBackground(Color.WHITE);
				btnCompta.setBackground(Color.WHITE);
				btnSeance.setBackground(Color.GREEN);
				layeredPane.removeAll();
				layeredPane.add(panel_4);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		
		//--------- Eleves ----
		
		table = new JTable();
		table.setFocusable(true);
		table.setRowSelectionAllowed(true);
		//table.setBounds(143, 0, 1382, 830);
		model = (DefaultTableModel) table.getModel();
		PreparedStatement sta;
		String query = "SELECT * FROM `AppStudents`";
		try {
			sta = dbConnection.getConnection().prepareStatement(query);
			ResultSet res = sta.executeQuery();
			ResultSetMetaData rsmd = (ResultSetMetaData) res.getMetaData();
			int cols = rsmd.getColumnCount();
			Object [] colName = new Object[cols];
			for (int i=0; i<cols; i++)
				colName[i] = rsmd.getColumnName(i+1);
			model.setColumnIdentifiers(colName);
			model.setRowCount(0);
			String[] data = new String[cols];
			while (res.next()) {
				for (int i=0; i<cols; i++) {
					data[i] = res.getString(i+1);
					if(i==3 || i==10) {
						LocalDate date = LocalDate.parse(data[i], OLD_FORMATTER);
						String newString = date.format(NEW_FORMATTER);
						data[i] = newString;
					}
				}
				model.addRow(data);
				}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		panel_1.setLayout(null);
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(143, 383, 1200, 418);
		panel_1.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		//scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(table);
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = table .getSelectedRow();
				get_ID =  model.getValueAt(index,0).toString();
				get_Nom = model.getValueAt(index,1).toString();
				get_Prenom = model.getValueAt(index,2).toString();
				get_Brithday = model.getValueAt(index,3).toString();
				get_Ecole = model.getValueAt(index,4).toString();
				get_Niveau = model.getValueAt(index,5).toString();
				get_Classe = model.getValueAt(index,6).toString();
				get_Tel = model.getValueAt(index,7).toString();
				get_Adresse = model.getValueAt(index,8).toString();
				get_Groupe = model.getValueAt(index,9).toString();
				get_DateInsc = model.getValueAt(index,10).toString();
				get_Dernier = model.getValueAt(index,11).toString();
				get_Absences = model.getValueAt(index,12).toString();
				
				PreparedStatement staPh;
				String queryPh = "SELECT `Photo` FROM `AppStudents` WHERE `Identifiant` =?";
				try {
					staPh = dbConnection.getConnection().prepareStatement(queryPh);
					staPh.setString(1, get_ID);
					ResultSet resPh = staPh.executeQuery();
					byte[] im = null;
				    while (resPh.next())
				    	im = resPh.getBytes("Photo");
				    Image img = Toolkit.getDefaultToolkit().createImage(im);
				    ImageIcon icone = new ImageIcon(img);
				    detailsEleve elv = new detailsEleve(get_ID, get_Nom, get_Prenom, get_Brithday, get_Ecole, get_Niveau, get_Classe, get_Tel, get_Adresse, get_Groupe, get_DateInsc, get_Dernier, get_Absences, icone);
				    elv.setPreferredSize(new Dimension(550, 400));
				    elv.pack();
				    elv.setLocationRelativeTo(null);
				    elv.setVisible(true);
				    elv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//JOptionPane.showMessageDialog(null,get_ID);
			}
		});
		
		JLabel lblNouvellve = new JLabel("Ajouter un nouvel élève");
		lblNouvellve.setForeground(Color.RED);
		lblNouvellve.setBackground(Color.BLACK);
		lblNouvellve.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNouvellve.setBounds(44, 48, 265, 15);
		panel_1.add(lblNouvellve);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom.setBounds(123, 103, 70, 15);
		panel_1.add(lblNom);
		
		JLabel lblPrnom = new JLabel("Prénom");
		lblPrnom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrnom.setBounds(123, 130, 70, 15);
		panel_1.add(lblPrnom);
		
		JLabel lblNom_1_1 = new JLabel("Date de naissance");
		lblNom_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom_1_1.setBounds(44, 156, 149, 15);
		panel_1.add(lblNom_1_1);
		
		JLabel lblNom_1_1_1 = new JLabel("Ecole");
		lblNom_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom_1_1_1.setBounds(123, 184, 70, 15);
		panel_1.add(lblNom_1_1_1);
		
		JLabel lblNom_1_1_2 = new JLabel("Niveau");
		lblNom_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom_1_1_2.setBounds(123, 211, 70, 15);
		panel_1.add(lblNom_1_1_2);
		
		JLabel lblNom_1_1_3 = new JLabel("Classe");
		lblNom_1_1_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom_1_1_3.setBounds(123, 239, 70, 15);
		panel_1.add(lblNom_1_1_3);
		
		JLabel lblNom_1_1_4 = new JLabel("Téléphone");
		lblNom_1_1_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom_1_1_4.setBounds(71, 266, 122, 15);
		panel_1.add(lblNom_1_1_4);
		
		JLabel lblNom_1_1_5 = new JLabel("Adresse");
		lblNom_1_1_5.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom_1_1_5.setBounds(22, 293, 171, 15);
		panel_1.add(lblNom_1_1_5);
		
		JLabel lblNom_1_1_6 = new JLabel("Groupe");
		lblNom_1_1_6.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom_1_1_6.setBounds(579, 103, 70, 15);
		panel_1.add(lblNom_1_1_6);
		
		JLabel lblNom_1_1_7 = new JLabel("Date d'inscription");
		lblNom_1_1_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom_1_1_7.setBounds(500, 129, 149, 15);
		panel_1.add(lblNom_1_1_7);
		
		JLabel lblNom_1_1_8 = new JLabel("Dernier paiement");
		lblNom_1_1_8.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom_1_1_8.setBounds(516, 156, 133, 15);
		panel_1.add(lblNom_1_1_8);
		
		JLabel lblNom_1_1_9 = new JLabel("Photo");
		lblNom_1_1_9.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom_1_1_9.setBounds(579, 184, 70, 15);
		panel_1.add(lblNom_1_1_9);
		
		JLabel lblSupprimerUnlve = new JLabel("Supprimer un élève");
		lblSupprimerUnlve.setForeground(Color.RED);
		lblSupprimerUnlve.setFont(new Font("Dialog", Font.BOLD, 18));
		lblSupprimerUnlve.setBackground(Color.BLACK);
		lblSupprimerUnlve.setBounds(961, 48, 207, 15);
		panel_1.add(lblSupprimerUnlve);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(913, 76, 19, 273);
		panel_1.add(separator);
		
		textNom = new JTextField();
		textNom.setBounds(227, 103, 186, 19);
		panel_1.add(textNom);
		textNom.setColumns(10);
		
		textPrenom = new JTextField();
		textPrenom.setColumns(10);
		textPrenom.setBounds(227, 130, 186, 19);
		panel_1.add(textPrenom);
		
		//textBirthday = new JTextField();
		//textBirthday.setColumns(10);
		//textBirthday.setBounds(227, 156, 186, 19);
		//panel_1.add(textBirthday);
		
        CustomTextField textBirthday = new CustomTextField(20);
        textBirthday.setPlaceholder("jj-mm-aaaa");
        textBirthday.setBounds(227, 156, 186, 19);
      	panel_1.add(textBirthday);
        
		textEcole = new JTextField();
		textEcole.setColumns(10);
		textEcole.setBounds(227, 184, 186, 19);
		panel_1.add(textEcole);
		
		textNiveau = new JTextField();
		textNiveau.setColumns(10);
		textNiveau.setBounds(227, 211, 186, 19);
		panel_1.add(textNiveau);
		
		textClasse = new JTextField();
		textClasse.setColumns(10);
		textClasse.setBounds(227, 239, 186, 19);
		panel_1.add(textClasse);
		
		textTel = new JTextField();
		textTel.setColumns(10);
		textTel.setBounds(227, 266, 186, 19);
		panel_1.add(textTel);
		
		textAdresse = new JTextField();
		textAdresse.setColumns(10);
		textAdresse.setBounds(227, 293, 186, 19);
		panel_1.add(textAdresse);
		
		textGroupe = new JTextField();
		textGroupe.setColumns(10);
		textGroupe.setBounds(687, 103, 186, 19);
		panel_1.add(textGroupe);
		
		CustomTextField textDateInsc = new CustomTextField(20);
		textDateInsc.setPlaceholder("jj-mm-aaaa");
		textDateInsc.setBounds(687, 129, 186, 19);
      	panel_1.add(textDateInsc);
		
		JComboBox txtDernierPaiement = new JComboBox();
		txtDernierPaiement.setModel(new DefaultComboBoxModel(new String[] {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"}));
		txtDernierPaiement.setMaximumRowCount(12);
		txtDernierPaiement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dernier_paiement = txtDernierPaiement.getSelectedItem().toString();
			}
		});
		txtDernierPaiement.setSelectedIndex(1);
		txtDernierPaiement.setBounds(690, 156, 149, 19);
		panel_1.add(txtDernierPaiement);
		
		JButton btnParcourir = new JButton("Parcourir");
		
		btnParcourir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser browse = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE FILES", "jpg", "jpeg","png");
				browse.setFileFilter(filter);
				int response = browse.showOpenDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					image = new File(browse.getSelectedFile().getAbsolutePath());
					//System.out.println(image.toString());
					try {
						input = new FileInputStream(image);
						System.out.println(input);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnParcourir.setFont(new Font("Dialog", Font.BOLD, 8));
		btnParcourir.setHorizontalAlignment(SwingConstants.LEFT);
		btnParcourir.setBounds(688, 324, 81, 15);
		panel_1.add(btnParcourir);
		
		JPanel panel = new JPanel();
		panel.setBounds(687, 184, 149, 135);
		panel_1.add(panel);
		
		JLabel lblImage = new JLabel();
		lblImage.setBounds(0, 0, 149, 135);
		panel.add(lblImage);
		panel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 8));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblImage.setIcon(new ImageIcon((new ImageIcon(image.toString()).getImage()).getScaledInstance(lblImage.getWidth(),lblImage.getHeight(),Image.SCALE_SMOOTH)));
			}
		});
		btnNewButton_1.setBounds(787, 324, 47, 15);
		panel_1.add(btnNewButton_1);
		
		
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random rnd = new Random();
				boolean exists = true;
				String ID = "";
				while (exists == true) {
					ID = String.format("%06d", rnd.nextInt(999999));
					PreparedStatement s;
					ResultSet c;
					String q = "SELECT * FROM `AppStudents` WHERE `Identifiant` =?";
					try {
						s = dbConnection.getConnection().prepareStatement(q);
						s.setString(1, ID);
						c = s.executeQuery();
						exists = c.next();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				String nom = textNom.getText();
				String prenom = textPrenom.getText();
				String birthday = textBirthday.getText();
				String ecole = textEcole.getText();
				String niveau = textNiveau.getText();
				String classe = textClasse.getText();
				String tel = textTel.getText();
				String Adresse = textAdresse.getText();
				String groupe = textGroupe.getText();
				String dateInsc = textDateInsc.getText();
				String dernierPaiement = Dernier_paiement;
				InputStream in = input;
				
				
				 
				 if (nom.equals("") || prenom.equals("") || birthday.equals("") || ecole.equals("") || niveau.equals("") || classe.equals("") || tel.equals("") || Adresse.equals("") || groupe.equals("") || dateInsc.equals("") || dernierPaiement.equals("")) {
					 JOptionPane.showMessageDialog(null,"Un champ vide est détecté");
				 }
				 else if(!isValidFormat("dd-MM-yyyy", birthday)) {
					 JOptionPane.showMessageDialog(null,"Veuilez introduire une date de naissance valide");
				 }
				 else if(!isValidFormat("dd-MM-yyyy", dateInsc)) {
				 JOptionPane.showMessageDialog(null,"Veuilez introduire une date d'inscription valide");
				 }
				 else {
					 
				 PreparedStatement sta;
				 
				 String query = "INSERT INTO `AppStudents` (`Identifiant`,`Nom`,`Prénom`,`Date de naissance`,`Ecole`,`Niveau`,`Classe`,`Téléphone`,`Adresse`,`Groupe`,`Date d'inscription`,`Dernier paiement`,`Photo`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
				 try {
					sta = dbConnection.getConnection().prepareStatement(query);
					sta.setString(1, ID);
					sta.setString(2, nom);
					sta.setString(3, prenom);
					birthday = LocalDate.parse(birthday, NEW_FORMATTER).format(OLD_FORMATTER);
					sta.setString(4, birthday);
					sta.setString(5, ecole);
					sta.setString(6, niveau);
					sta.setString(7, classe);
					sta.setString(8, tel);
					sta.setString(9, Adresse);
					sta.setString(10, groupe);
					dateInsc = LocalDate.parse(dateInsc, NEW_FORMATTER).format(OLD_FORMATTER);
					sta.setString(11, dateInsc);
					sta.setString(12, dernierPaiement);
					sta.setBinaryStream(13,in,(int)image.length());
					if (sta.executeUpdate()>0) {
						JOptionPane.showMessageDialog(null,String.format("Elève ajouté !\n ID : %s",ID));
						model.fireTableDataChanged();
						
						query = "SELECT * FROM `AppStudents`";
						try {
							sta = dbConnection.getConnection().prepareStatement(query);
							ResultSet res = sta.executeQuery();
							ResultSetMetaData rsmd = (ResultSetMetaData) res.getMetaData();
							int cols = rsmd.getColumnCount();
							Object [] colName = new Object[cols];
							for (int i=0; i<cols; i++)
								colName[i] = rsmd.getColumnName(i+1);
							model.setColumnIdentifiers(colName);
							model.setRowCount(0);
							String[] data = new String[cols];
							while (res.next()) {
								for (int i=0; i<cols; i++) {
									data[i] = res.getString(i+1);
									if(i==3 || i==10) {
										LocalDate date = LocalDate.parse(data[i], OLD_FORMATTER);
										String newString = date.format(NEW_FORMATTER);
										data[i] = newString;
									}
								}
								model.addRow(data);
								}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						scrollPane.setViewportView(table);
						
						textNom.setText("");
						textPrenom.setText("");
						textBirthday.setText("");
						textEcole.setText("");
						textNiveau.setText("");
						textClasse.setText("");
						textTel.setText("");
						textAdresse.setText("");
						textGroupe.setText("");
						textDateInsc.setText("");
						lblImage.setIcon(null);
						
					}
				 } catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 }
				 
			}
			
		});
		btnAjouter.setBounds(468, 329, 117, 25);
		panel_1.add(btnAjouter);
		
		JLabel lblNom_2 = new JLabel("Nom");
		lblNom_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom_2.setBounds(944, 153, 70, 15);
		panel_1.add(lblNom_2);
		
		textNom_Del = new JTextField();
		textNom_Del.setColumns(10);
		textNom_Del.setBounds(1048, 153, 186, 19);
		panel_1.add(textNom_Del);
		
		JLabel lblPrnom_2 = new JLabel("Prénom");
		lblPrnom_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrnom_2.setBounds(944, 180, 70, 15);
		panel_1.add(lblPrnom_2);
		
		JTextField textPrenom_Del = new JTextField();
		textPrenom_Del.setColumns(10);
		textPrenom_Del.setBounds(1048, 180, 186, 19);
		panel_1.add(textPrenom_Del);
		
		JLabel lblNom_2_1 = new JLabel("Identifiant");
		lblNom_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom_2_1.setBounds(891, 126, 123, 15);
		panel_1.add(lblNom_2_1);
		
		textIdentifiant_Del = new JTextField();
		textIdentifiant_Del.setColumns(10);
		textIdentifiant_Del.setBounds(1048, 126, 186, 19);
		panel_1.add(textIdentifiant_Del);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String IDDel = textIdentifiant_Del.getText();
				String nomDel = textNom_Del.getText();
				String prenomDel = textPrenom_Del.getText();
				
				if (!nomDel.equals("") && !prenomDel.equals("") && !IDDel.equals("")){
					 PreparedStatement sta3;
					 
					 String query3 = "DELETE FROM `AppStudents` WHERE `Identifiant` =?  AND `Nom` =? AND `Prénom`=?";
					 try {
						sta3 = dbConnection.getConnection().prepareStatement(query3);
						sta3.setString(1, IDDel);
						sta3.setString(2, nomDel);
						sta3.setString(3, prenomDel);
						
						if (sta3.executeUpdate()>0) {
							JOptionPane.showMessageDialog(null,String.format("Elève Supprimé !\n ID : %s",IDDel));
							model.fireTableDataChanged();
							
							query3 = "SELECT * FROM `AppStudents`";
							try {
								sta3 = dbConnection.getConnection().prepareStatement(query3);
								ResultSet res = sta3.executeQuery();
								ResultSetMetaData rsmd = (ResultSetMetaData) res.getMetaData();
								int cols = rsmd.getColumnCount();
								Object [] colName = new Object[cols];
								for (int i=0; i<cols; i++)
									colName[i] = rsmd.getColumnName(i+1);
								model.setColumnIdentifiers(colName);
								model.setRowCount(0);
								String[] data = new String[cols];
								while (res.next()) {
									for (int i=0; i<cols; i++) {
										data[i] = res.getString(i+1);
										if(i==3 || i==10) {
											LocalDate date = LocalDate.parse(data[i], OLD_FORMATTER);
											String newString = date.format(NEW_FORMATTER);
											data[i] = newString;
										}
									}
									model.addRow(data);
									}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							scrollPane.setViewportView(table);
							
							textIdentifiant_Del.setText("");
							textNom_Del.setText("");
							textPrenom_Del.setText("");
						}
					 } catch (SQLException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
					 }
			}
		});
		btnSupprimer.setBounds(1069, 261, 117, 25);
		panel_1.add(btnSupprimer);
		
		JButton btnNewButton = new JButton("Modifier");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PreparedStatement staPh;
				String queryPh = "SELECT `Photo` FROM `AppStudents` WHERE `Identifiant` =?";
				try {
					staPh = dbConnection.getConnection().prepareStatement(queryPh);
					staPh.setString(1, get_ID);
					ResultSet resPh = staPh.executeQuery();
					byte[] im = null;
				    while (resPh.next())
				    	im = resPh.getBytes("Photo");
				    Image img = Toolkit.getDefaultToolkit().createImage(im);
				    ImageIcon icone = new ImageIcon(img);
				    modifierEleve elv = new modifierEleve(get_ID, get_Nom, get_Prenom, get_Brithday, get_Ecole, get_Niveau, get_Classe, get_Tel, get_Adresse, get_Groupe, get_DateInsc, get_Dernier, get_Absences, icone, im);
				    elv.setPreferredSize(new Dimension(700, 400));
				    elv.pack();
				    elv.setLocationRelativeTo(null);
				    elv.setVisible(true);
				    elv.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//JOptionPane.showMessageDialog(null,get_ID);
			}
		});
		btnNewButton.setBounds(15, 383, 117, 25);
		panel_1.add(btnNewButton);
		
		JLabel lblChercher = new JLabel("Chercher");
		lblChercher.setHorizontalAlignment(SwingConstants.RIGHT);
		lblChercher.setBounds(30, 475, 70, 15);
		panel_1.add(lblChercher);
		
		textSearch = RowFilterUtil.createRowFilter(table);
		textSearch.setBounds(15, 500, 117, 25);
		panel_1.add(textSearch);
		scrollPane.setViewportView(table);
		
		
		
		//--------- Classes ----
		
		
	}
}
