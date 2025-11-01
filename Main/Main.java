package test.Main;

import framework.annotations.Controller;
import java.io.File;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        String packageName = "test.testController";

        // Utilisation de getClasses
        System.out.println("Résultat avec getClasses :");
        List<Class<?>> classesGet = AnnotationScanner.getClasses(packageName);
        for (Class<?> clazz : classesGet) {
            if (clazz.isAnnotationPresent(Controller.class)) {
                System.out.println("The class " + clazz.getSimpleName() + " est annote avec @Controller.");
            } else {
                System.out.println("The class " + clazz.getSimpleName() + " n'est pas annote avec @Controller.");
            }
        }

        // Utilisation directe de findClasses
        System.out.println("\nRésultat avec findClasses :");
        String path = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(path);
        if (resource != null) {
            File directory = new File(resource.getFile());
            List<Class<?>> classesFind = AnnotationScanner.findClasses(directory, packageName);
            for (Class<?> clazz : classesFind) {
                if (clazz.isAnnotationPresent(Controller.class)) {
                    System.out.println("The class " + clazz.getSimpleName() + " est annote avec @Controller.");
                } else {
                    System.out.println("The class " + clazz.getSimpleName() + " n'est pas annote avec @Controller.");
                }
            }
        } else {
            System.out.println("Le dossier du package n'a pas été trouvé.");
        }

        // Utilisation de findAllClassesInProject
        System.out.println("\nRésultat avec findAllClassesInProject :");
        try {
            List<Class<?>> allClasses = AnnotationScanner.findAllClassesInProject(packageName);
            for (Class<?> clazz : allClasses) {
                String pkg = (clazz.getPackage() != null) ? clazz.getPackage().getName() : "";
                // inclure le package exact et ses sous-packages
                if (pkg.equals(packageName) || pkg.startsWith(packageName + ".")) {
                    if (clazz.isAnnotationPresent(Controller.class)) {
                        System.out.println("The class " + clazz.getSimpleName() + " est annote avec @Controller.");
                    } else {
                        System.out.println("The class " + clazz.getSimpleName() + " n'est pas annote avec @Controller.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur findAllClassesInProject : " + e.getMessage());
        }
    }
}