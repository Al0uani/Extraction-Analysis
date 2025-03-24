import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Inscriptions {
	public int idAdherent;
	public int idDep;
	@Override
	public String toString() {
		return "Inscriptions [idAdherent=" + idAdherent + ", idDep=" + idDep + "]";
	}
	
}
