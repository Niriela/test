package test.urlAnnotations;

import framework.annotations.Controller;
import framework.annotations.Url;


public class Test1 {
    @Url("/test1")
    public void sayHello() {
        System.out.println("C'est un test de salutation");
    }
    
}
