package EX3;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import EX2.Voiture;

public class SocketServer {
	private static int CPT_IDENTIENT=0;
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
                    Personne PersonneRecu = (Personne) input.readObject();
                    System.out.println("Personne reçue du client : " + PersonneRecu);

                    // Demande de carburant
                    
                    
                    
                    	CPT_IDENTIENT++;
                        PersonneRecu.setIdentifient(CPT_IDENTIENT);
                    

                    // Affichage de l'adresse et du port du client
                    System.out.println("Ca vient de : " + socket.getInetAddress() + ":" + socket.getPort());

                    // Envoi de l'objet modifié au client
                    output.writeObject(PersonneRecu);
                    System.out.println("Personne modifiée envoyée au client.");

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
