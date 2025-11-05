package test.testController;

import framework.annotations.Url;

public class test2Controller {
     @Url("/test2controller")
    public void sayBye() {
        System.out.println("C'est un test d'au revoir");
    }
}