package EX2;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketServeur {
    public static void main(String argv[]) {
        int port = 0;
        Scanner keyb = new Scanner(System.in);

        // Demande à l'utilisateur de saisir le port d'écoute
        System.out.print("Port d'écoute : ");
        try {
            port = keyb.nextInt();
        } catch (Exception e) {
            System.err.println("Le paramètre n'est pas un entier.");
            System.exit(-1);
        }

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur en écoute sur le port " + port + "...");

            while (true) { // Keep the server running for multiple clients
                try (
                    Socket socket = serverSocket.accept();
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream input = new ObjectInputStream(socket.getInputStream())
                ) {
                    System.out.println("Client connecté : " + socket.getInetAddress());

                    // Lecture de l'objet Voiture envoyé par le client
                    Voiture voitureRecu = (Voiture) input.readObject();
                    System.out.println("Voiture reçue du client : " + voitureRecu);

                    // Demande de carburant
                    int capacity = 0;
                    System.out.print("Donner le carburant à ajouter : ");
                    
                    if (keyb.hasNextInt()) {
                        capacity = keyb.nextInt();
                        voitureRecu.setCarburant(capacity);
                    } else {
                        System.err.println("Entrée invalide, le carburant doit être un entier.");
                        keyb.next(); // Clear invalid input
                    }

                    // Affichage de l'adresse et du port du client
                    System.out.println("Ca vient de : " + socket.getInetAddress() + ":" + socket.getPort());

                    // Envoi de l'objet modifié au client
                    output.writeObject(voitureRecu);
                    System.out.println("Voiture modifiée envoyée au client.");

                } catch (Exception e) {
                    System.err.println("Erreur avec un client : " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Erreur du serveur : " + e.getMessage());
        } finally {
            keyb.close();
        }
    }
}
