package test.urlAnnotations;

import framework.annotations.*;
import framework.views.ModelView;


@Controller
public class UrlController {
    @Url("/test1")
    public void sayHello() {
        System.out.println("C'est un test de salutation");
    }

    @Url("/test2")
    public void sayBye() {
        System.out.println("C'est un test d'au revoir");
    }

    @Url("/test3")
    public void sayThanks(){
        System.out.println("C'est un test de remerciement");
    }

    @Url("/testmv")
    public ModelView showModel() {
        // renvoie la JSP située à la racine du webapp : /test.jsp
        return new ModelView("/test.jsp");
    }

    // @Url("/testhtml")
    // public ModelView showHtml() {
    //     // place test.html à la racine du webapp (webapp/test.html) ou sous WEB-INF si tu veux la protéger
    //     return new ModelView("/test1.html");
    // }

    @Url("/testdata")
    public ModelView showDataModel() {
        ModelView mv = new ModelView("/testdata.jsp");
        mv.addData("nom", "Naina");
        mv.addData("age", 22);
        mv.addData("ville", "Antananarivo");
        return mv;
    }


}
