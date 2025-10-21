package Dao;

import java.sql.SQLException;
import java.util.List;

import CategorieEnum.Categorie;
import Entite.Personne;

public interface PersonneDAO {

    int ajouterMembre(Personne personne) throws SQLException;

    int modifierMembre(Personne personne) throws SQLException;

    int supprimerMembre(Personne personne) throws SQLException;

    Personne rechercherUnmembre(Personne personne) throws SQLException; // --revoir

    Personne getMembreById(int id) throws SQLException;

    List<Personne> getAll() throws SQLException;

    List<Personne> getMembresParCategorie(Categorie categorie) throws SQLException;

    List<Personne> getProfesseursParDomaine(int domaine) throws SQLException;

    public int gererListeRouge(int identifiant,String metSurLrouge) throws SQLException;

}
