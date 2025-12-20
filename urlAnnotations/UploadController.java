package test.urlAnnotations;

import java.util.Map;

import framework.annotations.Controller;
import framework.annotations.GetMapping;
import framework.annotations.Param;
import framework.annotations.PostMapping;
import framework.views.ModelView;
import test.entity.User;
import test.util.FileUploadUtil;

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
        try {
            System.out.println("=== Données normales ===");
            if (user != null) {
                System.out.println("firstName = " + user.getFirstName());
                System.out.println("lastName = " + user.getLastName());
            } else {
                System.out.println("Aucune donnée utilisateur reçue.");
            }

            System.out.println("\n=== Fichiers reçus ===");
            files.forEach((key, fileBytes) -> {
                int size = (fileBytes != null) ? fileBytes.length : 0;
                System.out.println("Nom du fichier: " + key);
                System.out.println("Taille: " + size + " bytes");
                System.out.println(FileUploadUtil.getFileInfo(fileBytes, key));
                System.out.println("---");
            });

            // Sauvegarder tous les fichiers
            Map<String, String> savedFiles = FileUploadUtil.saveFiles(files);
            
            System.out.println("\n=== Fichiers sauvegardés ===");
            savedFiles.forEach((originalName, savedPath) -> {
                System.out.println(originalName + " -> " + savedPath);
            });

            return "Données et fichiers sauvegardés avec succès! " + savedFiles.size() + " fichier(s) uploadé(s).";
            
        } catch (Exception e) {
            System.err.println("Erreur lors de l'upload: " + e.getMessage());
            e.printStackTrace();
            return "Erreur lors de l'upload: " + e.getMessage();
        }
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
