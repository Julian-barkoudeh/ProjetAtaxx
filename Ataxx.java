import java.util.ArrayList;
import java.lang.Math;
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
        /*for(int i = 0; i<2; i++){
            this.ijSelectionCourant[i] = 5;
            this.ijDeplacementCourant[i] = 5;
        }*/
        this.joueurCourant = 0;

    }

    public boolean verifieCordonnee(int i, int j){
        if((i>=0 && i<p.dimension) && (j>=0 && j<p.dimension)){
            return true;
        }
        System.out.println("*** Error ***  : La case selectionnee ne respecte pas les dimensions");
        return false;
    }
    public boolean verifieSelection(int i, int j){
        if(verifieCordonnee(i, j)){
           if( p.donnerCase(i,j).getCouleur() == donnerJoueurCourant().getCouleur()){
               return true;
           }
        }
        return false;
    }
    public boolean verifieDeplacement(int i, int j){
        String message = "";
        if((i>=ijSelectionCourant[0]-2 && i<= ijSelectionCourant[0] + 2) && (j>=ijSelectionCourant[1]-2 && j<= ijSelectionCourant[1] + 2)){
            message = "Le deplacement depasse la limite possible ";
            if(verifieCordonnee(i, j)){
                return true;
            }
        }
        System.out.println("*** Error *** : " + message );
        return false;
    }
    public boolean jeuEstTermine(){
        int x = 0;
        for(int i = ijSelectionCourant[0]-2; i<=ijSelectionCourant[0]+2; i++){
            for(int j = ijSelectionCourant[1]-2; j<=ijSelectionCourant[1]+2; j++){
                if(verifieCordonnee(i, j)){
                    if(p.caseEstVide(i, j)){
                        x++;
                    }
                }
            }
        }
        if(x == 0){
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
            System.out.println("i : " + i  + "  j : " + j);
        }while(!verifieSelection(i-1,j-1));
        this.ijSelectionCourant[0] = i-1;
        this.ijSelectionCourant[1] = j-1;
        System.out.println("Selection i : " + ijSelectionCourant[0]  + "  j : " + ijSelectionCourant[1]);
    }

    public void demanderDeplacement(){
        do{
            decoderDeplacement(joueurs.get(joueurCourant).saisirDeplacement());
            System.out.println("Deplacement i : " + ijDeplacementCourant[0] + "   j :" + ijDeplacementCourant[1]);
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
       //System.out.println("selection Tmp i = " +  selectionTmp[0]  + " selection Tmp j = " +selectionTmp[1]);
       do{
        //System.out.println("deplacement = " + deplacement + "    dTmp = " + dTmp + "  n = " + n);
           switch(deplacement){
               case 1:
                    i = selectionTmp[0] + 1;
                    j = selectionTmp[1] - 1;
                    deplacement = dTmp%10;
                    //System.out.println("Cas1:i = " + i + ",  j = " + j);
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
                    //deplacement = dTmp%(int) Math.pow((double) 10, (double) n);
                    deplacement = dTmp%10;
                    //System.out.println("Cas3 :i = " + i + ",  j = " + j);
                    break;
                case 4:
                    i = selectionTmp[0];
                    j = selectionTmp[1] -1;
                    deplacement = dTmp%10;
                    //System.out.println("Cas4:i = " + i + ",  j = " + j);
                    break;
                case 5:
                    if(n == 0){
                       // System.out.println("Cas51:i = " + i + ",  j = " + j);
                        break;
                    }
                    deplacement = d%10;
                    //System.out.println("Cas52:i = " + i + ",  j = " + j);
                    break;
                case 6:
                    i = selectionTmp[0];
                    j = selectionTmp[1] + 1;
                    deplacement = dTmp%10;
                    //System.out.println("Cas6 i = " + i + ",  j = " + j);
                    break;
                case 7:
                    i = selectionTmp[0] - 1;
                    j = selectionTmp[1] -1;
                    deplacement = dTmp%10;deplacement = dTmp%10;
                    //System.out.println("Cas7:i = " + i + ",  j = " + j);
                    break;
                case 8:
                    i = selectionTmp[0] - 1;
                    j = selectionTmp[1];
                    deplacement = dTmp%10;
                    //System.out.println("Cas8:i = " + i + ",  j = " + j);
                    break;
                case 9:
                    i = selectionTmp[0] - 1;
                    j = selectionTmp[1] + 1;
                    deplacement = dTmp%10;
                    //System.out.println("Cas9:i = " + i + ",  j = " + j);  
                    break;
            }
            selectionTmp[0] =i; 
            selectionTmp[1] = j;
            dTmp = (int) dTmp/10;
            n--;
       }while(n>=0);
       //System.out.println("n = " + n + "   i = " + i + ",  j = " + j);
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
        p.affichePlateau();
        System.out.println("******** Tour du joueur : " + donnerJoueurCourant().getCouleur()+ donnerJoueurCourant().getNom() + Couleur.NORMALE_BCK);
        demanderSelection();
        p.slecteCase(ijSelectionCourant[0] , ijSelectionCourant[1]);
        p.affichePlateau();
        demanderDeplacement();
        p.deplacer((Pion) p.donnerCase(ijSelectionCourant[0], ijSelectionCourant[1]), ijDeplacementCourant[0], ijDeplacementCourant[1]);
        p.infecter((Pion) p.donnerCase(ijDeplacementCourant[0], ijDeplacementCourant[1]), donnerJoueurCourant());
        p.affichePlateau();
        changerTour();
        while(!jeuEstTermine()){
            System.out.println("******** Tour du joueur : " + donnerJoueurCourant().getCouleur()+ donnerJoueurCourant().getNom() + Couleur.NORMALE_BCK);
            demanderSelection();
            p.slecteCase(ijSelectionCourant[0] , ijSelectionCourant[1]);
            p.affichePlateau();
            demanderDeplacement();
            p.deplacer((Pion) p.donnerCase(ijSelectionCourant[0], ijSelectionCourant[1]), ijDeplacementCourant[0], ijDeplacementCourant[1]);
            p.infecter((Pion) p.donnerCase(ijDeplacementCourant[0], ijDeplacementCourant[1]), donnerJoueurCourant());
            changerTour();
            p.affichePlateau();
        }
    }
    public static void main(String [] args){
        Ataxx a = new Ataxx();
        a.gaimePlay();
    }



}
