import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Adherent {
    public int idA;
    public String CIN;
    public String nom;
    public String ville;
    public int age;
    public int dateInscription;
    public String profession ;
    public String situation ;

    @Override
    public String toString() {
        return "Adherent{" +
                "idA=" + idA +
                ", CIN='" + CIN + '\'' +
                ", nom='" + nom + '\'' +
                ", ville='" + ville + '\'' +
                ", age=" + age +
                ", dateInscription=" + dateInscription +
                ", profession=" + profession +
                ", situation=" + situation +
            
                '}';
    }
}
