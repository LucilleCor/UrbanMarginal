package Controleur;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Modèle.Jeu;
import Modèle.JeuClient;
import Modèle.JeuServeur;
import Outils.connexion.AsyncResponse;
import Outils.connexion.ClientSocket;
import Outils.connexion.Connection;
import Outils.connexion.ServeurSocket;
import Vue.Arene;
import Vue.ChoixJoueur;
import Vue.EntreeJeu;



public class Controle implements AsyncResponse, Global {
		
	private EntreeJeu frmEntreeJeu;
	private Arene frmArene;	
	private ChoixJoueur frmChoixJoueur;
	private Jeu leJeu;

	/**
	 * Constructeur
	 */
	private Controle() {
		this.frmEntreeJeu = new EntreeJeu(this);
		this.frmEntreeJeu.setVisible(true);
	}
	
	/**
	 * Gèe les demandes provenant de EntreeJeu
	 * @param info
	 */
	public void evenementEntreeJeu(String info) {
		if (info.equals(SERVEUR)) {
			new ServeurSocket(this, 6666);
			this.leJeu = new JeuServeur(this);
			this.frmEntreeJeu.dispose();
			this.frmArene = new Arene();
			((JeuServeur)leJeu).constructionMurs();
			this.frmArene.setVisible(true);
		}else {
			new ClientSocket(this, info, PORT);			
		}
	}
	
	/**
	 * Gère les demandes provenant de ChoixJoueur
	 * @param pseudo
	 * @param numPerso
	 */
	public void evenementChoixJoueur(String pseudo, int numPerso) {
		this.frmChoixJoueur.dispose();
		this.frmArene.setVisible(true);		
		((JeuClient)this.leJeu).envoi(PSEUDO+STRINGSEPARE+pseudo+STRINGSEPARE+ numPerso);
	}
	
	/**
	 * Gère les demandes de JeuServeur
	 * @param ordre
	 * @param info
	 */
	public void evenementJeuServeur(String ordre, Object info) {
		switch (ordre) {
			case AJOUTMUR :
				this.frmArene.ajoutMurs(info);
				break;
			case AJOUTPANELMURS :
				this.leJeu.envoi((Connection)info, this.frmArene.getJpnMurs());
				break;
			case AJOUTJLABELJEU :
				this.frmArene.ajoutJLabelJeu((JLabel)info);
				break;
			case MODIFPANELJEU : 
				this.leJeu.envoi((Connection)info, this.frmArene.getJpnJeu());
				break;
		}
	}
	
	/**
	 * Gère les demandes de JeuClient
	 * @param ordre
	 * @param info
	 */
	public void evenementJeuClient(String ordre, Object info) {
		switch (ordre) {
			case AJOUTPANELMURS :
				this.frmArene.setJpnMurs((JPanel)info);
				break;
			case MODIFPANELJEU :
				this.frmArene.setJpnJeu((JPanel)info);
				break;
		}
	}
	
	/**
	 * Envoi d'une information vers l'ordinateur distant
	 * @param connection
	 * @param info
	 */
	public void envoi(Connection connection, Object info) {
		connection.envoi(info);
	}
	
	/**
	 * Méthode d'entrée dans l'application
	 * @param args non utilisé
	 */
	public static void main(String[] args) {
		new Controle();
	}
	
	/**
	 * Gère la réception de données provenant de l'ordinateur distant
	 * @param connection objet de connexion de l'ordinateur distant
	 * @param ordre connexion, reception ou deconnexon
	 * @param info information reçue
	 */
	@Override
	public void reception(Connection connection, String ordre, Object info) {
		switch(ordre) {
			case CONNEXION :
				if (!(this.leJeu instanceof JeuServeur)) {
					this.frmEntreeJeu.dispose();
					this.frmChoixJoueur = new ChoixJoueur(this);
					this.frmArene = new Arene();
					this.frmChoixJoueur.setVisible(true);
					this.leJeu = new JeuClient(this);
					this.leJeu.connexion(connection);
				}else {
					this.leJeu.connexion(connection);
				}
				break;
			case RECEPTION :
				this.leJeu.reception(connection, info);
				break;
			case DECONNEXION :
		}
				
	}
}