import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "gerants")
public class GerantList {
    private List<Gerant> gerants;

    @XmlElement(name = "gerant")
    public void setGerants(List<Gerant> gerants) {
        this.gerants = gerants;
    }

    public List<Gerant> getGerants() {
        return gerants;
    }
}
