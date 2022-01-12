import java.util.Scanner;

public class Joueur {
    private String nom;
    private String couleur;
    private boolean aPerdu;
    Scanner entree  =  new Scanner(System.in);

    public Joueur(String nom, String couleur){
        this.nom = nom;
        this.couleur = couleur;
    }

    public int saisirSelection(){
        System.out.print("Saisiz la case a selectionner (ij):   ");
        //int tmp = 
        return entree.nextInt();
        
    }
    public int saisirDeplacement(){
        System.out.print("Saisiz le deplacement :   ");
        return entree.nextInt();
    }

    public String getNom(){
        return nom;
    }
    public String getCouleur(){
        return couleur;
    }
    public boolean getaPerdu(){
        return aPerdu;
    }
    public static void main(String [] args){
        //Joueur j = new Joueur("Barkoudeh", "Red");
       // System.out.println("Valuer saisie  : " + j.saisirSelection());
        int test;
        test = (int) 543/10;
        System.out.print(56%100);
    }
    
}
