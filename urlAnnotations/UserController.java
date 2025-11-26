package test.urlAnnotations;

import framework.annotations.*;
import framework.views.ModelView;

@Controller
public class UserController {

    @GetMapping("/user")
    public ModelView showUserForm() {
        ModelView mv = new ModelView("/user.jsp");
        mv.addData("title", "Formulaire Utilisateur");
        return mv;
    }
    
    @PostMapping("/user")
    public String saveUser(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email) {
        return "Utilisateur créé : " + firstName + " " + lastName + " (" + email + ")";
    }

    @GetMapping("/user/list")
    public ModelView listUsers() {
        ModelView mv = new ModelView("/user-list.jsp");
        mv.addData("message", "Liste des utilisateurs");
        return mv;
    }

    
    // @PostMapping("/user/save")
    // public String saveUser(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("email") String email) {
    //     return "Utilisateur créé : " + firstName + " " + lastName + " (" + email + ")";
    // }

    // @PostMapping("/user/save")
    // public String registerUser(String firstName, String lastName, String email) {
    //     return "Utilisateur enregistré : " + firstName + " " + lastName + " (" + email + ")";
    // }
}
