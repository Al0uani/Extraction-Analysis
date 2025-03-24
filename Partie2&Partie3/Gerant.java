import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)  // Utilise directement les champs
public class Gerant {
    private int idG;
    private String nomG;
    private int age;
    private String cartierResid;
    private int numDep;
    private String sexe;
    private String specialite;

    // Constructeur vide requis par JAXB
    public Gerant() {}

    // Getters et setters
    public int getIdG() { return idG; }
    public void setIdG(int idG) { this.idG = idG; }

    public String getNomG() { return nomG; }
    public void setNomG(String nomG) { this.nomG = nomG; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getCartierResid() { return cartierResid; }
    public void setCartierResid(String cartierResid) { this.cartierResid = cartierResid; }

    public int getNumDep() { return numDep; }
    public void setNumDep(int numDep) { this.numDep = numDep; }

    public String getSexe() { return sexe; }
    public void setSexe(String sexe) { this.sexe = sexe; }

    public String getSpecialite() { return specialite; }
    public void setSpecialite(String specialite) { this.specialite = specialite; }

	@Override
	public String toString() {
		return "Gerant [idG=" + idG + ", nomG=" + nomG + ", age=" + age + ", cartierResid=" + cartierResid + ", numDep="
				+ numDep + ", sexe=" + sexe + ", specialite=" + specialite + "]";
	}
}
