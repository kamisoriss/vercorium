package fr.javafxcours.vercorium;

import java.sql.*;
import java.util.ArrayList;

public class Gestionbdd
{
    private static final String URL = "jdbc:sqlite:vercorium.sqlite";
    public ArrayList<String> getlistcapteur() {

        ArrayList<String> listeDesCapteurs = new ArrayList<>();
        String sql = "SELECT id_capteur, nom_capteur  FROM Capteur";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
           // pstmt.setString(0, "actif");

            // Exécution de la requête et récupération des résultats dans un ResultSet
            try (ResultSet rs = pstmt.executeQuery()) {

                // rs.next() avance ligne par ligne dans les résultats de la base
                while (rs.next()) {
                    // Extraction des valeurs de la ligne courante selon leur type
                    String nom_capteur = rs.getString("nom_capteur");
                    listeDesCapteurs.add(nom_capteur);
                    System.out.println("capteur trouvé  : " + nom_capteur);

                    // C'est à cet endroit que vous pouvez construire vos objets Java
                    // et les ajouter à l'ObservableList de votre ListView JavaFX.
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur de connexion ou de requête : " + e.getMessage());
        }
        return listeDesCapteurs;
    }
}
