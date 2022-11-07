import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class mainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtNom;
	private JLabel lblNom;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
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
	public mainFrame() {
		setForeground(Color.LIGHT_GRAY);
		setTitle("Main");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 464, 315);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMain = new JLabel("Nom de l'enseignant");
		lblMain.setBounds(39, 12, 180, 15);
		contentPane.add(lblMain);
		
		txtNom = new JTextField();
		txtNom.setBounds(39, 37, 212, 19);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		lblNom = new JLabel("");
		lblNom.setBounds(27, 111, 411, 15);
		contentPane.add(lblNom);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Nom = txtNom.getText();
				if (!Nom.isEmpty())
				lblNom.setText("Bienvenue " + Nom);
				else lblNom.setText("Veuillez introduire votre nom !");
			}
		});
		btnOk.setBounds(180, 228, 117, 25);
		contentPane.add(btnOk);
	
		table = new JTable();
		table.setBounds(238, 203, -134, -15);
		contentPane.add(table);
	}
}
