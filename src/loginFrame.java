import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class loginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginFrame frame = new loginFrame();
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
	public loginFrame() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomDeLutilisateur = new JLabel("Nom de l'utilisateur");
		lblNomDeLutilisateur.setBounds(38, 50, 147, 15);
		contentPane.add(lblNomDeLutilisateur);
		
		JLabel lblNomDeLutilisateur_1 = new JLabel("Mot de passe");
		lblNomDeLutilisateur_1.setBounds(38, 108, 147, 15);
		contentPane.add(lblNomDeLutilisateur_1);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(203, 48, 159, 19);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnQuitter.setBounds(85, 178, 140, 25);
		contentPane.add(btnQuitter);
		
		JButton btnSeConnecter = new JButton("Se connecter");
		btnSeConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement sta;
				ResultSet res;
				String username = txtUsername.getText();
				String password = String.valueOf(txtPassword.getPassword());
				String query = "SELECT * FROM `AppUsers` WHERE `Username` =? AND `Password`=?";
				try {
					sta = dbConnection.getConnection().prepareStatement(query);
					sta.setString(1, username);
					sta.setString(2, password);
					res = sta.executeQuery();
					if(res.next()) {
						accueilFrame acc = new accueilFrame();
						//acc.setPreferredSize(new Dimension(500, 400));
						acc.setExtendedState(acc.getExtendedState() | JFrame.MAXIMIZED_BOTH);
						acc.pack();
						acc.setLocationRelativeTo(null);
						acc.setVisible(true);
						acc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Connexion échouée");
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSeConnecter.setBounds(258, 178, 147, 25);
		contentPane.add(btnSeConnecter);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setBounds(187, 12, 70, 15);
		contentPane.add(lblLogin);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(203, 106, 159, 19);
		contentPane.add(txtPassword);
		
		JLabel lblCr = new JLabel("Créer un nouvel utilisateur");
		lblCr.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				lblCr.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblCr.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				registerFrame reg = new registerFrame();
				reg.setPreferredSize(new Dimension(500, 400));
				reg.pack();
				reg.setLocationRelativeTo(null);
				reg.setVisible(true);
				reg.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		lblCr.setHorizontalAlignment(SwingConstants.CENTER);
		lblCr.setBounds(114, 236, 248, 15);
		contentPane.add(lblCr);
		
		JLabel lblOubli = new JLabel("Mot de passe oublié ?");
		lblOubli.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				lblOubli.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
		});
		lblOubli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				resetPwdFrame oubli = new resetPwdFrame();
				oubli.setPreferredSize(new Dimension(500, 400));
				oubli.pack();
				oubli.setLocationRelativeTo(null);
				oubli.setVisible(true);
				oubli.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		lblOubli.setBounds(203, 133, 159, 15);
		contentPane.add(lblOubli);
	}
}
