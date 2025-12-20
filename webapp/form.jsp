<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formulaire d'Enregistrement Étudiant</title>
</head>
<body>
    <div class="container"> 
        <h1> ETU003348 </h1>
        <h1>📝 Enregistrement Étudiant</h1>
        
        <div class="info">
            <strong>ℹ️ Note:</strong> Les données avec la notation pointée (e.nom, e.notes.moyenne) 
            seront automatiquement bindées à l'objet Etudiant grâce au framework!
        </div>

        <form method="POST" action="${pageContext.request.contextPath}/user/saveObject">
            <!-- Section Informations Personnelles -->
            <div class="form-section">
                <h2>Informations Personnelles</h2>
                
                <div class="form-group">
                    <label for="nom">Nom <span style="color: red;">*</span></label>
                    <input type="text" id="nom" name="e.nom" required placeholder="Dupont">
                </div>

                <div class="form-group">
                    <label for="prenom">Prénom <span style="color: red;">*</span></label>
                    <input type="text" id="prenom" name="e.prenom" required placeholder="Jean">
                </div>

                <div class="form-group">
                    <label for="email">Email <span style="color: red;">*</span></label>
                    <input type="email" id="email" name="e.email" required placeholder="jean.dupont@example.com">
                </div>

                <div class="form-group">
                    <label for="id">ID Étudiant</label>
                    <input type="number" id="id" name="e.id" placeholder="12345">
                </div>
            </div>

            <!-- Section Notes -->
            <div class="form-section">
                <h2>Informations sur les Notes</h2>
                
                <div class="form-group">
                    <label for="matiere">Matière <span style="color: red;">*</span></label>
                    <input type="text" id="matiere" name="e.notes.matiere" required placeholder="Mathématiques">
                </div>

                <div class="form-group">
                    <label for="note">Note <span style="color: red;">*</span></label>
                    <input type="number" id="note" name="e.notes.note" step="0.01" min="0" max="20" required placeholder="15.5">
                </div>

                <div class="form-group">
                    <label for="moyenne">Moyennes (plusieurs valeurs possible) <span style="color: red;">*</span></label>
                    <input type="number" id="moyenne1" name="e.notes.moyenne[]" step="0.01" required placeholder="15.50">
                    <input type="number" id="moyenne2" name="e.notes.moyenne[]" step="0.01" placeholder="12.00">
                    <input type="number" id="moyenne3" name="e.notes.moyenne[]" step="0.01" placeholder="14.25">
                    <p style="font-size:0.9em;color:#666;">Si vous voulez 1 seule valeur, remplissez le premier champ.</p>
                </div>
            </div>

            <!-- Bouton Envoi -->
            <button type="submit">✓ Enregistrer l'Étudiant</button>
        </form>

        <hr style="margin-top: 40px; border: none; border-top: 1px solid #eee;">

        <h2 style="color: #666; margin-top: 30px;">🧪 Test Complexe (Optionnel)</h2>
        <form method="POST" action="${pageContext.request.contextPath}/saveJsonComplex" style="margin-top: 20px;">
            <div class="form-group">
                <label>ID (paramètre simple) <span style="color: red;">*</span></label>
                <input type="number" name="id" required placeholder="123">
            </div>

            <div class="form-group">
                <label>Action (paramètre simple) <span style="color: red;">*</span></label>
                <input type="text" name="action" required placeholder="ENREGISTRER">
            </div>

            <div class="form-group">
                <label>Nom Étudiant <span style="color: red;">*</span></label>
                <input type="text" name="e.nom" required placeholder="Martin">
            </div>

            <div class="form-group">
                <label>Moyennes <span style="color: red;">*</span></label>
                <input type="number" name="e.notes.moyenne[]" required step="0.01" placeholder="13.50">
                <input type="number" name="e.notes.moyenne[]" step="0.01" placeholder="14.00">
            </div>

            <button type="submit">✓ Test Complexe</button>
        </form>
    </div>
</body>
</html>