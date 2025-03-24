
public class Livre {
	public int idLivre;
	public String titre;
	public String auteur;
	public int DateSortie;
	public String editeur;
	public double prixAchat;
	public int numDep;
	
	public Livre(int idLivre, String titre, String auteur, int dateSortie, String editeur, double prixAchat,
			int numDep) {
		this.idLivre = idLivre;
		this.titre = titre;
		this.auteur = auteur;
		this.DateSortie = dateSortie;
		this.editeur = editeur;
		this.prixAchat = prixAchat;
		this.numDep = numDep;
	}

	@Override
	public String toString() {
		return "Livre [idLivre=" + idLivre + ", titre=" + titre + ", auteur=" + auteur + ", DateSortie=" + DateSortie
				+ ", editeur=" + editeur + ", prixAchat=" + prixAchat + ", numDep=" + numDep + "]";
	}
	
	
}
