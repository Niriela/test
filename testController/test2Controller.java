package test.testController;

import framework.annotations.Url;

public class test2Controller {
     @Url("/test2controller")
    public String sayBye() {
        return "C'est un test d'au revoir";
    }
}