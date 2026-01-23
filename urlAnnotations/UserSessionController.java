package test.urlAnnotations;

import java.util.Map;

import framework.annotations.*;
import framework.views.ModelView;

@Controller
public class UserSessionController {

    @GetMapping("/login")
    public ModelView showLoginForm() {
        ModelView mv = new ModelView();
        mv.setView("/login.jsp");
        return mv;
    }
    
    @PostMapping("/login")
    public ModelView processLogin(@Param("username") String username, 
                                  @Param("password") String password,
                                  @Session Map<String, Object> session) {
        if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
            // Stocker dans la session
            session.put("user", username);
            session.put("userId", 123);
            session.put("isAuthenticated", true);
            
            System.out.println("=== LOGIN SUCCESS ===");
            System.out.println("User: " + username);
            System.out.println("Session créée avec succès");
            
            ModelView mv = new ModelView();
            mv.setView("/login.jsp");
            mv.addData("username", username);
            mv.addData("isLoggedIn", true);
            return mv;
        } else {
            System.out.println("=== LOGIN FAILED ===");
            System.out.println("Username ou password vide");
            
            ModelView mv = new ModelView();
            mv.setView("/login.jsp");
            return mv;
        }
    }
    
    @GetMapping("/profile")
    public ModelView showProfile(@Session Map<String, Object> session) {
        String username = (String) session.get("user");
        if (username == null) {
            ModelView mv = new ModelView();
            mv.setView("/login.jsp");
            return mv;
        }
        
        ModelView mv = new ModelView();
        mv.setView("/profile.jsp");
        mv.addData("username", username);
        mv.addData("isLoggedIn", true);
        return mv;
    }
    
    @GetMapping("/logout")
    public ModelView logout(@Session Map<String, Object> session) {
        session.clear();
        ModelView mv = new ModelView();
        mv.setView("/login.jsp");
        return mv;
    }
}
