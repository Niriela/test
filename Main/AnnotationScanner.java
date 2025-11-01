package test.Main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AnnotationScanner {

    public static List<Class<?>> getClasses(String packageName) throws IOException, ClassNotFoundException {
        String path = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(path);
        if (resource == null) {
            throw new ClassNotFoundException("Le dossier du package '" + packageName + "' n'a pas été trouvé.");
        }

        File directory = new File(resource.getFile());
        return findClasses(directory, packageName);
    }

    public static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) return classes;

        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                String className = packageName + '.' + file.getName().replaceAll("\\.class$", "");
                classes.add(Class.forName(className)); 
            }
        }
        return classes;
    }

    public static List<Class<?>> findAllClassesInProject(String projectPathOrPackage) throws ClassNotFoundException {
        File root;
        // Si le chemin existe sur le disque, on l'utilise directement
        File testFile = new File(projectPathOrPackage);
        if (testFile.exists() && testFile.isDirectory()) {
            root = testFile;
        } else {
            // Sinon, on suppose que c'est un package et on cherche dans le classpath
            String path = projectPathOrPackage.replace('.', '/');
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL resource = classLoader.getResource(path);
            if (resource == null) {
                throw new ClassNotFoundException("Le chemin ou package '" + projectPathOrPackage + "' n'a pas été trouvé.");
            }
            root = new File(resource.getFile());
        }
        List<Class<?>> classes = new ArrayList<>();
        findClassesRecursively(root, projectPathOrPackage, classes);
        return classes;
    }

    private static void findClassesRecursively(File directory, String packageName, List<Class<?>> classes) throws ClassNotFoundException {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                String subPackage = packageName.isEmpty() ? file.getName() : packageName + "." + file.getName();
                findClassesRecursively(file, subPackage, classes);
            } else if (file.getName().endsWith(".class")) {
                String className = packageName.isEmpty() ? file.getName().replaceAll("\\.class$", "")
                                                     : packageName + '.' + file.getName().replaceAll("\\.class$", "");
                try {
                    classes.add(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    throw new ClassNotFoundException("Impossible de charger la classe : " + className, e);
                }
            }
        }
    }

}
