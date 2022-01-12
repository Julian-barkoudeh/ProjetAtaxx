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
        this.joueurs.add(new Joueur("j1", "R"));
        this.joueurs.add(new Joueur("j2", "B"));
        /*for(int i = 0; i<2; i++){
            this.ijSelectionCourant[i] = 5;
            this.ijDeplacementCourant[i] = 5;
        }*/
        this.joueurCourant = 0;

    }
    public boolean verifieCordonnee(int i, int j){
        if((i>=1 && i<=p.dimension) && (j>=1 && j<=p.dimension)){
            return true;
        }
        return false;
    }
    public void demanderSelection(){
        int i, j;
        int s = joueurs.get(joueurCourant).saisirSelection();
        j = s%10;
        i = (s-j)/10;
        while(!verifieCordonnee(i,j)){
            s = joueurs.get(joueurCourant).saisirSelection();
            j = s%10;
            i = (s-j)/10;
        }
        this.ijSelectionCourant[0] = i;
        this.ijSelectionCourant[1] = j;
    }
    public void deplacementEstCorrect(int d){
       int deplacement =d%10;
       int dTmp = (int) d/10;
       int[] selectionTmp = new int[2];
       selectionTmp[0] = this.ijSelectionCourant[0]; 
       selectionTmp[1] = this.ijSelectionCourant[1]; 
       int i = 0;
       int j = 0;
       int n = 2;
       System.out.println("selection Tmp i = " +  selectionTmp[0]  + " selection Tmp j = " +selectionTmp[1]);
       do{
        System.out.println("deplacement = " + deplacement + "    dTmp = " + dTmp + "  n = " + n);
           switch(deplacement){
               case 1:
                    i = selectionTmp[0] + 1;
                    j = selectionTmp[1] - 1;
                    deplacement = dTmp%10;
                    System.out.println("Cas1:i = " + i + ",  j = " + j);
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
                    System.out.println("Cas3 :i = " + i + ",  j = " + j);
                    break;
                case 4:
                    i = selectionTmp[0];
                    j = selectionTmp[1] -1;
                    deplacement = dTmp%10;
                    System.out.println("Cas4:i = " + i + ",  j = " + j);
                    break;
                case 5:
                    if(n == 0){
                        System.out.println("Cas51:i = " + i + ",  j = " + j);
                        break;
                    }
                    deplacement = d%10;
                    System.out.println("Cas52:i = " + i + ",  j = " + j);
                    break;
                case 6:
                    i = selectionTmp[0];
                    j = selectionTmp[1] + 1;
                    deplacement = dTmp%10;
                    System.out.println("Cas6 i = " + i + ",  j = " + j);
                    break;
                case 7:
                    i = selectionTmp[0] - 1;
                    j = selectionTmp[1] -1;
                    deplacement = dTmp%10;deplacement = dTmp%10;
                    System.out.println("Cas7:i = " + i + ",  j = " + j);
                    break;
                case 8:
                    i = selectionTmp[0] - 1;
                    j = selectionTmp[1];
                    deplacement = dTmp%10;
                    System.out.println("Cas8:i = " + i + ",  j = " + j);
                    break;
                case 9:
                    i = selectionTmp[0] - 1;
                    j = selectionTmp[1] + 1;
                    deplacement = dTmp%10;
                    System.out.println("Cas9:i = " + i + ",  j = " + j);  
                    break;
            }
            selectionTmp[0] =i; 
            selectionTmp[1] = j;
            dTmp = (int) dTmp/10;
            n--;
       }while(n>=0);
       System.out.println("n = " + n + "   i = " + i + ",  j = " + j);
       this.ijDeplacementCourant[0] = i;
       this.ijDeplacementCourant[1] = j;

    }
    public static void main(String [] args){
        Ataxx a = new Ataxx();
        a.ijSelectionCourant[0] = 3;
        a.ijSelectionCourant[1] = 3;
        a.deplacementEstCorrect(51);
        System.out.println("i = " + a.ijSelectionCourant[0] + ", j = " + a.ijSelectionCourant[1] );
        System.out.println("i = " + a.ijDeplacementCourant[0] + ", j = " + a.ijDeplacementCourant[1] );
    }



}
