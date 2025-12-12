package test.entity;

/**
 * Classe Notes contenant les informations sur les notes d'un étudiant
 */
public class Notes {
    private String moyenne;
    private String matiere;
    private double note;

    // Constructeur vide (obligatoire pour la réflexion)
    public Notes() {
    }

    // Getters et Setters
    public String getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(String moyenne) {
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
        return "Notes{" +
                "moyenne='" + moyenne + '\'' +
                ", matiere='" + matiere + '\'' +
                ", note=" + note +
                '}';
    }
}
