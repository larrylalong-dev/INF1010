package Client;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 9090;

    // Point d'entrée du client : établit la connexion, envoie les commandes, reçoit
    // les réponses
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);

        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        Thread readerThread = new Thread(() -> {

            try {
                String line;
                while ((line = input.readLine()) != null) {
                    if (line.equals("END_MENU")) {
                        System.out.print("\n> ");
                    } else {
                        System.out.println(line);
                    }
                }
            } catch (Exception e) {
                System.err.println("Connexion perdue avec le serveur");

            }
        });

        readerThread.start();

        String command;
        while((command = clavier.readLine()) != null) {
            if (command.equalsIgnoreCase("quit")) {
                out.println("quit");
                break;
            }
            out.println(command);
        }

        socket.close();

       /*  afficherMenu(input);

        while (true) {

            System.out.println(">");
            String command = clavier.readLine();

            if (command.equalsIgnoreCase("quit"))
                break;
            System.out.println(command);
            out.println(command);

            afficherMenu(input);
        }

        socket.close(); */

    }

    /*
     * Lit et affiche les lignes envoyées par le serveur jusqu’à "END_MENU"
     */
    private static void afficherMenu(BufferedReader input) throws IOException {
        String line;
        while ((line = input.readLine()) != null) {
            if (line.equals("END_MENU"))
                break;
            System.out.println(line);
        }
    }

}