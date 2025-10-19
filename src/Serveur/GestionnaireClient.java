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
    Personne personne = new Personne(null, null, null, null, null, null, null, null, false);

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
        int idPersonne;
        String IdNumero;

        try {
            switch (choix) {
                case 1: // //pas fini---> Lister membres par catégorie

                    // int resultat = personneDAO.insert(personne);

                case 2: // pas fini--> Lister professeurs par domaine

                    /*
                     * out.println("Entrez le domaine d'activité:");
                     * String domaine = in.readLine();
                     * List<Personne> profs = personneDAO.getProfesseursParDomaine(domaine);
                     * break;
                     */

                case 3: // Rechercher un membre --> bon

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

                    personne = personneDAO.rechercherUnmembre(personne);

                    if (personne != null) {
                        out.println("Membre trouvé : " + personne.toString());
                    } else {
                        out.println("Aucun membre correspondant trouvé.");
                    }
                    out.println("END_RESULT");

                    // Personne membre = personneDAO.getMembreById(identifiant);
                    break;

                case 4: // biennnnnnn
                    /*
                     * Ajouter membre (admin seulement)
                     * if (verifierMotDePasse()) {
                     * ajouterNouveauMembre();
                     * }
                     */

                    // personne = new Personne(null, null, null, null, null, null, null, null,
                    // false);

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

                    int resultat = personneDAO.ajouterMembre(personne);

                    if (resultat > 0) {
                        out.println("Membre ajouté avec succès!");
                    } else {
                        out.println(" Erreur lors de l'ajout du membre");
                    }
                    out.println("END_RESULT"); // Marqueur de fin
                    break;

                case 5: // 5 - Modifier (mettre à jour) un membre

                    // PersonneDAOImpl personneDAO = new PersonneDAOImpl();
                    // NORMALEMENT LE CLIENT N EST PAS CENSE RENTRE L ID DU MEMBRE ..MAIS MOI JE
                    // FAIS CELA POUR SIMULER --> L UPDATE

                    out.println("Entrez l'identifiant du membre :");

                    IdNumero = in.readLine();
                    idPersonne = Integer.parseInt(IdNumero);

                    personne.setId(idPersonne);

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

                    rep = in.readLine();
                    surListeRouge = rep.equalsIgnoreCase("oui");
                    personne.setListeRouge(surListeRouge);

                    resultat = personneDAO.modifierMembre(personne);

                    if (resultat > 0) {
                        out.println("Membre mis a jour avec succès!");
                    } else {
                        out.println("Erreur lors de la mise a jour du membre");
                    }
                    out.println("END_RESULT"); // Marqueur de fin
                    break;

                case 6:// biennnnnnnn
                       // Supprimer un membre;

                    out.println("A partir de l interface On fait un get id du membre ki sera affiché:");
                    System.out.println("exemple : id = 3 j ai deja enleve donc choisiautre numero");
                    IdNumero = in.readLine();
                    int id = Integer.parseInt(IdNumero);
                    PersonneDAOImpl personneDAO = new PersonneDAOImpl();
                    personne = personneDAO.getMembreById(id);

                    personneDAO.supprimerMembre(personne);
                    // System.out.println(personne); personne = personne.
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
                " 1 - Lister les membres d'une catégorie donnée\n" +
                " 2 - Lister les professeurs dans un domaine d'activité donné\n" +
                " 3 - Rechercher un membre\n" + //fait
                " 4 - Ajouter un membre\n" + // fait
                " 5 - Modifier (mettre à jour) un membre\n" + // fait
                " 6 - Supprimer un membre\n" + // fait
                " 7 - Mettre un membre sur la liste rouge\n" +
                " 8 - Enlever un membre de la liste rouge\n" +
                "Tapez votre choix (1-8) ou 'quit' pour quitter.";
    }

}
