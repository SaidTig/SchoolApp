import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class resetPwdFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPwd;
	private JPasswordField txtPwdCon;
	private JTextField txtRepSec;
	private String question;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					resetPwdFrame frame = new resetPwdFrame();
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
	public resetPwdFrame() {
		setTitle("Nouveau mot de passe");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 345);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblReinitialisation = new JLabel("Réinitialisation du mot de passe");
		lblReinitialisation.setHorizontalAlignment(SwingConstants.CENTER);
		lblReinitialisation.setBounds(90, 12, 295, 15);
		contentPane.add(lblReinitialisation);
		
		JLabel lblNomDutilisateur = new JLabel("Nom d'utilisateur");
		lblNomDutilisateur.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNomDutilisateur.setBounds(116, 69, 133, 15);
		contentPane.add(lblNomDutilisateur);
		
		JLabel lblMotDePasse = new JLabel("Nouveau mot de passe");
		lblMotDePasse.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMotDePasse.setBounds(49, 160, 193, 15);
		contentPane.add(lblMotDePasse);
		
		JLabel lblConfirmerLeMot = new JLabel("Confirmer le mot de passe");
		lblConfirmerLeMot.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmerLeMot.setBounds(37, 191, 205, 15);
		contentPane.add(lblConfirmerLeMot);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(284, 67, 114, 19);
		contentPane.add(txtUsername);
		
		txtPwd = new JPasswordField();
		txtPwd.setBounds(284, 158, 114, 19);
		contentPane.add(txtPwd);
		
		txtPwdCon = new JPasswordField();
		txtPwdCon.setBounds(284, 189, 114, 19);
		contentPane.add(txtPwdCon);
		
		txtRepSec = new JTextField();
		txtRepSec.setColumns(10);
		txtRepSec.setBounds(284, 127, 114, 19);
		contentPane.add(txtRepSec);
		
		JComboBox txtQueSec = new JComboBox();
		txtQueSec.setModel(new DefaultComboBoxModel(new String[] {"Equipe préférée", "Couleur préférée", "Animal préféré"}));
		txtQueSec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				question = txtQueSec.getSelectedItem().toString();
			}
		});
		txtQueSec.setBounds(249, 100, 149, 19);
		contentPane.add(txtQueSec);
		
		JLabel lblQuestionSecr = new JLabel("Question Secrète");
		lblQuestionSecr.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQuestionSecr.setBounds(62, 104, 159, 15);
		contentPane.add(lblQuestionSecr);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUsername.getText();
				String password = String.valueOf(txtPwd.getPassword());
				String reponseSec = txtRepSec.getText();
				String questionSec = question;
				
				PreparedStatement s;
				 ResultSet c;
				 String ques = "";
				 String rep = "";
				 boolean exists = true;
				 String q = "SELECT * FROM `AppUsers` WHERE `Username` =?";
				 try {
					 s = dbConnection.getConnection().prepareStatement(q);
					 s.setString(1, username);
					 c = s.executeQuery();
					 exists = c.next();
					 if(exists) {
						 ques = c.getString(6);
						 rep = c.getString(7);
					 }
					 System.out.println(ques);
				 } catch (SQLException e1) {
					 // TODO Auto-generated catch block
					 e1.printStackTrace();
				 }
				
				if (username.equals("") || password.equals("") || reponseSec.equals("") || questionSec.equals("")) {
					 JOptionPane.showMessageDialog(null,"Un champ vide est détecté");
				 }
				 else if (!password.equals(String.valueOf(txtPwdCon.getPassword()))) {
					 JOptionPane.showMessageDialog(null,"Veuillez ré-introduire le mot de passe");
				 }
				 else if (!exists) {
					 JOptionPane.showMessageDialog(null,"Username inexistant");
				 }
				 else if (!questionSec.equals(ques) || !reponseSec.equals(rep)) {
					 JOptionPane.showMessageDialog(null,"Question secrète incorrecte");
				 }
				 else {
					 PreparedStatement sta;
						
					 String query = "UPDATE `AppUsers` SET `Password`=? WHERE `Username`=?";
					 try {
						sta = dbConnection.getConnection().prepareStatement(query);
						sta.setString(1, password);
						sta.setString(2, username);
						if (sta.executeUpdate()>0) {
							JOptionPane.showMessageDialog(null,"Mot de passe modifié!");
						}
					 } catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				 }
			}
		});
		btnEnregistrer.setBounds(281, 238, 117, 25);
		contentPane.add(btnEnregistrer);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnQuitter.setBounds(132, 238, 117, 25);
		contentPane.add(btnQuitter);
		

	}
}
