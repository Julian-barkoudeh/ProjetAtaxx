import java.util.InputMismatchException;
import java.util.Scanner;

public class Joueur {
    private String nom;
    private Couleur couleur;
    private boolean aPerdu;
    private boolean passeSonTour;
    private int score;
    Scanner entree  =  new Scanner(System.in);

    public Joueur(String nom, Couleur couleur){
        this.nom = nom;
        this.couleur = couleur;
    }

    public int saisirSelection() throws InputMismatchException{
        int tmp = 0;
        do{
            try{
                System.out.print("Saisiz la case a selectionner (ij):   ");
                tmp = entree.nextInt();
            }
            catch(InputMismatchException e){
                entree.nextLine();
                System.out.println("**** Error : les coordonnées saisies ne correspondent pas à des entiers");
            }
        }while(tmp==0);
        return tmp;
        
    }
    public int saisirDeplacement(){
        int tmp = 0;
        do{
            try{
                System.out.print("Saisiz le deplacement :   ");
                tmp = entree.nextInt();
            }
            catch(InputMismatchException e){
                entree.nextLine();
                System.out.println("**** Error : les coordonnées saisies ne correspondent pas à des entiers");
            }
        }while(tmp == 0);
        return tmp;
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
    public boolean getPsseSonTour(){
        return passeSonTour;
    }
    public void setPasseSonTour(boolean passe){
        this.passeSonTour = passe;
    }
    public void incrementerScore(){
       this.score++;
    }
    public String toString(){
        return couleur + nom + Couleur.NORMALE_BCK + "    (score : " + score + ")";
    }
    public static void main(String [] args){
        Joueur j = new Joueur("Barkoudeh", Couleur.ROUGE_FOR);
       // System.out.println("Valuer saisie  : " + j.saisirSelection());
       while(true){
        
        j.saisirSelection();
        
    }
        
    }

    
}
