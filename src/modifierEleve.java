import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

public class modifierEleve extends JFrame {

	private JPanel contentPane;
	private JTextField lblBirthday_1;
	private JTextField lblEcole_1;
	private JTextField lblNiveau_1;
	private JTextField lblClasse_1;
	private JTextField lblTel_1;
	private JTextField lblAdresse_1;
	private JTextField lblGroupe_1;
	private JTextField lblDateInsc_1;
	private String lblDernier_1;
	private JTextField lblAbs_1;

	private File image;
	private FileInputStream input;
	
	final DateTimeFormatter OLD_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	final DateTimeFormatter NEW_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
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
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					detailsEleve frame = new detailsEleve();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public modifierEleve(String get_ID,String get_Nom, String get_Prenom, String get_Brithday, String get_Ecole, String get_Niveau, String get_Classe, String get_Tel, String get_Adresse, String get_Groupe, String get_DateInsc, String get_Dernier, String get_Absences, @Nullable ImageIcon ic, byte[] im ) {
		setTitle(get_Nom + " " + get_Prenom);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Nom = new JLabel(get_Nom);
		Nom.setHorizontalAlignment(SwingConstants.CENTER);
		Nom.setFont(new Font("Dialog", Font.BOLD, 18));
		Nom.setBounds(150, 10, 250, 15);
		contentPane.add(Nom);
		
		JLabel Prenom = new JLabel(get_Prenom);
		Prenom.setHorizontalAlignment(SwingConstants.CENTER);
		Prenom.setFont(new Font("Dialog", Font.BOLD, 18));
		Prenom.setBounds(150, 39, 250, 15);
		contentPane.add(Prenom);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(12, 100, 180, 220);
		contentPane.add(panel2);
		panel2.setLayout(null);
		
		JLabel lblID = new JLabel("ID :");
		lblID.setHorizontalAlignment(SwingConstants.RIGHT);
		lblID.setBounds(0, 0, 180, 15);
		panel2.add(lblID);
		
		JLabel lblBirthday = new JLabel("Date de naissance :");
		lblBirthday.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBirthday.setBounds(0, 20, 180, 15);
		panel2.add(lblBirthday);
		
		JLabel lblEcole = new JLabel("Ecole :");
		lblEcole.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEcole.setBounds(0, 40, 180, 15);
		panel2.add(lblEcole);
		
		JLabel lblNiveau = new JLabel("Niveau :");
		lblNiveau.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNiveau.setBounds(0, 60, 180, 15);
		panel2.add(lblNiveau);
		
		JLabel lblClasse = new JLabel("Classe :");
		lblClasse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblClasse.setBounds(0, 80, 180, 15);
		panel2.add(lblClasse);
		
		JLabel lblTel = new JLabel("Téléphone :");
		lblTel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTel.setBounds(0, 100, 180, 15);
		panel2.add(lblTel);
		
		JLabel lblAdresse = new JLabel("Adresse : ");
		lblAdresse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdresse.setBounds(0, 120, 180, 15);
		panel2.add(lblAdresse);
		
		JLabel lblGroupe = new JLabel("Groupe :");
		lblGroupe.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGroupe.setBounds(0, 140, 180, 15);
		panel2.add(lblGroupe);
		
		JLabel lblDateInsc = new JLabel("Date d'inscription :");
		lblDateInsc.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateInsc.setBounds(0, 160, 180, 15);
		panel2.add(lblDateInsc);
		
		JLabel lblDernier = new JLabel("Dernier paiement :");
		lblDernier.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDernier.setBounds(0, 180, 180, 15);
		panel2.add(lblDernier);
		
		JLabel lblAbs = new JLabel("Absences :");
		lblAbs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAbs.setBounds(0, 205, 180, 15);
		panel2.add(lblAbs);
		
		JPanel panel2_1 = new JPanel();
		panel2_1.setLayout(null);
		panel2_1.setBounds(200, 100, 180, 220);
		contentPane.add(panel2_1);
		
		JLabel lblID_1 = new JLabel(get_ID);
		lblID_1.setBounds(0, 0, 180, 15);
		panel2_1.add(lblID_1);
		
		lblBirthday_1 = new JTextField(get_Brithday);
		lblBirthday_1.setBounds(0, 20, 180, 15);
		panel2_1.add(lblBirthday_1);
		
		lblEcole_1 = new JTextField(get_Ecole);
		lblEcole_1.setBounds(0, 40, 180, 15);
		panel2_1.add(lblEcole_1);
		
		lblNiveau_1 = new JTextField(get_Niveau);
		lblNiveau_1.setBounds(0, 60, 180, 15);
		panel2_1.add(lblNiveau_1);
		
		lblClasse_1 = new JTextField(get_Classe);
		lblClasse_1.setBounds(0, 80, 180, 15);
		panel2_1.add(lblClasse_1);
		
		lblTel_1 = new JTextField(get_Tel);
		lblTel_1.setBounds(0, 100, 180, 15);
		panel2_1.add(lblTel_1);
		
		lblAdresse_1 = new JTextField(get_Adresse);
		lblAdresse_1.setBounds(0, 120, 180, 15);
		panel2_1.add(lblAdresse_1);
		
		lblGroupe_1 = new JTextField(get_Groupe);
		lblGroupe_1.setBounds(0, 140, 180, 15);
		panel2_1.add(lblGroupe_1);
		
		lblDateInsc_1 = new JTextField(get_DateInsc);
		lblDateInsc_1.setBounds(0, 160, 180, 15);
		panel2_1.add(lblDateInsc_1);
		
		JComboBox txtDernierPaiement = new JComboBox();
		txtDernierPaiement.setModel(new DefaultComboBoxModel(new String[] {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"}));
		txtDernierPaiement.setMaximumRowCount(12);
		txtDernierPaiement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblDernier_1 = txtDernierPaiement.getSelectedItem().toString();
			}
		});
		txtDernierPaiement.setSelectedItem(get_Dernier);
		txtDernierPaiement.setBounds(0, 180, 180, 20);
		panel2_1.add(txtDernierPaiement);
		
		lblAbs_1 = new JTextField(get_Absences);
		lblAbs_1.setBounds(0, 205, 180, 15);
		panel2_1.add(lblAbs_1);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(500, 100, 147, 144);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 147, 144);
		lblNewLabel.setIcon(new ImageIcon(ic.getImage().getScaledInstance(lblNewLabel.getWidth(),lblNewLabel.getHeight(), Image.SCALE_DEFAULT)));
		panel.add(lblNewLabel);
		
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
		btnParcourir.setBounds(500, 250, 81, 15);
		contentPane.add(btnParcourir);
		
		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.setFont(new Font("Dialog", Font.BOLD, 8));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setIcon(new ImageIcon((new ImageIcon(image.toString()).getImage()).getScaledInstance(lblNewLabel.getWidth(),lblNewLabel.getHeight(),Image.SCALE_SMOOTH)));
			}
		});
		btnNewButton_1.setBounds(600, 250, 47, 15);
		contentPane.add(btnNewButton_1);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(200, 340, 117, 25);
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.add(btnAnnuler);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.setBounds(325, 340, 117, 25);
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ID = get_ID;				
				String nom = get_Nom;
				String prenom = get_Prenom;
				String birthday = lblBirthday_1.getText();
				String ecole = lblEcole_1.getText();
				String niveau = lblNiveau_1.getText();
				String classe = lblClasse_1.getText();
				String tel = lblTel_1.getText();
				String Adresse = lblAdresse_1.getText();
				String groupe = lblGroupe_1.getText();
				String dateInsc = lblDateInsc_1.getText();
				String dernierPaiement = lblDernier_1;
				String Absences = lblAbs_1.getText();
				
				
				
				 
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
				 
				 String query = "UPDATE `AppStudents` SET `Date de naissance`=?,`Ecole`=?,`Niveau`=?,`Classe`=?,`Téléphone`=?,`Adresse`=?,`Groupe`=?,`Date d'inscription`=?,`Dernier paiement`=?,`Absences`=?,`Photo`=? WHERE `Identifiant`=?";
				 try {
					sta = dbConnection.getConnection().prepareStatement(query);
					sta.setString(12, ID);
					//sta.setString(13, nom);
					//sta.setString(14, prenom);
					birthday = LocalDate.parse(birthday, NEW_FORMATTER).format(OLD_FORMATTER);
					sta.setString(1, birthday);
					sta.setString(2, ecole);
					sta.setString(3, niveau);
					sta.setString(4, classe);
					sta.setString(5, tel);
					sta.setString(6, Adresse);
					sta.setString(7, groupe);
					dateInsc = LocalDate.parse(dateInsc, NEW_FORMATTER).format(OLD_FORMATTER);
					sta.setString(8, dateInsc);
					sta.setString(9, dernierPaiement);
					sta.setString(10, Absences);
					if (input == null) sta.setBytes(11,im);
					else sta.setBinaryStream(11,input);
					if (sta.executeUpdate()>0) {
						JOptionPane.showMessageDialog(null,String.format("Modification validée !\n ID : %s",ID));
						accueilFrame.refreshTable();
						dispose();
					}
				 } catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 }
				 
			}
			
		});
		contentPane.add(btnEnregistrer);
		
	}
}
