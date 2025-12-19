package test.urlAnnotations;

import java.util.Map;

import framework.annotations.Controller;
import framework.annotations.GetMapping;
import framework.annotations.Param;
import framework.annotations.PostMapping;
import framework.views.ModelView;
import test.entity.User;

@Controller
public class UploadController {

    @GetMapping("/user/formUpload")
    public ModelView showUserForm() {
        ModelView mv = new ModelView("/user_upload.jsp");
        return mv;
    }

    //   // Sprint10 - Upload fichier
    // @PostMapping("/user/upload")
    // public String saveDonnees(Map<String, Object> normalData, Map<String, byte[]> files) {
    //     System.out.println("=== Données normales ===");
    //     normalData.forEach((key, value) -> System.out.println(key + " = " + value));

    //     System.out.println("\n=== Fichiers ===");
    //     files.forEach((key, fileBytes) -> System.out.println(key + " = " + fileBytes.length + " bytes"));

    //     return "Données et fichiers reçus avec succès!";
    // }

    @PostMapping("/user/upload")
    public String saveDonnees(User user, Map<String, byte[]> files) {
        System.out.println("=== Données normales ===");
        if (user != null) {
            System.out.println("firstName = " + user.getFirstName());
            System.out.println("lastName = " + user.getLastName());
        } else {
            System.out.println("Aucune donnée utilisateur reçue.");
        }

        System.out.println("\n=== Fichiers ===");
        files.forEach((key, fileBytes) -> {
            int size = (fileBytes != null) ? fileBytes.length : 0;
            System.out.println(key + " = " + size + " bytes");
        });

        return "Données et fichiers reçus avec succès!";
    }

    // @PostMapping("/user/upload")
    // public String saveDonnees(
    //         @Param("firstName") String firstName,
    //         @Param("lastName") String lastName,
    //         Map<String, byte[]> files) {
    //     System.out.println("=== Données normales ===");
    //     System.out.println("firstName = " + firstName);
    //     System.out.println("lastName = " + lastName);

    //     System.out.println("\n=== Fichiers ===");
    //     files.forEach((key, fileBytes) -> {
    //         int size = (fileBytes != null) ? fileBytes.length : 0;
    //         System.out.println(key + " = " + size + " bytes");
    //     });

    //     return "Données et fichiers reçus avec succès!";
    // }
}
