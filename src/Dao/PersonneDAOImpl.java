package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import CategorieEnum.Categorie;
import Database.DatabaseConnection;
import Entite.Personne;

public class PersonneDAOImpl implements PersonneDAO {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    String sqlScript;
    ResultSet rs;

    @Override
    public Personne getMembreById(int id) throws SQLException {
        connection = DatabaseConnection.getConnection();
        Personne personne = null;

        sqlScript = "SELECT id, nom, prenom, matricule, telephone, adresse_courriel, domaine_activite, mot_de_passe, categorie, liste_rouge FROM personne WHERE id = ? ";
        preparedStatement = connection.prepareStatement(sqlScript);
        preparedStatement.setInt(1, id);
        rs = preparedStatement.executeQuery();

        if (rs.next()) {
            personne = new Personne();
            personne.setId(rs.getInt("id"));
            personne.setNom(rs.getString("nom"));
            personne.setPrenom(rs.getString("prenom"));
            personne.setMatricule(rs.getString("matricule") != null ? rs.getString("matricule") : "N/A");
            personne.setTelephone(rs.getString("telephone") != null ? rs.getString("telephone") : "N/A");
            personne.setAdresseCourriel(rs.getString("adresse_courriel") != null ? rs.getString("adresse_courriel")
                    : "N/A");
            personne.setDomaineActivite(rs.getString("domaine_activite") != null ? rs.getString("domaine_activite")
                    : "N/A");
            personne.setMotDePasse(rs.getString("mot_de_passe"));
            personne.setCategorie(Categorie.valueOf(rs.getString("categorie")));

            personne.setListeRouge(rs.getBoolean("liste_rouge"));
        }

        DatabaseConnection.closePreparedStatement(preparedStatement);
        DatabaseConnection.closeConnection(connection);
        return personne;
    }

    @Override
    public List<Personne> getAll() throws SQLException {

        rs = null;

        List<Personne> personnes = new ArrayList<>();

        connection = DatabaseConnection.getConnection();
        String sqlScript = "SELECT * FROM personne ORDER BY id";
        preparedStatement = connection.prepareStatement(sqlScript);
        rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Personne personne = new Personne();
            personne.setId(rs.getInt("id"));
            personne.setNom(rs.getString("nom"));
            personne.setPrenom(rs.getString("prenom"));
            personne.setMatricule(rs.getString("matricule"));
            personne.setTelephone(rs.getString("telephone"));
            personne.setAdresseCourriel(rs.getString("adresse_courriel"));
            personne.setDomaineActivite(rs.getString("domaine_activite"));
            personne.setMotDePasse(rs.getString("mot_de_passe"));
            personne.setCategorie(Categorie.valueOf(rs.getString("categorie")));


            personne.setListeRouge(rs.getBoolean("liste_rouge"));

            personnes.add(personne);
            System.out.println(personne);
        }

        System.out.println(" " + personnes.size() + " personne(s) récupérée(s)");

        DatabaseConnection.closePreparedStatement(preparedStatement);
        DatabaseConnection.closeConnection(connection);

        return personnes;
    }
//Categorie.valueOf(rs.getString("categorie")
    @Override
    public int ajouterMembre(Personne personne) throws SQLException {

        int rowsAffected = 0;

        connection = DatabaseConnection.getConnection();

        sqlScript = "INSERT INTO personne (nom, prenom, matricule, telephone, " +
                "adresse_courriel, domaine_activite, mot_de_passe, categorie, liste_rouge) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        preparedStatement = connection.prepareStatement(sqlScript);
        preparedStatement.setString(1, personne.getNom());
        preparedStatement.setString(2, personne.getPrenom());
        preparedStatement.setString(3, personne.getMatricule());
        preparedStatement.setString(4, personne.getTelephone());
        preparedStatement.setString(5, personne.getAdresseCourriel());
        preparedStatement.setString(6, personne.getDomaineActivite());
        preparedStatement.setString(7, personne.getMotDePasse());

        preparedStatement.setString(8,  personne.getCategorie().name());
        preparedStatement.setBoolean(9, personne.isListeRouge());

        rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println(" Personne insérée avec succès");
        }

        DatabaseConnection.closePreparedStatement(preparedStatement);
        DatabaseConnection.closeConnection(connection);

        return rowsAffected;
    }

    @Override
    public int modifierMembre(Personne personne) throws SQLException {

        int rowsAffected = 0;

        connection = DatabaseConnection.getConnection();

        sqlScript = "UPDATE personne SET nom=?, prenom=?, matricule=?, telephone=?, " +
                "adresse_courriel=?, domaine_activite=?, mot_de_passe=?, " +
                "categorie=?, liste_rouge=? WHERE id=?";

        preparedStatement = connection.prepareStatement(sqlScript);
        preparedStatement.setString(1, personne.getNom());
        preparedStatement.setString(2, personne.getPrenom());
        preparedStatement.setString(3, personne.getMatricule());
        preparedStatement.setString(4, personne.getTelephone());
        preparedStatement.setString(5, personne.getAdresseCourriel());
        preparedStatement.setString(6, personne.getDomaineActivite());
        preparedStatement.setString(7, personne.getMotDePasse()); /////
        preparedStatement.setString(8,  personne.getCategorie().name());
        preparedStatement.setBoolean(9, personne.isListeRouge());
        preparedStatement.setInt(10, personne.getId());

        rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println(" Personne mise à jour avec succès");
        } else {
            System.out.println(" Aucune personne trouvée avec l'ID : " + personne.getId());
        }

        DatabaseConnection.closePreparedStatement(preparedStatement);
        DatabaseConnection.closeConnection(connection);
        return rowsAffected;
    }

    @Override
    public int supprimerMembre(Personne personne) throws SQLException {

        connection = DatabaseConnection.getConnection();

        sqlScript = "DELETE FROM personne WHERE id = ?";
        preparedStatement = connection.prepareStatement(sqlScript);

        preparedStatement.setInt(1, personne.getId());
        int resultat = preparedStatement.executeUpdate();

        DatabaseConnection.closePreparedStatement(preparedStatement);
        DatabaseConnection.closeConnection(connection);

        return resultat;
    }

    @Override
    public Personne rechercherUnmembre(Personne personne) throws SQLException {

        sqlScript = "SELECT * FROM personne WHERE nom = ? AND prenom = ? AND matricule = ? AND telephone = ? AND adresse_courriel = ?";

        connection = DatabaseConnection.getConnection();
        preparedStatement = connection.prepareStatement(sqlScript);

        preparedStatement.setString(1, personne.getNom());
        preparedStatement.setString(2, personne.getPrenom());
        preparedStatement.setString(3, personne.getMatricule());
        preparedStatement.setString(4, personne.getTelephone());
        preparedStatement.setString(5, personne.getAdresseCourriel());
        rs = preparedStatement.executeQuery();
        personne = null;

        while (rs.next()) {
            personne = new Personne(); // Crée une nouvelle personne
            personne.setId(rs.getInt("id"));
            personne.setNom(rs.getString("nom"));
            personne.setPrenom(rs.getString("prenom"));
            personne.setMatricule(rs.getString("matricule"));
            personne.setTelephone(rs.getString("telephone"));
            personne.setAdresseCourriel(rs.getString("adresse_courriel"));
            personne.setDomaineActivite(rs.getString("domaine_activite"));
            personne.setMotDePasse(rs.getString("mot_de_passe"));
            preparedStatement.setString(8,  personne.getCategorie().name());

            personne.setListeRouge(rs.getBoolean("liste_rouge"));
        }

        return personne;
    }

    @Override
    public List<Personne> getMembresParCategorie(Categorie categorie) throws SQLException {
        List<Personne> personnes = new ArrayList<>();

        String sql = "SELECT id, nom, prenom, matricule, telephone, adresse_courriel, " +
                "domaine_activite, mot_de_passe, categorie, liste_rouge " +
                "FROM personne WHERE categorie = ? ORDER BY nom, prenom";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, categorie.name());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Personne p = new Personne();
                    p.setId(rs.getInt("id"));
                    p.setNom(rs.getString("nom"));
                    p.setPrenom(rs.getString("prenom"));
                    p.setMatricule(rs.getString("matricule"));
                    p.setTelephone(rs.getString("telephone"));
                    p.setAdresseCourriel(rs.getString("adresse_courriel"));
                    p.setDomaineActivite(rs.getString("domaine_activite"));
                    p.setMotDePasse(rs.getString("mot_de_passe"));
                    p.setCategorie(Categorie.valueOf(rs.getString("categorie")));

                    p.setListeRouge(rs.getBoolean("liste_rouge"));

                    personnes.add(p);
                    System.out.println(
                            p.getNom() + " " + p.getPrenom() + " membre(s) trouvé(s) dans la catégorie : " + categorie);

                }
            }
        }

        return personnes;
    }

    @Override
    public List<Personne> getProfesseursParDomaine(int domaine) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProfesseursParDomaine'");
    }

    @Override
    public int gererListeRouge(int identifiant, String metSurLrouge) throws SQLException {
        Personne personne = getMembreById(identifiant);
        int resultat = 0;

        if (metSurLrouge.equalsIgnoreCase("oui")) {
            personne.setListeRouge(true);
            resultat = modifierMembre(personne);

        } else {
            personne.setListeRouge(false);
            modifierMembre(personne);
            resultat = 0;
        }
        return resultat;
    }

}
