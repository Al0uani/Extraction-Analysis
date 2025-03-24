
public class Departement {
	public int numDep;
	public String NomDep;
	public String categorie;
	public int nbrTables;
	public int superficie;
	@Override
	public String toString() {
		return "Departement [numDep=" + numDep + ", NomDep=" + NomDep + ", categorie=" + categorie + ", nbrTables="
				+ nbrTables + ", superficie=" + superficie + "]";
	}
	public Departement(int numDep, String nomDep, String categorie, int nbrTables, int superficie) {
		super();
		this.numDep = numDep;
		NomDep = nomDep;
		this.categorie = categorie;
		this.nbrTables = nbrTables;
		this.superficie = superficie;
	}
	
	
}
