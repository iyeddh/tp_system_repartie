package EX1;

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
		} catch (NumberFormatException e) {
		System.err.println("Le paramètre n'est pas un entier.");
		System.err.println("Usage : java ServeurUDP port-serveur");
		System.exit(-1);
		}
		try {
		// Création du serveur socket écoutant sur le port spécifié
		ServerSocket serverSocket = new ServerSocket(port);
		// Attente d'une connexion client (bloquant)

		Socket socket = serverSocket.accept();

		//  Création des flux pour envoyer et recevoir des objets
		ObjectOutputStream output =
		new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream input =
		new ObjectInputStream(socket.getInputStream());
		// Lecture d'un message envoyé par le client
		String chaine = (String) input.readObject();
		System.out.println(" recu : " + chaine);
		// Affichage de l'adresse et du port du client
		System.out.println(" ca vient de : " + socket.getInetAddress() +
		":" + socket.getPort());
		// Envoi d'un accusé de réception au client
		output.writeObject(new String("bien recu"));
		} catch (Exception e) {
		System.err.println("Erreur : " + e);
		}
		}
}
