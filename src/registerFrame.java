import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import java.awt.Choice;
import java.awt.Cursor;
import java.awt.List;
import java.awt.Label;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class registerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtUsername;
	private JPasswordField txtPwd;
	private JPasswordField txtPwdCon;
	private JTextField txtEmail;
	private JTextField txtRepSec;
	private String QueSec;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerFrame frame = new registerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public registerFrame() {
		setTitle("Nouvel utilisateur");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 440, 392);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNom.setBounds(146, 47, 70, 15);
		contentPane.add(lblNom);
		
		JLabel lblPr = new JLabel("Prénom");
		lblPr.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPr.setBounds(146, 74, 70, 15);
		contentPane.add(lblPr);
		
		JLabel lblNomDutilisateur = new JLabel("Nom d'utilisateur");
		lblNomDutilisateur.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomDutilisateur.setBounds(83, 101, 133, 15);
		contentPane.add(lblNomDutilisateur);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe");
		lblMotDePasse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMotDePasse.setBounds(64, 128, 152, 15);
		contentPane.add(lblMotDePasse);
		
		JLabel lblConfirmerLeMot = new JLabel("Confirmer le mot de passe");
		lblConfirmerLeMot.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmerLeMot.setBounds(12, 155, 205, 15);
		contentPane.add(lblConfirmerLeMot);
		
		txtNom = new JTextField();
		txtNom.setBounds(248, 45, 114, 19);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		txtPrenom = new JTextField();
		txtPrenom.setColumns(10);
		txtPrenom.setBounds(248, 72, 114, 19);
		contentPane.add(txtPrenom);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(248, 99, 114, 19);
		contentPane.add(txtUsername);
		
		txtPwd = new JPasswordField();
		txtPwd.setBounds(248, 126, 114, 19);
		contentPane.add(txtPwd);
		
		txtPwdCon = new JPasswordField();
		txtPwdCon.setBounds(248, 151, 114, 19);
		contentPane.add(txtPwdCon);
		
		JLabel lblAdresselectronique = new JLabel("Adresse électronique");
		lblAdresselectronique.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAdresselectronique.setBounds(22, 179, 194, 15);
		contentPane.add(lblAdresselectronique);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(248, 177, 114, 19);
		contentPane.add(txtEmail);
		
		txtRepSec = new JTextField();
		txtRepSec.setBounds(248, 238, 114, 19);
		contentPane.add(txtRepSec);
		txtRepSec.setColumns(10);
		
		JLabel lblQuestionSec = new JLabel("Question secrète");
		lblQuestionSec.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuestionSec.setBounds(64, 209, 152, 15);
		contentPane.add(lblQuestionSec);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Equipe préférée", "Couleur préférée", "Animal préféré"}));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QueSec = comboBox.getSelectedItem().toString();
			}
		});
		comboBox.setBounds(234, 207, 149, 19);
		contentPane.add(comboBox);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String nom = txtNom.getText();
				 String prenom = txtPrenom.getText();
				 String username = txtUsername.getText();
				 String password = String.valueOf(txtPwd.getPassword());
				 String email = txtEmail.getText();
				 String reponseSec = txtRepSec.getText();
				 String questionSec = QueSec;
				 String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
				 Pattern pattern = Pattern.compile(regex);
				 Matcher matcher = pattern.matcher(email);
				 
				 PreparedStatement s;
				 ResultSet c;
				 boolean exists = true;
				 String q = "SELECT * FROM `AppUsers` WHERE `Username` =?";
				 try {
					 s = dbConnection.getConnection().prepareStatement(q);
					 s.setString(1, username);
					 c = s.executeQuery();
					 exists = c.next();
				 } catch (SQLException e1) {
					 // TODO Auto-generated catch block
					 e1.printStackTrace();
				 }
				 
				 if (prenom.equals("") || nom.equals("") || username.equals("") || password.equals("") || reponseSec.equals("") || questionSec.equals("")) {
					 JOptionPane.showMessageDialog(null,"Un champ vide est détecté");
				 }
				 else if (!password.equals(String.valueOf(txtPwdCon.getPassword()))) {
					 JOptionPane.showMessageDialog(null,"Veuillez ré-introduire le mot de passe");
				 }
				 else if (!matcher.matches()) {
					 JOptionPane.showMessageDialog(null,"Veuillez introduire un e-mail correct");
				 }
				 else if (exists) {
					 JOptionPane.showMessageDialog(null,"Username existant");
				 }
				 else {
				 PreparedStatement sta;
				 
				 String query = "INSERT INTO `AppUsers` (`Nom`,`Prenom`,`Username`,`Password`,`Email`,`QuestionSec`,`ReponseSec`) VALUES (?,?,?,?,?,?,?)";
				 try {
					sta = dbConnection.getConnection().prepareStatement(query);
					sta.setString(1, nom);
					sta.setString(2, prenom);
					sta.setString(3, username);
					sta.setString(4, password);
					sta.setString(5, email);
					sta.setString(6, questionSec);
					sta.setString(7, reponseSec);
					if (sta.executeUpdate()>0) {
						JOptionPane.showMessageDialog(null,"Utilisateur ajouté !");
					}
				 } catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 }
				 
			}
			
		});
		btnEnregistrer.setBounds(245, 314, 117, 25);
		contentPane.add(btnEnregistrer);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.exit(0);
				dispose();
			}
		});
		btnQuitter.setBounds(96, 314, 117, 25);
		contentPane.add(btnQuitter);
		
		JLabel lblCrerUnNouvel = new JLabel("Créer un nouvel utilisateur");
		lblCrerUnNouvel.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrerUnNouvel.setBounds(92, 12, 273, 15);
		contentPane.add(lblCrerUnNouvel);
		

	}
}
