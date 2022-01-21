import java.util.Scanner;

public class Joueur {
    private String nom;
    private Couleur couleur;
    private boolean aPerdu;
    private int score;
    Scanner entree  =  new Scanner(System.in);

    public Joueur(String nom, Couleur couleur){
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
    public Couleur getCouleur(){
        return couleur;
    }
    public boolean getaPerdu(){
        return aPerdu;
    }
    public int getScore(){
        return score;
    }
    public void incrementerScore(){
       this.score++;
    }
    public static void main(String [] args){
        //Joueur j = new Joueur("Barkoudeh", "Red");
       // System.out.println("Valuer saisie  : " + j.saisirSelection());
        int test;
        test = (int) 543/10;
        Couleur c = Couleur.ROUGE_FOR;
        Couleur d2 = Couleur.VERT_FOR;
        System.out.println(d2 == c);
    }

    
}
