package EX3;

import java.io.Serializable;

public class Personne implements Serializable {
	private String nom;
	private int age ;
	public int identifient;
	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", age=" + age+"identifiant "+identifient + "]";
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Personne(String nom , int age) {
		this.age= age;
		this.nom=nom;
		this.identifient = -1;
		
	}
	public int getIdentifient() {
		return identifient;
	}
	public void setIdentifient(int identifient) {
		this.identifient = identifient;
	}
	
	

}
