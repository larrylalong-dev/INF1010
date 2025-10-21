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
    Personne personne = new Personne(null, null, null, null, null, null, null, null, false);
    PersonneDAOImpl personneDAO = new PersonneDAOImpl();

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

                    out.println("Entrez la catégorie (professeur / auxiliaire / étudiant/ administrateur) : ");
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

                case 5: // Modifier (mettre à jour) un membre
                    out.println("Entrez l'identifiant du membre :");
                    IdNumero = in.readLine();
                    idPersonne = Integer.parseInt(IdNumero);

                    // Récupérer le membre existant depuis la BD
                    Personne membreExistant = personneDAO.getMembreById(idPersonne);
                    String tampon;

                    // Afficher les infos actuelles du membre

                    out.println("=== Membre trouvé ===");
                    out.println("Nom: " + membreExistant.getNom());
                    out.println("Prénom: " + membreExistant.getPrenom());
                    out.println("Matricule: " + membreExistant.getMatricule());
                    out.println("Téléphone: " + membreExistant.getTelephone());
                    out.println("Email: " + membreExistant.getAdresseCourriel());
                    out.println("Domaine: " + membreExistant.getDomaineActivite());
                    out.println("Mot de passe: "
                            + (membreExistant.getMotDePasse() != null ? membreExistant.getMotDePasse() : "Aucun"));
                    out.println("Catégorie: " + membreExistant.getCategorie());
                    out.println("Liste rouge: " + (membreExistant.isListeRouge() ? "Oui" : "Non"));
                    out.println("====================");

                    // membreExistant.setId(idPersonne);

                    out.println("Entrez le nom du membre :");
                    tampon = in.readLine();
                    membreExistant.setNom(
                            (tampon == null || tampon == "" || tampon.isEmpty()) ? membreExistant.getNom() : tampon);

                    out.println("Entrez le prenom du membre :");
                    tampon = in.readLine();
                    membreExistant.setPrenom(tampon.isEmpty() ? membreExistant.getPrenom() : tampon);

                    out.println("Entrez le matricule du membre :");
                    tampon = in.readLine();
                    membreExistant.setMatricule(tampon.isEmpty() ? membreExistant.getMatricule() : tampon);

                    out.println("Entrez le telephone du membre :");
                    tampon = in.readLine();
                    personne.setTelephone(tampon.isEmpty() ? membreExistant.getTelephone() : tampon);

                    out.println("Entrez l'adresse_courriel du membre :");
                    tampon = in.readLine();
                    membreExistant.setAdresseCourriel(tampon.isEmpty() ? membreExistant.getAdresseCourriel() : tampon);

                    out.println("Entrez le domaine d'activité du membre : ");
                    tampon = in.readLine();
                    membreExistant.setDomaineActivite(tampon.isEmpty() ? membreExistant.getDomaineActivite() : tampon);

                    out.println("Entrez le mot de passe (si applicable) : ");
                    tampon = in.readLine();
                    membreExistant.setMotDePasse((tampon.isEmpty() || tampon.trim().isEmpty() || tampon == null)
                            ? membreExistant.getMotDePasse()
                            : tampon);

                    out.println("Entrez la catégorie (professeur / auxiliaire / étudiant) : ");
                    tampon = in.readLine();
                    membreExistant.setCategorie(tampon.isEmpty() ? membreExistant.getCategorie() : tampon);

                    out.println("Le membre est-il sur la liste rouge ? (oui/non) : ");
                    rep = in.readLine();
                    surListeRouge = rep.equalsIgnoreCase("oui");
                    membreExistant.setListeRouge(surListeRouge == true ? true : false);

                    resultat = personneDAO.modifierMembre(membreExistant);

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
                    personne = personneDAO.getMembreById(id);

                    personneDAO.supprimerMembre(personne);
                    // System.out.println(personne); personne = personne.
                    break;

                case 7:
                    // 7 - Mettre un membre sur la liste rouge
                    out.println("Entrez l'identifiant du membre à mettre sur la liste rouge :");
                    IdNumero = in.readLine();
                    idPersonne = Integer.parseInt(IdNumero);
                    resultat = personneDAO.gererListeRouge(idPersonne, "oui");

                    personne = personneDAO.getMembreById(idPersonne);
                    out.println(personne.toString());

                    out.println(personne.getNom() + personne.getPrenom() + "a été ajouté à la liste rouge");

                    break;

                case 8:
                    // 8 - Enlever un membre sur la liste rouge
                    out.println("Entrez l'identifiant du membre à enlever sur la liste rouge :");
                    IdNumero = in.readLine();
                    idPersonne = Integer.parseInt(IdNumero);
                    resultat = personneDAO.gererListeRouge(idPersonne, "non");

                    personne = personneDAO.getMembreById(idPersonne);
                    out.println(personne.toString());

                    out.println(personne.getNom() + personne.getPrenom() + "a été enlevé de la liste rouge");

                    break;
                
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
                " 3 - Rechercher un membre\n" + // fait
                " 4 - Ajouter un membre\n" + // fait
                " 5 - Modifier (mettre à jour) un membre\n" + // fait
                " 6 - Supprimer un membre\n" + // fait
                " 7 - Mettre un membre sur la liste rouge\n" + // fait
                " 8 - Enlever un membre de la liste rouge\n" + //fait
                "Tapez votre choix (1-8) ou 'quit' pour quitter.";
    }

}
