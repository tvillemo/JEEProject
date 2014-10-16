
import com.objet.Acheteur;
import com.objet.Artiste;
import com.objet.Expert;
import com.objet.Oeuvre;
import com.objet.OeuvreService;
import com.objet.Vendeur;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
 
@ManagedBean
@ViewScoped
public class DataScrollerView implements Serializable {
     
    private List<Oeuvre> oeuvres;
         
    @ManagedProperty("#{OeuvreService}")
    private OeuvreService service;
     
 
    public List<Oeuvre> getOeuvres() {
        return oeuvres;
    }
 
    public void setService(OeuvreService service) {
        this.service = service;
    }
}