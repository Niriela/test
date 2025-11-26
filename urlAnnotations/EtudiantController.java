package test.urlAnnotations;
import framework.annotations.*;
import framework.views.ModelView;

@Controller
public class EtudiantController {

    // @Url("/etudiant/{id}")
    // public String getId(int id) { 
    //     return "getIdString : id=" + id;
    // }

    @Url("/etudiant/{id}")
    public int getIntId(int id) {
        return id;
    }
}
