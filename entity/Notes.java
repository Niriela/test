package test.entity;

/**
 * Classe Notes contenant les informations sur les notes d'un étudiant
 */
public class Notes {
    private Double[] moyenne;
    private String matiere;
    private double note;

    // Constructeur vide (obligatoire pour la réflexion)
    public Notes() {
    }

    // Getters et Setters
    public Double[] getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(Double[] moyenne) {
        this.moyenne = moyenne;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    @Override
    public String toString() {
        String moyStr = "null";
        if (moyenne != null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < moyenne.length; i++) {
                if (i > 0) sb.append(", ");
                sb.append(moyenne[i]);
            }
            moyStr = sb.toString();
        }
        return "Notes{" +
                "moyenne='" + moyStr + '\'' +
                ", matiere='" + matiere + '\'' +
                ", note=" + note +
                '}';
    }
}
