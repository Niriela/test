package test.util;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class FileUploadUtil {
    
    private static String uploadPath;
    
    // Charger le chemin depuis le fichier de configuration
    static {
        try {
            // Utiliser le chemin absolu du fichier de config
            String projectRoot = System.getProperty("user.dir");
            String configPath = projectRoot + "/test/upload-config.txt";
            uploadPath = new String(Files.readAllBytes(Paths.get(configPath))).trim();
            System.out.println("Chemin d'upload chargé: " + uploadPath);
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de la configuration: " + e.getMessage());
            // Utiliser un chemin absolu par défaut
            uploadPath = "D:/ITU_S5/Naina/FRAMEWORK/Sprint_framework/test/assets/images";
            System.out.println("Utilisation du chemin par défaut: " + uploadPath);
        }
    }
    
    /**
     * Obtenir le chemin d'upload configuré
     */
    public static String getUploadPath() {
        return uploadPath;
    }
    
    /**
     * Sauvegarder un fichier uploadé
     * @param fileBytes Les bytes du fichier
     * @param originalFileName Le nom original du fichier
     * @return Le chemin relatif du fichier sauvegardé
     * @throws IOException
     */
    public static String saveFile(byte[] fileBytes, String originalFileName) throws IOException {
        if (fileBytes == null || fileBytes.length == 0) {
            throw new IOException("Le fichier est vide");
        }
        
        // Créer le répertoire s'il n'existe pas
        Path uploadDir = Paths.get(uploadPath);
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }
        
        // Générer un nom unique pour éviter les collisions
        String fileName = generateUniqueFileName(originalFileName);
        
        // Chemin complet du fichier
        Path filePath = uploadDir.resolve(fileName);
        
        // Écrire le fichier
        Files.write(filePath, fileBytes);
        
        System.out.println("Fichier sauvegardé: " + filePath.toString());
        
        return uploadPath + "/" + fileName;
    }
    
    /**
     * Sauvegarder plusieurs fichiers
     * @param files Map contenant les noms de fichiers et leurs bytes
     * @return Map contenant les noms originaux et leurs chemins sauvegardés
     * @throws IOException
     */
    public static Map<String, String> saveFiles(Map<String, byte[]> files) throws IOException {
        java.util.HashMap<String, String> savedFiles = new java.util.HashMap<>();
        
        for (Map.Entry<String, byte[]> entry : files.entrySet()) {
            try {
                String savedPath = saveFile(entry.getValue(), entry.getKey());
                savedFiles.put(entry.getKey(), savedPath);
            } catch (IOException e) {
                System.err.println("Erreur lors de la sauvegarde de " + entry.getKey() + ": " + e.getMessage());
                throw e;
            }
        }
        
        return savedFiles;
    }
    
    /**
     * Générer un nom de fichier unique
     */
    private static String generateUniqueFileName(String originalFileName) {
        // Obtenir l'extension du fichier
        String extension = "";
        int lastDot = originalFileName.lastIndexOf('.');
        if (lastDot > 0) {
            extension = originalFileName.substring(lastDot);
        }
        
        // Générer un timestamp
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = sdf.format(new Date());
        
        // Nom sans extension
        String nameWithoutExt = originalFileName;
        if (lastDot > 0) {
            nameWithoutExt = originalFileName.substring(0, lastDot);
        }
        
        // Nettoyer le nom (remplacer les caractères spéciaux)
        nameWithoutExt = nameWithoutExt.replaceAll("[^a-zA-Z0-9-_]", "_");
        
        return nameWithoutExt + "_" + timestamp + extension;
    }
    
    /**
     * Obtenir les informations d'un fichier
     */
    public static String getFileInfo(byte[] fileBytes, String fileName) {
        if (fileBytes == null) {
            return "Fichier vide";
        }
        
        double sizeInKB = fileBytes.length / 1024.0;
        double sizeInMB = sizeInKB / 1024.0;
        
        String sizeStr;
        if (sizeInMB > 1) {
            sizeStr = String.format("%.2f MB", sizeInMB);
        } else {
            sizeStr = String.format("%.2f KB", sizeInKB);
        }
        
        return String.format("Fichier: %s, Taille: %s (%d bytes)", 
                           fileName, sizeStr, fileBytes.length);
    }
    
    /**
     * Vérifier si un fichier est une image
     */
    public static boolean isImageFile(String fileName) {
        String lowerCase = fileName.toLowerCase();
        return lowerCase.endsWith(".jpg") || 
               lowerCase.endsWith(".jpeg") || 
               lowerCase.endsWith(".png") || 
               lowerCase.endsWith(".gif") || 
               lowerCase.endsWith(".bmp") ||
               lowerCase.endsWith(".webp");
    }
    
    /**
     * Supprimer un fichier
     */
    public static boolean deleteFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.deleteIfExists(path);
        } catch (IOException e) {
            System.err.println("Erreur lors de la suppression du fichier: " + e.getMessage());
            return false;
        }
    }
}
