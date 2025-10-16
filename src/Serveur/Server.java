package Serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import Dao.PersonneDAO;

public class Server {


    private static final int PORT = 9090;
// on doit faire de tel ensorte ke le port si est occupe sur un autre pc on puisse l< incrementer pour le changer 
    private static ArrayList<GestionnaireClient> clients = new ArrayList<>();
    private static ExecutorService pool = Executors.newCachedThreadPool(); /// 1-le système supporte plusieurs clients
                                                                           /// qui accèdent en même temps au serveur.
    //PersonneDAO utilisateurDAO = new PersonneDAO();
    ServerSocket listener;
    static PrintWriter envoyeur;

    // Point d’entrée du serveur : accepte les connexions et démarre un thread par
    // client

    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);
        int compteClient = 0;

        while (true) {
            System.out.println("[SERVER] attend une nouvelle connection cliente ...");
            Socket client = listener.accept();

            System.out.println("[SERVER] Connecté a " + compteClient++ +"client!");
            // envoyeur.println("END_MENU");
            GestionnaireClient clientThread = new GestionnaireClient(client); // 2- Creation de thread pour chak client
            clients.add(clientThread);
            // envoyeur = new PrintWriter(client.getOutputStream(), true);
            // envoyeur.println(menu());
            /// les clients ne recoiv pas le menuu

            pool.execute(clientThread);// Lance le thread pour ce client

        }
        
    }


}
