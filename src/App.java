import Dao.PersonneDAO;
import Dao.PersonneDAOImpl;
import Dao.PersonneDAO;
import Dao.PersonneDAOImpl;
import Entite.Personne;

public class App {
    public static void main(String[] args) throws Exception {

        PersonneDAO personneDAO = new PersonneDAOImpl();

        Personne personne = personneDAO.get(1);
        System.out.println(personne);
    }
}
