import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class detailsEleve extends JFrame {

	private JPanel contentPane;

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
	public detailsEleve(String get_ID,String get_Nom, String get_Prenom, String get_Brithday, String get_Ecole, String get_Niveau, String get_Classe, String get_Tel, String get_TelParent, String get_Groupe, String get_DateInsc, String get_Dernier, String get_Absences, @Nullable ImageIcon ic) {
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
		
		JPanel panel = new JPanel();
		panel.setBounds(354, 89, 147, 144);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 147, 144);
		lblNewLabel.setIcon(new ImageIcon(ic.getImage().getScaledInstance(lblNewLabel.getWidth(),lblNewLabel.getHeight(), Image.SCALE_DEFAULT)));
		panel.add(lblNewLabel);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(12, 100, 180, 214);
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
		
		JLabel lblTelParent = new JLabel("Contact des parents :");
		lblTelParent.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelParent.setBounds(0, 120, 180, 15);
		panel2.add(lblTelParent);
		
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
		lblAbs.setBounds(0, 200, 180, 15);
		panel2.add(lblAbs);
		
		JPanel panel2_1 = new JPanel();
		panel2_1.setLayout(null);
		panel2_1.setBounds(200, 100, 180, 214);
		contentPane.add(panel2_1);
		
		JLabel lblID_1 = new JLabel(get_ID);
		lblID_1.setBounds(0, 0, 180, 15);
		panel2_1.add(lblID_1);
		
		JLabel lblBirthday_1 = new JLabel(get_Brithday);
		lblBirthday_1.setBounds(0, 20, 180, 15);
		panel2_1.add(lblBirthday_1);
		
		JLabel lblEcole_1 = new JLabel(get_Ecole);
		lblEcole_1.setBounds(0, 40, 180, 15);
		panel2_1.add(lblEcole_1);
		
		JLabel lblNiveau_1 = new JLabel(get_Niveau);
		lblNiveau_1.setBounds(0, 60, 180, 15);
		panel2_1.add(lblNiveau_1);
		
		JLabel lblClasse_1 = new JLabel(get_Classe);
		lblClasse_1.setBounds(0, 80, 180, 15);
		panel2_1.add(lblClasse_1);
		
		JLabel lblTel_1 = new JLabel(get_Tel);
		lblTel_1.setBounds(0, 100, 180, 15);
		panel2_1.add(lblTel_1);
		
		JLabel lblTelParent_1 = new JLabel(get_TelParent);
		lblTelParent_1.setBounds(0, 120, 180, 15);
		panel2_1.add(lblTelParent_1);
		
		JLabel lblGroupe_1 = new JLabel(get_Groupe);
		lblGroupe_1.setBounds(0, 140, 180, 15);
		panel2_1.add(lblGroupe_1);
		
		JLabel lblDateInsc_1 = new JLabel(get_DateInsc);
		lblDateInsc_1.setBounds(0, 160, 180, 15);
		panel2_1.add(lblDateInsc_1);
		
		JLabel lblDernier_1 = new JLabel(get_Dernier);
		lblDernier_1.setBounds(0, 180, 180, 15);
		panel2_1.add(lblDernier_1);
		
		JLabel lblAbs_1 = new JLabel(get_Absences);
		lblAbs_1.setBounds(0, 200, 180, 15);
		panel2_1.add(lblAbs_1);
	}
}
