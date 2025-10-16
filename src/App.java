import Dao.PersonneDAO;
import Dao.PersonneDAOImpl;
import Dao.PersonneDAO;
import Dao.PersonneDAOImpl;
import Entite.Personne;

public class App {
    public static void main(String[] args) throws Exception {

        /*--------------TESTE 1 POUR INSERT
         * PersonneDAOImpl personneDAO = new PersonneDAOImpl();
         * 
         * Personne personne = new Personne(
         * "konan", "moiye", "0383223", "243434545", "ali.tremblay@uqtr.ca", "Réseaux",
         * "elev2024", "etudiant",
         * false
         * );
         * personneDAO.insert(personne); // ajouter membres
         


        /* -----------TESTE 2 POUR DELETE

         PersonneDAOImpl personneDAO = new PersonneDAOImpl();
         Personne personne = personneDAO.get(2);

        personneDAO.delete(personne);  
        //System.out.println(personne);
         */

         /* -----------TESTE 2 POUR UPDATE

         PersonneDAOImpl personneDAO = new PersonneDAOImpl();
         Personne personne = new Personne(1,
         "unePersonne", "moiye", "23233435", "243434545", "toure.koffi@uqtr.ca", "Réseaux",
          "elev2024", "etudiant",
          false
          );

        personneDAO.update(personne);  
         */


         /* -----------TESTE 2 POUR GETALL

         PersonneDAOImpl personneDAO = new PersonneDAOImpl();
         personneDAO.getAll();  
         
         */

         
         


         

    }
}