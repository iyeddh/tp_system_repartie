package EX1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

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
		ObjectOutputStream output =
		new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream input =
		new ObjectInputStream(socket.getInputStream());
		// Envoi d'un message au serveur
		output.writeObject(new String("ma première socket"));
		// Réception de la réponse du serveur
		String chaine = (String) input.readObject();
		System.out.println(" recu du serveur : " + chaine);
		} catch (Exception e) {
		System.err.println("Erreur : " + e);
		}
		}
}
