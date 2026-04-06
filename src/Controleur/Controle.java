package Controleur;
import Vue.EntreeJeu;

public class Controle {
	/**
	 * Constructeur
	 */
	private Controle() {
		this.frmEntreeJeu = new EntreeJeu();
		this.frmEntreeJeu.setVisible(true);
	}
	
	/**
	 * Méthode d'entrée dans l'application
	 * @param args non utilisé
	 */
	private EntreeJeu frmEntreeJeu ;	
	public static void main(String[] args) {
		new Controle();
	}

}
