package Dao;

import java.sql.SQLException;
import java.util.List;
import Entite.Personne;

public interface PersonneDAO {

    int ajouterMembre(Personne personne) throws SQLException;

    int modifierMembre(Personne personne) throws SQLException;

    int supprimerMembre(Personne personne) throws SQLException;

    Personne getMembre(Personne personne) throws SQLException; // --revoir

    Personne getMembreById(int id) throws SQLException;

    List<Personne> getAll() throws SQLException;

    List<Personne> getMembresParCategorie(int categorie) throws SQLException;

    List<Personne> getProfesseursParDomaine(int domaine) throws SQLException;

    void mettreSurListeRouge(int identifiant) throws SQLException;

    void retirerDeListeRouge(int identifiant) throws SQLException;
}
