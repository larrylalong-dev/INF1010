package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import Database.DatabaseConnection;
import Entite.Personne;

public class PersonneDAOImpl implements PersonneDAO {

    @Override
    public Personne get(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        Personne personne = null;

        String sqlScript = "SELECT id, nom, prenom, matricule, telephone, adresse_courriel, domaine_activite, mot_de_passe, categorie, liste_rouge FROM personne WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlScript);
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            personne = new Personne();
            personne.setId(rs.getInt("id"));
            personne.setNom(rs.getString("nom"));
            personne.setPrenom(rs.getString("prenom"));
            personne.setMatricule(rs.getString("matricule") != null ? rs.getString("matricule") : "N/A");
            personne.setTelephone(rs.getString("telephone") != null ? rs.getString("telephone") : "N/A");
            personne.setAdresseCourriel(rs.getString("adresse_courriel") != null ? rs.getString("adresse_courriel")
                    : "N/A");
            personne.setAdresseCourriel(rs.getString("domaine_activite") != null ? rs.getString("domaine_activite")
                    : "N/A");
            personne.setCategorie(rs.getString("categorie"));
            personne.setListeRouge(rs.getBoolean("liste_rouge"));
        }

        return personne;
    }

    @Override
    public List<Personne> getAll() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public int save(Personne personne) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public int insert(Personne personne) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public int update(Personne personne) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(Personne personne) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
