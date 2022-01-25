import java.util.InputMismatchException;
import java.util.Scanner;

public class Joueur {
    private String nom;                         //nom du joueur
    private Couleur couleur;                    //couleur des pions du joueur
    private int score;                          //score qui correspond aux cases infectées par le pion du joueur
    Scanner entree  =  new Scanner(System.in);  //Stocke les saisies dans le terminal 

    public Joueur(String nom, Couleur couleur){
        this.nom = nom;
        this.couleur = couleur;
    }

    /**
     * Demande au joueur de saisir les coordonnées d'une case à selectionner sous la forme de ij
     * @return les coordonnées sous forme ij
     */
    public int saisirSelection(){
        int tmp = 0;
        do{
            try{
                System.out.print("Saisiz la case a selectionner (ij):   ");
                tmp = entree.nextInt();
            }
            //Gére les erreurs de saisie
            catch(InputMismatchException e){
                entree.nextLine();
                System.out.println("**** Error : les coordonnées saisies ne correspondent pas à des entiers");
            }
        }while(tmp==0);
        return tmp;
        
    }

    /**
     * Demande au joueur de saisir le code du déplacement à effectuer
     * @return code du déplacement
     */
    public int saisirDeplacement(){
        int tmp = 0;
        do{
            try{
                System.out.print("Saisiz le deplacement :   ");
                tmp = entree.nextInt();
            }
            //Gére les erreurs de saisie
            catch(InputMismatchException e){
                entree.nextLine();
                System.out.println("**** Error : les coordonnées saisies ne correspondent pas à des entiers");
            }
        }while(tmp == 0);
        return tmp;
    }

    /**
     * Methode getter
     * @return le nom du joueur
     */

    public String getNom(){
        return nom;
    }

    /**
     * Methode getter
     * @return la couleur des pions du joueur
     */
    public Couleur getCouleur(){
        return couleur;
    }

    /**
     * Methode getter
     * @return le score du joueur
     */
    public int getScore(){
        return score;
    }

    /**
     * Incrémente le score du joueur de 1
     * @return le nom du joueur
     */
    public void incrementerScore(){
       this.score++;
    }
    
    public String toString(){
        return couleur + nom + Couleur.NORMALE_BCK + "    (score : " + score + ")";
    }
}
