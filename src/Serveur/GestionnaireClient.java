package Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class GestionnaireClient implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

        // Initialise les flux d'entrée et sortie pour un client connecté
    public GestionnaireClient(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);

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

                // LES OUT AUSSI NORMALEMENT C ES
                switch (choix) {
      case 1: //PAR EXMPLE KE LE CLIENT FAIT 1 le out.println cause aek ->server aek->basedd->serveur->client
                        System.out.println("Haloo");
                        out.println("Appel à retourneMembreParCategorie()");
                        break;
                    case 2:
                        out.println("Appel à retourneProfesseurParDomaine()");
                        break;
                    case 3:
                        out.println("Appel à retourneMembre()");
                        break;
                    case 4:
                        out.println("Appel à ajouteMembre()");
                        break;
                    case 5:
                        out.println("Appel à modifieMembre()");
                        break;
                    case 6:
                        out.println("Appel à supprimeMembre()");
                        break;
                    case 7:
                        out.println("Appel à metMembreSurListeRouge()");
                        break;
                    case 8:
                        out.println("Appel à retireMembreSurListeRouge()");
                        break;
                    default:
                        out.println("Choix non valide. Entrez un nombre entre 1 et 8.");
                        break;
                }

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
