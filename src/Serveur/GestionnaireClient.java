package Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import Dao.PersonneDAO;
import Dao.PersonneDAOImpl;
import Entite.Personne;

public class GestionnaireClient implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private PersonneDAO personneDAO;

    // Initialise les flux d'entrée et sortie pour un client connecté et //
    // Instanciation du DAO
    public GestionnaireClient(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);
        this.personneDAO = new PersonneDAOImpl();
    }

    // Gère les interactions entre le serveur et un client : menu, choix, réponses
    @Override
    public void run() {
        try {
            out.println(menu()); // Envoie du menu une première fois
            out.println("END_MENU"); // Marqueur pour indiquer la fin du menu
            while (true) {
                String request = in.readLine();
                if (request.equalsIgnoreCase("quit")) {
                    out.println("Déconnexion...");
                    break;
                }

                int choix;
                try {
                    choix = Integer.parseInt(request);
                } catch (NumberFormatException e) {
                    out.println("Choix invalide. Veuillez entrer un nombre entre 1 et 8.");
                    continue;
                }

                traiterChoix(choix);

                // Ré-affiche le menu après traitement
                out.println(menu());
                out.println("END_MENU");
            }
        } catch (IOException e) {
            System.err.println("IO exception in client handler: " + e.getMessage());
        } finally {
            try {
                in.close();
                out.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void traiterChoix(int choix) {
        try {
            switch (choix) {
                case 1: // Ajouter un membre
                    Personne personne = new Personne(null, null, null, null, null, null, null, null, false);

                    out.println("Entrez le nom du membre :");
                    personne.setNom(in.readLine());
                    out.println("Entrez le prenom du membre :");
                    personne.setPrenom(in.readLine());
                    out.println("Entrez le matricule du membre :");
                    personne.setMatricule(in.readLine());
                    out.println("Entrez le telephone du membre :");
                    personne.setTelephone(in.readLine());
                    out.println("Entrez l'adresse_courriel du membre :");

                    personne.setAdresseCourriel(in.readLine());

                    out.println("Entrez le domaine d'activité du membre : ");
                    personne.setDomaineActivite(in.readLine());

                    out.println("Entrez le mot de passe (si applicable) : ");
                    personne.setMotDePasse(in.readLine());

                    out.println("Entrez la catégorie (professeur / auxiliaire / étudiant) : ");
                    personne.setCategorie(in.readLine());

                    out.println("Le membre est-il sur la liste rouge ? (oui/non) : ");
                    String rep = in.readLine();
                    boolean surListeRouge = rep.equalsIgnoreCase("oui");
                    personne.setListeRouge(surListeRouge);

                    int resultat = personneDAO.insert(personne);

                    break;

                case 2: // Lister professeurs par domaine
                    out.println("Entrez le domaine d'activité:");
                    String domaine = in.readLine();
                    List<Personne> profs = personneDAO.getProfesseursParDomaine(domaine);
                    break;

                case 3: // Rechercher un membre
                    out.println("Entrez le nom ou matricule:");
                    String identifiant = in.readLine();
                    Personne membre = personneDAO.getMembre(identifiant);
                    break;

                case 4: /*
                         * Ajouter membre (admin seulement)
                         * if (verifierMotDePasse()) {
                         * ajouterNouveauMembre();
                         * }
                         */
                    break;

                // ... autres cas
            }
        } catch (SQLException | IOException e) {
            out.println("Erreur: " + e.getMessage());
        }
    }

    // Renvoie le menu principal affiché au client
    public static String menu() {
        return "Bienvenue sur notre plateforme. Que souhaitez-vous faire aujourd'hui ?\n" +
                "MENU\n" +
                " 1 - retourneMembreParCategorie()\n" +
                " 2 - retourneProfesseurParDomaine()\n" +
                " 3 - retourneMembre()\n" +
                " 4 - ajouteMembre()\n" +
                " 5 - modifieMembre()\n" +
                " 6 - supprimeMembre()\n" +
                " 7 - metMembreSurListeRouge()\n" +
                " 8 - retireMembreSurListeRouge()\n" +
                "Tapez votre choix (1-8) ou 'quit' pour quitter.";
    }

}
