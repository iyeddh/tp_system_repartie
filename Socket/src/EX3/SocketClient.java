package EX3;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import EX2.Voiture;

public class SocketClient {
	public static void main(String argv[]) {
		int port = 0;
		String host = "";
		Scanner keyb = new Scanner(System.in);
		// Demande à l'utilisateur de saisir le nom du serveur
		System.out.print("Nom du serveur : ");
		host = keyb.next();
		System.out.print("Port du serveur : ");
		try {
		port = keyb.nextInt();
		} catch (NumberFormatException e) {
		System.err.println("Le second paramètre n'est pas un entier.");
		System.exit(-1);
		}
		// initialise la socket et la comminication avec le serveur 
		try {
		// Résolution de l'adresse IP à partir du nom du serveur
		InetAddress adr = InetAddress.getByName(host);
		// Résolution de l'adresse IP à partir du nom du serveur
		Socket socket = new Socket(adr, port);
		// Création des flux pour envoyer et recevoir des objets
		 ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
         ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
		// Envoi d'un message au serveur
		// creation du Personne 
         System.out.println("donner le nom");
         String nom = keyb.next();
         System.out.println("donner l'age ");
         int age = keyb.nextInt(); 
         Personne personne = new Personne(nom, age);
         System.out.println("Personne créée : " + personne);
         //envoi
         output.writeObject(personne);
         System.out.println("voiture envoyee");
		// Réception de la réponse du serveur
		Personne personneModifier = (Personne) input.readObject();
		System.out.println(" recu du serveur : " + personneModifier);
		} catch (Exception e) {
		System.err.println("Erreur : " + e);
		}
		}

}
