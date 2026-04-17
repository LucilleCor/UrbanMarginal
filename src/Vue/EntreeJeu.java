package Vue;

import Controleur.Controle;
import Controleur.Global;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntreeJeu extends JFrame implements Global{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtlp;
	private Arene frmArene;	
	private ChoixJoueur frmChoixJoueur;
	private Controle controle;
	
	/**
	 * Clic sur le bouton Exit
	 * Sortie de l'application
	 */
	private void btnExit_clic() {
		System.exit(0);
	}
	
	/**
	 * Clic sur le bouton Connect
	 * Ouverture de ChoixJoueur
	 */
	private void btnConnect_clic() {
		this.controle.evenementEntreeJeu(txtlp.getText().toString());
	}
	
	/**
	 * Clic sur le bouton Start
	 * Ouverture de Arene
	 */
	private void btnStart_clic() {
		this.controle.evenementEntreeJeu(SERVEUR);
	}

	/**
	 * Create the frame.
	 */
	public EntreeJeu(Controle controle) {
		setTitle("Urban Marginal");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 302, 159);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbl1 = new JLabel("Start a server : ");
		lbl1.setBounds(10, 11, 94, 14);
		contentPane.add(lbl1);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStart_clic();
			}
		});
		btnStart.setBounds(186, 7, 89, 23);
		contentPane.add(btnStart);
		
		JLabel lbl2 = new JLabel("Connect an existing server :");
		lbl2.setBounds(10, 36, 197, 14);
		contentPane.add(lbl2);
		
		JLabel lbl3 = new JLabel("IP server : ");
		lbl3.setBounds(10, 61, 68, 14);
		contentPane.add(lbl3);
			
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConnect_clic();
			}
		});
		btnConnect.setBounds(186, 57, 89, 23);
		contentPane.add(btnConnect);
		
		txtlp = new JTextField();
		txtlp.setText("127.0.0.1");
		txtlp.setBounds(69, 58, 107, 20);
		contentPane.add(txtlp);
		txtlp.setColumns(10);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExit_clic();
			}
		});
		btnExit.setBounds(186, 91, 89, 23);
		contentPane.add(btnExit);		
		
		this.controle = controle;
	}
}
