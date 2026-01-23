package test.testController;

import framework.annotations.*;
import framework.views.ModelView;
import test.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/formEtudiant")
    public ModelView form() {
        return new ModelView("/form.jsp");
    }

    /**
     * Reçoit les données du formulaire et les insère dans l'objet Etudiant
     * Les paramètres du formulaire auront la notation : e.nom, e.prenom, e.notes.moyenne, etc.
     */

    @PostMapping("/user/saveObject")
    public ModelView saveEtudiant(Etudiant e) {
        System.out.println("\n=== DONNEES REÇUES OBJECT ===");
        System.out.println("Etudiant complet: " + e);
        System.out.println("Nom: " + e.getNom());
        System.out.println("Prénom: " + e.getPrenom());
        System.out.println("Email: " + e.getEmail());
        System.out.println("Moyenne: " + e.getNotes().getMoyenne());
        System.out.println("Matière: " + e.getNotes().getMatiere());
        System.out.println("Note: " + e.getNotes().getNote());
        System.out.println("====================\n");

        // Créer ModelView et ajouter l'objet Etudiant
        ModelView mv = new ModelView("/etudiant-result.jsp");
        mv.addData("etudiant", e);
        return mv;
    }

    /**
     * Exemple avec plusieurs paramètres : objet + primitifs
     */

    // @PostMapping("/saveComplex")
    @JSON
    @PostMapping("/saveJsonComplex")
    public Map<String,Object> saveComplex(Etudiant e, int id, String action) {
        System.out.println("\n=== DONNÉES COMPLEXES ===");
        System.out.println("ID: " + id);
        System.out.println("Action: " + action);
        System.out.println("Etudiant: " + e);
        System.out.println("========================\n");

        Map<String,Object> result = new HashMap<>();

        // build nested map for `e`
        Map<String,Object> eMap = new HashMap<>();
        eMap.put("id", e.getId());
        eMap.put("nom", e.getNom());
        eMap.put("prenom", e.getPrenom());
        eMap.put("email", e.getEmail());

        if (e.getNotes() != null) {
            Map<String,Object> notes = new HashMap<>();
            Double[] moy = e.getNotes().getMoyenne();
            if (moy != null) {
                // convert Double[] to List<Double> for nicer JSON
                List<Double> list = new ArrayList<>();
                for (Double d : moy) if (d != null) list.add(d);
                notes.put("moyenne", list);
            }
            notes.put("matiere", e.getNotes().getMatiere());
            notes.put("note", e.getNotes().getNote());
            eMap.put("notes", notes);
        }

        result.put("e", eMap);
        result.put("id", id);
        result.put("action", action);

        return result;
    }

       // @PostMapping("/saveComplex")
    // public String saveComplex(Etudiant e, int id, String action) {
    //     System.out.println("\n=== DONNÉES COMPLEXES ===");
    //     System.out.println("ID: " + id);
    //     System.out.println("Action: " + action);
    //     System.out.println("Etudiant: " + e);
    //     System.out.println("========================\n");

    //     return "✓ Enregistrement complexe réussi! ID=" + id + ", Action=" + action;
    // }

    


}
