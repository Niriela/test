package test.testController;

import framework.annotations.Controller;
import framework.annotations.Url;

@Controller
public class test3Controller {
     @Url("/test3controller")
    public void sayThanks(){
        System.out.println("C'est un test de remerciement");
    }

}