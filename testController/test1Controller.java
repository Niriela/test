package test.testController;

import framework.annotations.Controller;
import framework.annotations.Url;

@Controller
public class test1Controller {

     @Url("/test1controller")
    public String sayHello() {
         return "C'est un test de salutation";
    }
}