package test.testController;

import framework.annotations.Controller;
import framework.annotations.Url;

@Controller
public class test3Controller {
    //  @Url("/test3controller")
    // public String sayThanks(){
    //     return "C'est un test de remerciement";
    // }

    @Url("/test3controller")
    public int sayAnumber(){
        return 200;
    }

}