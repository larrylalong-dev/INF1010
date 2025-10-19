package Dao;

import java.sql.SQLException;
import java.util.List;
import Entite.Personne;

public interface PersonneDAO {

    //  Méthodes CRUD principales
    Personne getMembreById(int id) throws SQLException;
    List<Personne> getAll() throws SQLException;
    int insert(Personne personne) throws SQLException;
    int update(Personne personne) throws SQLException;
    int delete(Personne personne) throws SQLException;


    List<Personne> getMembresParCategorie(String categorie) throws SQLException;
    List<Personne> getProfesseursParDomaine(String domaine) throws SQLException;
    void ajouterMembre(Personne membre) throws SQLException;
    void modifierMembre(Personne membre) throws SQLException;
    void mettreSurListeRouge(String identifiant) throws SQLException;
    void retirerDeListeRouge(String identifiant) throws SQLException;
}


