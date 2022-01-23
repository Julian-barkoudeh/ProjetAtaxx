import java.util.ArrayList;
import java.util.InputMismatchException;
import java.lang.Math;
import java.util.Scanner;
public class Ataxx {
    private ArrayList<Joueur> joueurs = new ArrayList<>();
    private Plateau p;
    private int[] ijSelectionCourant = new int[2];
    private int[] ijDeplacementCourant = new int[2];
    private int joueurCourant;

    public Ataxx(){
        this.p = new Plateau();
        this.joueurs.add(new Joueur("j1", Couleur.ROUGE_BCK));
        this.joueurs.add(new Joueur("j2", Couleur.BLEU_BCK));
        this.joueurCourant = 0;
    }
    
    public boolean verifieDeplacement(int i, int j){
        String message = "Les coordonnées saisis dépassent les dimansions du plateau";
        if(p.verifieCordonnee(i, j)){
            message = "Le deplacement depasse la limite possible ";
            if((i>=ijSelectionCourant[0]-2 
                && i<= ijSelectionCourant[0] + 2) 
                && (j>=ijSelectionCourant[1]-2 
                && j<= ijSelectionCourant[1] + 2)){
                message = "La case de destination est occupée";
                if(p.caseEstVide(i,j)){
                    return true;
                }
            }
        }
        System.out.println("*** Error *** : " + message );
        return false;
    }
    public boolean verifieJoeurPeutJouer(Joueur joueur){
        int cptPions = 0;
        int cptPionsBLoqués = 0;
        for(int i = 0; i<p.dimension; i++){
            for(int j = 0; j<p.dimension; j++){
                if(p.donnerCase(i,j).getCouleur() == joueur.getCouleur()){
                    cptPions++;
                    if(p.verifieSiBloquee((Pion) p.donnerCase(i,j))){
                        cptPionsBLoqués++;
                    }
                }
            }
        }
        if(cptPions == cptPionsBLoqués){
            joueur.setPasseSonTour(true);
            return false;
        }
        return true;
    }
    public boolean jeuEstTermine(){
        int cptCasesVides = 0;
        int cptPionsJoueurCourant = 0;
        for(int i = 0; i<p.dimension; i++){
            for(int j = 0; j<p.dimension; j++){
                if(p.verifieCordonnee(i, j)){
                    if(p.caseEstVide(i, j)){
                        cptCasesVides++;
                    }
                }
                if(p.donnerCase(i,j).getCouleur() == donnerJoueurCourant().getCouleur()){
                    cptPionsJoueurCourant++;
                }
            }
        }
        if(cptCasesVides == 0 || cptPionsJoueurCourant == 0){
            return true;
        }
        return false;
    }

    public void demanderSelection(){
        int i, j;
        int s ;
        do{
        
                s = joueurs.get(joueurCourant).saisirSelection();
                j = s%10;
                i = (s-j)/10;
        }while(!verifieSelection(i-1,j-1));
        this.ijSelectionCourant[0] = i-1;
        this.ijSelectionCourant[1] = j-1;
        //System.out.println("Selection i : " + ijSelectionCourant[0]  + "  j : " + ijSelectionCourant[1]);
    }
    public boolean verifieSelection(int i, int j){
        String message = "Les coordonnées saisis dépassent les dimansions du plateau";
        if(p.verifieCordonnee(i, j)){
            message = "La case selectionnée n'est pas un pion correspondant au joueur";
           if( p.donnerCase(i,j).getCouleur() == donnerJoueurCourant().getCouleur()){
            message = "Le pion est bloqué et ne peut pas se deplacer";
                if(!p.verifieSiBloquee((Pion) p.donnerCase(i,j))){
                    return true;
                }
           }
        }
        System.out.println("*** Error *** : " + message );
        return false;
    }

    public void demanderDeplacement(){
       
        do{
                decoderDeplacement(joueurs.get(joueurCourant).saisirDeplacement());
        
                //System.out.println("Deplacement i : " + ijDeplacementCourant[0] + "   j :" + ijDeplacementCourant[1]);
            }while(!verifieDeplacement(ijDeplacementCourant[0],ijDeplacementCourant[1]));
         
        
    }
    public void decoderDeplacement(int d){
       int deplacement =d%10;
       int dTmp = (int) d/10;
       int[] selectionTmp = new int[2];
       selectionTmp[0] = this.ijSelectionCourant[0]; 
       selectionTmp[1] = this.ijSelectionCourant[1]; 
       int i = 0;
       int j = 0;
       int n = 2;
       do{
           switch(deplacement){
               case 1:
                    i = selectionTmp[0] + 1;
                    j = selectionTmp[1] - 1;
                    deplacement = dTmp%10;
                    break;
                case 2:
                    i = selectionTmp[0] + 1;
                    j = selectionTmp[1];
                    deplacement = dTmp%10;
                    System.out.println("Cas2:i = " + i + ",  j = " + j);
                    break;
                case 3:
                    i = selectionTmp[0] + 1;
                    j = selectionTmp[1] + 1;
                    deplacement = dTmp%10;
                    break;
                case 4:
                    i = selectionTmp[0];
                    j = selectionTmp[1] -1;
                    deplacement = dTmp%10;
                    break;
                case 5:
                    if(n == 0){
                        break;
                    }
                    deplacement = d%10;
                    break;
                case 6:
                    i = selectionTmp[0];
                    j = selectionTmp[1] + 1;
                    deplacement = dTmp%10;
                    break;
                case 7:
                    i = selectionTmp[0] - 1;
                    j = selectionTmp[1] -1;
                    deplacement = dTmp%10;deplacement = dTmp%10;
                    break;
                case 8:
                    i = selectionTmp[0] - 1;
                    j = selectionTmp[1];
                    deplacement = dTmp%10;
                    break;
                case 9:
                    i = selectionTmp[0] - 1;
                    j = selectionTmp[1] + 1;
                    deplacement = dTmp%10;
                    break;
            }
            selectionTmp[0] =i; 
            selectionTmp[1] = j;
            dTmp = (int) dTmp/10;
            n--;
       }while(n>=0);
       this.ijDeplacementCourant[0] = i;
       this.ijDeplacementCourant[1] = j;

    }
    
    public void changerTour(){
        
        if(joueurCourant == 0){
            joueurCourant = 1;
        }
        else{
            joueurCourant = 0;
        }
    }
    public Joueur donnerJoueurCourant(){
        return joueurs.get(joueurCourant);
    }

    public void gaimePlay(){
/*
        p.board.get(6).set(2, new Pion(Couleur.ROUGE_BCK, 6,2));
        p.board.get(5).set(2, new Pion(Couleur.ROUGE_BCK, 5,2));
        p.board.get(4).set(2, new Pion(Couleur.ROUGE_BCK, 4,2));
        p.board.get(4).set(1, new Pion(Couleur.ROUGE_BCK, 4,1));
        p.board.get(4).set(0, new Pion(Couleur.ROUGE_BCK, 4,0));
        p.board.get(5).set(0, new Pion(Couleur.ROUGE_BCK, 5,0));
        p.board.get(5).set(1, new Pion(Couleur.ROUGE_BCK, 5,1));
        p.board.get(6).set(1, new Pion(Couleur.ROUGE_BCK, 6,1));
        p.board.get(6).set(0, new Pion(Couleur.ROUGE_BCK, 6,0));
        
        p.board.get(0).set(4, new Pion(Couleur.ROUGE_BCK, 0,4));
        p.board.get(1).set(4, new Pion(Couleur.ROUGE_BCK, 0,4));
        p.board.get(2).set(4, new Pion(Couleur.ROUGE_BCK, 0,4));
        p.board.get(0).set(5, new Pion(Couleur.ROUGE_BCK, 0,5));
        p.board.get(1).set(5, new Pion(Couleur.ROUGE_BCK, 1,5));
        p.board.get(2).set(5, new Pion(Couleur.ROUGE_BCK, 2,5));
        p.board.get(1).set(6, new Pion(Couleur.ROUGE_BCK, 1,6));
        p.board.get(2).set(6, new Pion(Couleur.ROUGE_BCK, 2,6));*/
        System.out.println(Couleur.NORMALE_BCK + "\n********************************\n");
        p.affichePlateau();
        System.out.println("******** Tour du joueur : " + donnerJoueurCourant().toString());
        demanderSelection();
        p.selectionnerCase(ijSelectionCourant[0] , ijSelectionCourant[1]);
        p.affichePlateau();
        demanderDeplacement();
        p.deplacer((Pion) p.donnerCase(ijSelectionCourant[0], ijSelectionCourant[1]), ijDeplacementCourant[0], ijDeplacementCourant[1]);
        p.infecter((Pion) p.donnerCase(ijDeplacementCourant[0], ijDeplacementCourant[1]), donnerJoueurCourant());
        p.affichePlateau();
        changerTour();
        while(!jeuEstTermine()){
            System.out.println("\n********************************\n");
            if(verifieJoeurPeutJouer(donnerJoueurCourant())){
                System.out.println("******** Tour du joueur : " + donnerJoueurCourant().toString());
                demanderSelection();
                p.selectionnerCase(ijSelectionCourant[0] , ijSelectionCourant[1]);
                p.affichePlateau();
                demanderDeplacement();
                p.deplacer((Pion) p.donnerCase(ijSelectionCourant[0], ijSelectionCourant[1]), ijDeplacementCourant[0], ijDeplacementCourant[1]);
                p.infecter((Pion) p.donnerCase(ijDeplacementCourant[0], ijDeplacementCourant[1]), donnerJoueurCourant());
                p.affichePlateau();
            }
            else{
                System.out.println("******** Joueur doit passer son tour : " + donnerJoueurCourant().toString());
                donnerJoueurCourant().setPasseSonTour(false);
            }
            changerTour();
        }
        int max  = donnerJoueurCourant().getScore();
        System.out.println("*********** Jeu Terminé !! ***********\n");
        if(joueurs.get(1-joueurCourant).getScore() > max){
            System.out.println("Le joueur  " + joueurs.get(1-joueurCourant).toString() + " a gagné");
        }
        else{
            System.out.println("Le joueur  " + donnerJoueurCourant().toString() + " a gagné");
        }

    }
    public static void main(String [] args){
        Ataxx a = new Ataxx();
        a.gaimePlay();
    }



}
