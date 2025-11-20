package test.urlAnnotations;

import framework.annotations.*;
import framework.views.ModelView;

@Controller
public class FormController {

    @Url("/form")
    public ModelView showForm() {
        return new ModelView("/form.jsp");
    }

    @Url("/form/save")
    public String saveForm(@Param("id") int id, @Param("nom") String nom) {
        return "Reçu : id=" + id + ", nom=" + nom;
    }

    @Url("/form/saveTest")
    public String saveForm1(int id, String nom) {
        // Affiche les valeurs reçues du formulaire
        return "Reçu : id=" + id + ", nom=" + nom;
    }

    

}
