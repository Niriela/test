package test.urlAnnotations;

import framework.annotations.*;
import framework.views.ModelView;

import java.util.Map;

@Controller
public class AdminController {

    @GetMapping("/admin/login")
    public ModelView showLogin() {
        return new ModelView("/admin-login.jsp");
    }

    @PostMapping("/admin/login")
    public ModelView doLogin(@Param("username") String username,
                             @Param("role") String role,
                             @framework.annotations.Session Map<String, Object> session) {
        session.put("auth", username);
        session.put("role", role);
        session.put("isAuthenticated", true);
        session.put("role", role);


        ModelView mv = new ModelView("/admin-login.jsp");
        mv.addData("message", "Session creee: " + username + " / role=" + role);
        return mv;
    }

    @Authorized
    @Role("chef")
    @GetMapping("/admin/panel")
    public ModelView panel() {
        ModelView mv = new ModelView("/admin-panel.jsp");
        mv.addData("title", "Tableau de bord chef");
        return mv;
    }
}